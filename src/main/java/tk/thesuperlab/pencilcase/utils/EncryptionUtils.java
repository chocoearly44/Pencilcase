package tk.thesuperlab.pencilcase.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Base64;

public class EncryptionUtils {
	public EncryptionUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static String encryptSha256(String input) {
		return DigestUtils.sha256Hex(input);
	}

	public static String encryptMd5(String input) {
		return DigestUtils.md5Hex(input);
	}

	public static String encryptBase64(String input) {
		return Base64.getEncoder().encodeToString(input.getBytes());
	}
}
