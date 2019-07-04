package spittr.config;

import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	/**
	 * 用StandardServletMultipartResolver的话需要这个
	 */
	@Override
	protected void customizeRegistration(Dynamic registration) {

		/**
		 * 限制文件的大小不超过2MB，整个请求不超过4MB，而且所有的文件都要 写到磁盘中。
		 */
		registration.setMultipartConfig(new MultipartConfigElement("/uploads", 2097152, 4194304, 0));
	}

//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		
//		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter",
//				CharacterEncodingFilter.class);
//		encodingFilter.setInitParameter("encodingFilter", String.valueOf(StandardCharsets.UTF_8));
//		encodingFilter.setInitParameter("forceEncoding", "true");
//		encodingFilter.addMappingForUrlPatterns(null, false, "/*");
//		
//		
//		servletContext.addFilter("encodingFilter2", EncodingFilter.class);
//		
//		
//		super.onStartup(servletContext);
//	}

	@Override
	protected Filter[] getServletFilters() {
		
		
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		
		return new Filter[] { characterEncodingFilter };
	}



}
