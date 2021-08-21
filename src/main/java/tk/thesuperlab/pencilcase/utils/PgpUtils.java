package tk.thesuperlab.pencilcase.utils;

import javax.crypto.Cipher;
import java.security.*;

public class PgpUtils {
	private static final String RSA = "RSA";

	public static KeyPair generateRSAKeyPair() throws Exception {
		SecureRandom secureRandom = new SecureRandom();
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);

		keyPairGenerator.initialize(8192, secureRandom);
		return keyPairGenerator.generateKeyPair();
	}

	public static byte[] do_RSAEncryption(String plainText, PublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance(RSA);

		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(plainText.getBytes());
	}

	public static String do_RSADecryption(byte[] cipherText, PrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance(RSA);

		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] result = cipher.doFinal(cipherText);

		return new String(result);
	}
}
