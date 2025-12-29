package java24;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.NamedParameterSpec;

public class MlDsa {
  public static final String MESSAGE = "Die Nachricht.";

  /** Der Sender erzeugt ein öffentliches und privates Schlüsselpaar. */
  static KeyPair stepFirst() throws GeneralSecurityException {
    var keyPairGenerator = KeyPairGenerator.getInstance("ML-DSA");
    keyPairGenerator.initialize(NamedParameterSpec.ML_DSA_65); // Default
    return keyPairGenerator.generateKeyPair();
  }

  /** Der Sender signiert eine Nachricht mit seinem privaten Schlüssel. */
  static byte[] stepSecond(PrivateKey senderPrivateKey) throws GeneralSecurityException {
    var message = MESSAGE.getBytes(StandardCharsets.UTF_8);

    var signature = Signature.getInstance("ML-DSA");
    signature.initSign(senderPrivateKey);
    signature.update(message);
    return signature.sign();
  }

  /** Der Empfänger überprüft die Signatur mit dem öffentlichen Schlüssel des Senders. */
  static Boolean stepThird(PublicKey senderPublicKey, byte[] signatureData)
      throws GeneralSecurityException {
    var message = MESSAGE.getBytes(StandardCharsets.UTF_8);

    var signature = Signature.getInstance("ML-DSA");
    signature.initVerify(senderPublicKey);
    signature.update(message);
    return signature.verify(signatureData);
  }

  static void process() throws GeneralSecurityException {
    var senderkeyPair = stepFirst();

    var signatureData = stepSecond(senderkeyPair.getPrivate());

    var verified = stepThird(senderkeyPair.getPublic(), signatureData);
    System.out.printf("Valid: %s%n", verified);
  }
}
