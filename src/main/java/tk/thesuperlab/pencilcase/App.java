package tk.thesuperlab.pencilcase;

import tk.thesuperlab.pencilcase.entities.OperatingMode;
import tk.thesuperlab.pencilcase.entities.configs.Config;
import tk.thesuperlab.pencilcase.modes.PgpMode;
import tk.thesuperlab.pencilcase.modes.WhoisMode;
import tk.thesuperlab.pencilcase.utils.ConfigUtils;
import tk.thesuperlab.pencilcase.utils.StorageUtils;

import java.util.HashMap;
import java.util.Scanner;

public class App {
	public static Config config;
	private static Scanner scanner = new Scanner(System.in);
	private static HashMap<String, OperatingMode> modes = new HashMap<String, OperatingMode>();

	public static void main(String[] args) {
		StorageUtils.initialiseStorage();
		config = ConfigUtils.loadConfig();

		modes.put("pgp", new PgpMode(scanner));
		modes.put("whois", new WhoisMode(scanner));

		modes.get(args[0]).start(args);
	}
}