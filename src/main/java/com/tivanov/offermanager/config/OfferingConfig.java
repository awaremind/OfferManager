package com.tivanov.offermanager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "system.offering")
public class OfferingConfig {
	
	@Getter @Setter
	private int defaultValidityDays;

}
