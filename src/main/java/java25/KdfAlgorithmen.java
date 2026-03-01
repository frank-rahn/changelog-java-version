package java25;

import java.security.GeneralSecurityException;
import javax.crypto.KDF;
import javax.crypto.spec.HKDFParameterSpec;

public class KdfAlgorithmen {
  /**
   * Eine Parameter-Spezifikation von Typ ExtractExpand erzeugen:
   * <ol>
   *   <li>Extract: Konzentriert die Entropie aus der Eingabe, um einen pseudorandom key (PRK) zu erzeugen</li>
   *   <li>Erweitert den PRK zu einem oder mehreren Schlüsseln, wobei der info-Parameter zur Kontextbindung dient</li>
   * </ol>
   */
  private static void process() throws GeneralSecurityException {
    // Das primäre geheime Material
    var initialKeyMaterial = "seed-key-material".getBytes();
    // Verbessert Sicherheit, besonders wenn die Eingabe schwach ist
    var salt = "salt".getBytes();
    // Verhindert Wiederverwendung des gleichen Schlüssels in verschiedenen Kontexten
    var info = "encryption".getBytes(); // or authentication
    // Eine Parameter-Spezifikation von Typ ExtractExpand erzeugen
    var params =
        HKDFParameterSpec.ofExtract()
            .addIKM(initialKeyMaterial)
            .addSalt(salt)
            .thenExpand(info, 32);

    // Ein KDF-Objekt für den Algorithmus HKDF-SHA256 (HMAC-based Key Derivation Function) erzeugen
    var hkdf = KDF.getInstance("HKDF-SHA256");

    // Den 32-byte AES Schlüssel ermitteln
    var aesKey = hkdf.deriveKey("AES", params);
    IO.println("Key algorithm: " + aesKey.getAlgorithm());
    IO.println("Key length:    " + aesKey.getEncoded().length);
  }

  static void main() {
    try {
      process();
    } catch (GeneralSecurityException e) {
      e.printStackTrace();
    }
  }
}
