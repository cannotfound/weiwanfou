package com.run.audience;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class AudienceConfig {
	
	@Bean
	public Audience audience() {
		return new Audience();
	}
	
	@Bean
	public AddISayToIPerformance addIToTopShow() {
		return new AddISayToIPerformance();
	}
}
