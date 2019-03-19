package spittr.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;

import ch.qos.logback.ext.spring.web.LogbackConfigListener;

public class LogbackListener implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		System.out.println("Say Again on startup----------");

		/**
		 * logback初始化时，默认会去classpath下依次加载如下配置文件(logback.groovy、logback-test.xml、logback.xml)，
		 * 当找不到配置文件时logback将为rootLogger 添加一个 ConsoleAppender ， 用于将日志输出到控制台。
		 */
		servletContext.addListener(LogbackConfigListener.class);
		
		servletContext.setInitParameter("logbackConfigLocation", "classpath:logbackConfig/logback.xml");
		
		
	}

}
