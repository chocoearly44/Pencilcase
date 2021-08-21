package tk.thesuperlab.pencilcase.modes;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.net.WhoisClient;
import tk.thesuperlab.pencilcase.entities.OperatingMode;
import tk.thesuperlab.pencilcase.utils.ConstantsUtils;
import tk.thesuperlab.pencilcase.utils.WhoisUtils;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BOLD;
import static com.diogonunes.jcolor.Attribute.YELLOW_TEXT;
import static tk.thesuperlab.pencilcase.utils.ConsoleUtils.std.*;

public class WhoisMode implements OperatingMode {
	private Scanner scanner;

	public WhoisMode(Scanner scanner) {
		this.scanner = scanner;
	}

	@Override
	public void start(String[] args) {
		try {
			clearConsole();
			printTitle("WhoIs Utility", ConstantsUtils.whoisUtilVersion);

			Options options = new Options();
			options.addOption("h", false, "Display this list.");
			options.addOption("ip", true, "Get information about IP.");
			options.addOption("d", true, "Get information about Domain.");

			CommandLineParser parser = new BasicParser();
			CommandLine cmd = parser.parse(options, args);

			if(cmd.hasOption("h") && !cmd.hasOption("d") && !cmd.hasOption("ip")) {

			} else if(!cmd.hasOption("h") && cmd.hasOption("d") && !cmd.hasOption("ip")) {
				printMessage(colorize("Retrieving WHOIS records...", YELLOW_TEXT(), BOLD()));

				String domain = cmd.getOptionValue("d");
				WhoisClient whois = new WhoisClient();
				whois.connect("whois.iana.org");
				printMessage(colorize("Records retrieved.\n", YELLOW_TEXT(), BOLD()));

				printSectionTitle("WHOIS Records:");
				printMessage(whois.query(domain));

				whois.disconnect();
			} else if(!cmd.hasOption("h") && !cmd.hasOption("d") && cmd.hasOption("ip")) {
				printMessage(colorize("Retrieving IP info...", YELLOW_TEXT(), BOLD()));
				printMessage(colorize("Records retrieved.\n", YELLOW_TEXT(), BOLD()));

				printSectionTitle("IP Information:");
				printMessage(WhoisUtils.retrieveIpInfo(cmd.getOptionValue("ip")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}