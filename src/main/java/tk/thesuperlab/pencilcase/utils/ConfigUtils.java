package tk.thesuperlab.pencilcase.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import tk.thesuperlab.pencilcase.entities.configs.Config;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConfigUtils {
	private static final ObjectMapper om;
	public static Config config;

	static {
		om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static void createConfig(File file) {
		try {
			Config config = new Config();
			PrintWriter pw = new PrintWriter(file);

			pw.write(om.writeValueAsString(config));
			pw.flush();
			pw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static Config loadConfig() {
		try {
			File config = StorageUtils.getConfig();

			StringBuilder fileContent = new StringBuilder();
			Scanner myReader = new Scanner(config);

			while(myReader.hasNextLine()) {
				fileContent.append(myReader.nextLine());
			}

			return om.readValue(fileContent.toString(), Config.class);
		} catch(Exception e) {
			e.printStackTrace();

			return null;
		}
	}
}
