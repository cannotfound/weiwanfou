package spittr.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan({"spittr.aop"})
public class AudienceConfig {
	
	@Bean
	public Audience audience() {
		
		return new Audience();
	}

}
