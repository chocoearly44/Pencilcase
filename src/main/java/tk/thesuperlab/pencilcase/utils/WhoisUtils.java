package tk.thesuperlab.pencilcase.utils;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import static tk.thesuperlab.pencilcase.App.config;

public class WhoisUtils {
	public static String retrieveIpInfo(String ipAddress) {
		String ipInfoApiKey = config.getIpInfoConfig().getApiKey();

		try {
			OkHttpClient httpClient = new OkHttpClient();
			Request request = new Request.Builder()
					.url("https://ipinfo.io/" + ipAddress + "/?token=" + ipInfoApiKey)
					.build();

			Response response = httpClient.newCall(request).execute();

			if(response.isSuccessful()) {
				return response.body().string();
			} else {
				return "";
			}
		} catch(Exception e) {
			e.printStackTrace();

			return "";
		}
	}
}