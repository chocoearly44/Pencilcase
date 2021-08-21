package tk.thesuperlab.pencilcase.entities.configs;

import com.fasterxml.jackson.annotation.JsonProperty;
import tk.thesuperlab.pencilcase.entities.configs.groups.IpinfoConfig;

public class Config {
	@JsonProperty("IPInfo")
	IpinfoConfig ipInfoConfig = new IpinfoConfig();

	public Config() {
	}

	public IpinfoConfig getIpInfoConfig() {
		return ipInfoConfig;
	}

	public void setIpInfoConfig(IpinfoConfig ipInfoConfig) {
		this.ipInfoConfig = ipInfoConfig;
	}
}
