package tk.thesuperlab.pencilcase.entities.configs.groups;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IpinfoConfig {
	@JsonProperty
	private String apiKey;

	public IpinfoConfig() {
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
