package soundsystem;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import say.ISay;

@Configuration
@ComponentScan(basePackageClasses= {ISay.class})
public class CDPlayerConfig { 
}
