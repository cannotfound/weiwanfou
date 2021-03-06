package spittr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 我加的呵呵
 * @author hel
 * 数据源配置applicationContext 和DataConfig任选一
 */
@Configuration
@ComponentScan(basePackages= {"spittr"}, excludeFilters= {@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ImportResource("classpath*:applicationContext.xml")
@Import({ DataConfig.class, RabbitMQConfig.class })
public class RootConfig {

}
