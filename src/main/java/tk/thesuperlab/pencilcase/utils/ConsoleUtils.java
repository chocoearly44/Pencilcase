package tk.thesuperlab.pencilcase.utils;

import com.diogonunes.jcolor.Attribute;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.RESET;
import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

public class ConsoleUtils {
	private static final Attribute[] errorFormat = new Attribute[]{RED_TEXT(), BOLD()};
	private static final Attribute[] promptFormat = new Attribute[]{CYAN_TEXT(), BOLD()};
	private static final Attribute[] titleFormat = new Attribute[]{WHITE_TEXT(), CYAN_BACK(), BOLD()};
	private static final Attribute[] optionsTitleFormat = new Attribute[]{YELLOW_TEXT(), BOLD()};
	private static final Attribute[] optionFormat = new Attribute[]{YELLOW_TEXT()};
	private static final Attribute[] successFormat = new Attribute[]{WHITE_TEXT(), BLUE_BACK()};

	public static class std {
		public static void printHelp(String commandName, String commandDescription) {

		}

		public static int printOptions(String optionsTitle, Scanner scanner, String... options) {
			System.out.println(colorize(optionsTitle, optionsTitleFormat));

			for(int i = 0; i < options.length; i++) {
				System.out.println(colorize(i + 1 + ". " + options[i], optionFormat));
			}

			boolean inputValid = false;
			int selection = 0;

			while (!inputValid) {
				System.out.print("> ");

				if (scanner.hasNextDouble()) {
					selection = scanner.nextInt();

					if (selection > options.length) {
						err.invalidSelection();
					}
					else {
						inputValid = true;
					}
				} else {
					err.notNumber();
					scanner.next();
				}
			}

			return selection;
		}

		public static void printTitle(String utilityName, String utilityVersion) {
			clearConsole();
			System.out.println(colorize(utilityName + " " + utilityVersion, titleFormat));
			System.out.println("\n");
		}

		public static void printMessage(String message) {
			System.out.println(message);
		}

		public static void printSectionTitle(String message) {
			System.out.println(colorize(message, successFormat));
		}

		public static String prompt(String message, Scanner scanner) {
			System.out.println(colorize(message, promptFormat));
			System.out.print("> ");

			return scanner.nextLine();
		}

		public static void clearConsole() {
			System.out.print(RESET);
		}
	}

	public static class err {
		public static void invalidArguments() {
			System.out.println(colorize("Invalid arguments.", errorFormat));
		}

		public static void notNumber() {
			System.out.println(colorize("Input is not a number.", errorFormat));
		}

		public static void invalidSelection() {
			System.out.println(colorize("Invalid selection.", errorFormat));
		}
	}
}
