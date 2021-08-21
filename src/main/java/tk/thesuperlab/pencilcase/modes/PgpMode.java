package tk.thesuperlab.pencilcase.modes;

import tk.thesuperlab.pencilcase.entities.OperatingMode;
import tk.thesuperlab.pencilcase.utils.ConstantsUtils;

import javax.xml.bind.DatatypeConverter;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;
import static tk.thesuperlab.pencilcase.utils.ConsoleUtils.std.*;
import static tk.thesuperlab.pencilcase.utils.PgpUtils.*;

public class PgpMode implements OperatingMode {
	private Scanner scanner;

	public PgpMode(Scanner scanner) {
		this.scanner = scanner;
	}

	@Override
	public void start(String[] args) {
		try {
			clearConsole();
			printTitle("Pretty Good Privacy Utility", ConstantsUtils.pgpUtilVersion);

			int selection = printOptions(
					"I want to...",
					scanner,
					"decrypt and receive message",
					"encrypt and send message"
			);

			if(selection == 1) {
				printMessage(colorize("\nGenerating your RSA Keys (this could take a while)...", YELLOW_TEXT(), BOLD()));
				KeyPair keypair = generateRSAKeyPair();

				printSectionTitle("Here is your public key:");
				printMessage(DatatypeConverter.printHexBinary(keypair.getPublic().getEncoded()));
				printMessage("");
				printMessage(colorize("Send this key to anyone that you want to encrypt their messages.", GREEN_TEXT()));
				printMessage(colorize("You will be the only one who will be able to decrypt messages encrypted with public key above.", GREEN_TEXT()));

				scanner.nextLine();
				String messageRaw = prompt("\nEnter message that you want to decrypt:", scanner);

				byte[] messageEncrypted = DatatypeConverter.parseHexBinary(messageRaw);
				String decryptedText = do_RSADecryption(messageEncrypted, keypair.getPrivate());

				printSectionTitle("\nHere is your message decrypted:");
				printMessage(decryptedText);
			} else {
				scanner.nextLine();
				String messagePlain = prompt("\nEnter message that you want to encrypt:", scanner);
				String publicKeyRaw = prompt("\nEnter receiver's public key:", scanner);

				KeyFactory kf = KeyFactory.getInstance("RSA");
				PublicKey publicKey = kf.generatePublic(
						new X509EncodedKeySpec(
								DatatypeConverter.parseHexBinary(publicKeyRaw)
						)
				);
				byte[] cipherText = do_RSAEncryption(messagePlain, publicKey);

				printSectionTitle("\nHere is your encrypted message:");
				printMessage(DatatypeConverter.printHexBinary(cipherText));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}