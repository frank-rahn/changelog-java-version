package java24;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.NamedParameterSpec;
import java.util.HexFormat;
import javax.crypto.Cipher;
import javax.crypto.KEM;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MlKem {
  record Request(byte[] keyEncapsulationMessage, byte[] encryptedMessage) {}

  public static final int IV_LENGTH = 12;
  public static final String MESSAGE = "Die geheime Nachricht.";

  private static String formatHex(byte[] bytes) {
    return HexFormat.of().formatHex(bytes);
  }

  private static byte[] createInitializationVector() {
    var iv = new byte[IV_LENGTH];
    new SecureRandom().nextBytes(iv);
    return iv;
  }

  /** Der Empfänger erzeugt ein öffentliches und privates Schlüsselpaar. */
  static KeyPair stepFirst() throws GeneralSecurityException {
    var keyPairGenerator = KeyPairGenerator.getInstance("ML-KEM");
    keyPairGenerator.initialize(NamedParameterSpec.ML_KEM_768); // Default
    return keyPairGenerator.generateKeyPair();
  }

  /** Der Sender verschlüsselt eine Nachricht mit dem öffentlichen Schlüssel des Empfängers. */
  static Request stepSecond(PublicKey receiverPublicKey) throws GeneralSecurityException {
    var message = MESSAGE.getBytes(StandardCharsets.UTF_8);

    var kem = KEM.getInstance("ML-KEM");
    var encapsulator = kem.newEncapsulator(receiverPublicKey);
    var encapsulated = encapsulator.encapsulate();
    var sessionKey = encapsulated.key();
    System.out.printf(
        "Sender   - Algorithm: %s, Format: %s, Encoded: %s%n",
        sessionKey.getAlgorithm(), sessionKey.getFormat(), formatHex(sessionKey.getEncoded()));

    // Erzeuge AES-256-Schlüssel
    var aesKeySpec = new SecretKeySpec(sessionKey.getEncoded(), "AES");
    var iv = createInitializationVector();
    var gcmSpec = new GCMParameterSpec(128, iv);
    var cipher = Cipher.getInstance("AES/GCM/NoPadding");
    cipher.init(Cipher.ENCRYPT_MODE, aesKeySpec, gcmSpec);
    var encryptedMessage = cipher.doFinal(message);

    // Den Initialization Vector der encryptedMessage hinzufügen
    var data =
        ByteBuffer.allocate(iv.length + encryptedMessage.length)
            .put(iv)
            .put(encryptedMessage)
            .array();

    return new Request(encapsulated.encapsulation(), data);
  }

  /** Der Empfänger entschlüsselt die Nachricht mit dem öffentlichen Schlüssel des Senders. */
  static String stepThird(PrivateKey receiverPrivateKey, Request request)
      throws GeneralSecurityException {
    var kem = KEM.getInstance("ML-KEM");
    var decapsulator = kem.newDecapsulator(receiverPrivateKey);
    var sessionKey = decapsulator.decapsulate(request.keyEncapsulationMessage);
    System.out.printf(
        "Receiver - Algorithm: %s, Format: %s, Encoded: %s%n",
        sessionKey.getAlgorithm(), sessionKey.getFormat(), formatHex(sessionKey.getEncoded()));

    // Erzeuge AES-256-Schlüssel
    var aesKeySpec = new SecretKeySpec(sessionKey.getEncoded(), "AES");
    var gcmSpec = new GCMParameterSpec(128, request.encryptedMessage, 0, IV_LENGTH);
    var cipher = Cipher.getInstance("AES/GCM/NoPadding");
    cipher.init(Cipher.DECRYPT_MODE, aesKeySpec, gcmSpec);
    var message =
        cipher.doFinal(
            request.encryptedMessage, IV_LENGTH, request.encryptedMessage.length - IV_LENGTH);

    return new String(message, StandardCharsets.UTF_8);
  }

  static void process() throws GeneralSecurityException {
    var receiverKeyPair = stepFirst();

    var request = stepSecond(receiverKeyPair.getPublic());

    var message = stepThird(receiverKeyPair.getPrivate(), request);
    System.out.printf("Received: '%s'%n", message);
  }
}
