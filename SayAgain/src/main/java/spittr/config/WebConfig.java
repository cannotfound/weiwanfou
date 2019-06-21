package spittr.config;

import java.io.IOException;

import javax.persistence.Basic;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import spittr.interceptor.AuthInterceptor;

/**
 * 静态资源处理：(待验证)</br>
 * 如果是WebMvcConfigurationSupport接口，要覆盖两个	 resourceHandlerMapping(),addResourceHandlers()</br>
 * 如果是WebMvcConfigurerAdapter接口，configureDefaultServletHandling()就可以了。
 * 
 * 
 * proxyTargetClass=true, 能注解装配实现类，如果false,只能装配命名为接口的变量
 * @author only_TG
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan("spittr.*")
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class WebConfig extends WebMvcConfigurationSupport {

	Logger logger = LoggerFactory.getLogger(WebConfig.class);
	
	/**
	 * 我们要求DispatcherServlet将对静态资源
	 * 的请求转发到Servlet容器中默认的Servlet上，而不是使用DispatcherServlet本身来处理 此类请求
	 * WebMvcConfigurerAdapter接口实现中，这个覆盖就可以了。
	 */
	/*@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		
		configurer.enable();
		
	}*/
	
	
	public ViewResolver viewResolver() {

		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	@Bean
	public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
		// ----thymeleaf
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setTemplateEngine(templateEngine);
		return thymeleafViewResolver;
	}

	/**
	 * 模板引擎
	 * @param templateResolver
	 * @return
	 */
	@Bean
	public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setEnableSpringELCompiler(true);
		templateEngine.setTemplateResolver(templateResolver);
		templateEngine.addDialect(new SpringSecurityDialect());//注册安全方言
		return templateEngine;
	}

	/**
	 * 模板解析器
	 * @return
	 */
	@Bean
	public ITemplateResolver templateResolver(){
		
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(getApplicationContext());		
		resolver.setPrefix("/WEB-INF/thymeleafs/");
		resolver.setSuffix(".html");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setTemplateMode(TemplateMode.HTML5);
		resolver.setCacheable(false);
		return resolver;
		
		
	}
	
	/**
	 * 静态资源处理
	 * resourceHandlerMapping()
	 * addResourceHandlers()
	 * 在WebMvcConfigurationSupport接口实现两个都必须要
	 * 
	 */
	@Bean
	public HandlerMapping resourceHandlerMapping() {
		 
		return super.resourceHandlerMapping();
	}
	
	/**
	 * 静态资源处理
	 * 
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		 
		
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	/**
	 * basename属性可以设置为在 类路径下（以“classpath:”作为前缀） 、文件系统中（以“file:”作为前缀） 或Web应用
	 * 的根路径下（没有前缀）查找属性
	 * 
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {

		ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();

		reloadableResourceBundleMessageSource.setBasename("i18N/cn");
		reloadableResourceBundleMessageSource.setCacheSeconds(10);

		return reloadableResourceBundleMessageSource;
	}
	
	/**
	 * 名字必须为multipartResolver
	 * @return
	 */
	@Bean(name="multipartResolver")
	public MultipartResolver multipartResolverSM() {
		return new StandardServletMultipartResolver();
	}
	
	/**
	 * multipartResolverCM() multipartResolverSM()两种MultipartResolver的实现，
	 * 不加Bean注解不就不生效了。。
	 * 与StandardServletMultipartResolver 相比 无法设定multipart请求整体的最大容量
	 * @return
	 * @throws IOException 
	 */
	public MultipartResolver multipartResolverCM() throws IOException {
		
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		
		commonsMultipartResolver.setMaxUploadSize(2097152);
		
		//内存大小为0，表明不管文件多大都会写到磁盘上
		commonsMultipartResolver.setMaxInMemorySize(0);
		commonsMultipartResolver.setUploadTempDir(new FileSystemResource("/uploads"));
		
		return commonsMultipartResolver;
	}

	@Bean
	public HandlerInterceptor authInterceptor() {
		return new AuthInterceptor();
	}
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		
		logger.error("--------addInterceptors--------");
		
		registry.addInterceptor(authInterceptor());//.addPathPatterns("/**");
		
		
		 
	}

	/**
	 * RequestMappingHandlerMapping 需要显示声明，否则不能注册自定义的拦截器>. <br>
	 * 奇怪----
	 */
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		
		return super.requestMappingHandlerMapping();
	}
		


}
