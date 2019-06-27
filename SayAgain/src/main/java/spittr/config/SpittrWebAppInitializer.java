package spittr.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
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
	
	

}
