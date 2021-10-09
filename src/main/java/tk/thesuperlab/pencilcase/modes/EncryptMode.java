package tk.thesuperlab.pencilcase.modes;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import tk.thesuperlab.pencilcase.entities.OperatingMode;
import tk.thesuperlab.pencilcase.utils.ConstantsUtils;
import tk.thesuperlab.pencilcase.utils.EncryptionUtils;

import java.util.Scanner;

import static tk.thesuperlab.pencilcase.utils.ConsoleUtils.std.*;

public class EncryptMode implements OperatingMode {
	private Scanner scanner;

	public EncryptMode(Scanner scanner) {
		this.scanner = scanner;
	}

	@Override
	public void start(String[] args) {
		try {
			clearConsole();
			printTitle("Encryption Utility", ConstantsUtils.encryptionUtilVersion);

			Options options = new Options();
			options.addOption("h", false, "Display this list.");
			options.addOption("a", true, "Encryption algorithm.");
			options.addOption("m", true, "Message that you want to encrypt.");

			CommandLineParser parser = new BasicParser();
			CommandLine cmd = parser.parse(options, args);

			if(cmd.hasOption("h") && !cmd.hasOption("a") && !cmd.hasOption("m")) {

			} else if(!cmd.hasOption("h") && cmd.hasOption("a") && cmd.hasOption("m")) {
				switch(cmd.getOptionValue("a")) {
					case "sha256":
						printSectionTitle("Here is your sha256 hash:");
						printMessage(EncryptionUtils.encryptSha256(cmd.getOptionValue("m")));
						break;

					case "md5":
						printSectionTitle("Here is your md5 hash:");
						printMessage(EncryptionUtils.encryptMd5(cmd.getOptionValue("m")));
						break;

					case "base64":
						printSectionTitle("Here is your Base64 hash:");
						printMessage(EncryptionUtils.encryptBase64(cmd.getOptionValue("m")));
						break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
