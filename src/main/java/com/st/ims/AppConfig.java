package com.st.ims;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
	
	@Value("spring.datasource.url")
	private String dataSourceUrl;
	
	@Value("spring.datasource.username")
	private String dataSourceUsername;
	
	@Autowired
	private PropertiesConfiguration proConfig;
	
	public PropertiesConfiguration getProConfig() {
		return proConfig;
	}

	public String getDataSourceUrl() {
		return proConfig.getString(dataSourceUrl);
	}

	public void setDataSourceUrl(String dataSourceUrl) {
		this.dataSourceUrl = dataSourceUrl;
	}

	public String getDataSourceUsername() {
		return proConfig.getString(dataSourceUsername);
	}

	public void setDataSourceUsername(String dataSourceUsername) {
		this.dataSourceUsername = dataSourceUsername;
	}
	
	
	
	

}
