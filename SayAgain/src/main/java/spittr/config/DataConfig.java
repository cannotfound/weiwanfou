package spittr.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;

@Configuration
public class DataConfig {
	
	/*
	@Bean
	public DataSource dataSource() {
		
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/may");
		ds.setUsername("root");
		ds.setPassword("123456");
		ds.setMaxActive(10);
		
		return ds;
	}
	*/
	
	
	@Bean
	public JndiObjectFactoryBean jndiObjectFactoryBean() throws NamingException {
		
		JndiObjectFactoryBean jb = new JndiObjectFactoryBean();
		jb.setJndiName("mayDS");
		jb.setResourceRef(true);
		jb.setProxyInterface(javax.sql.DataSource.class);
		
		return jb;
		
		/*Context initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/mayDS");
		return ds;*/
		
		//return (DataSource) jb.getObject();
		
	}

	
	@Bean
	public JdbcOperations jdbcTemplate(DataSource ds) {
		return new JdbcTemplate(ds);
	}
	
	

}
