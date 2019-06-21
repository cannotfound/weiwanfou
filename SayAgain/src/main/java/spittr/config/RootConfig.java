package spittr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 我加的呵呵
 * @author hel
 *
 */
@Configuration
@ComponentScan(basePackages= {"spittr"}, excludeFilters= {@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RootConfig {

}
