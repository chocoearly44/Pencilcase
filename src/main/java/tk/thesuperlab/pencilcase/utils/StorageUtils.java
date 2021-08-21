package tk.thesuperlab.pencilcase.utils;

import org.apache.commons.io.FileUtils;
import tk.thesuperlab.pencilcase.App;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class StorageUtils {
	private static File storageRoot() {
		return new File(System.getenv("APPDATA") + "/pencilcase");
	}

	public static void initialiseStorage() {
		try {
			if(!Files.exists(Path.of(storageRoot().getAbsolutePath()))) {
				Files.createDirectory(Path.of(storageRoot().getAbsolutePath()));
				File config = new File(storageRoot().getAbsolutePath() + "/config.json");

				config.createNewFile();
				ConfigUtils.createConfig(config);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static File getConfig() {
		return new File(storageRoot().getAbsolutePath() + "/config.json");
	}
}
