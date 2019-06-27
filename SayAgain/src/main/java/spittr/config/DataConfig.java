package spittr.config;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;


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
	
	@Bean
	public LocalSessionFactoryBean sessionFactoryBean(DataSource dataSource) {
		
		LocalSessionFactoryBean sb = new LocalSessionFactoryBean();
		
		sb.setDataSource(dataSource);
		sb.setPackagesToScan(new String[] {"spittr.pojo"});
		Properties properties = new Properties();
		
		properties.setProperty("dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		properties.setProperty("hbm2ddl.auto", "true");
		properties.setProperty("show_sql", "true");
		properties.setProperty("connection.autocommit", "true");
		properties.setProperty("connection.release_mode", "auto");
		
		
		sb.setHibernateProperties(properties);
		
		return sb;
		/**
		 * 
		 *  <prop key="hibernate.dialect">org.hibernate.dialect.MySQL5InnoDBDialect</prop>
                <prop key="hibernate.show_sql">true</prop>
                <prop key="hibernate.format_sql">true</prop>
                <prop key="hibernate.hbm2ddl.auto">update</prop>
		 */
		
		
	}
	
	@Bean
	public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
		
		HibernateTransactionManager tm = new HibernateTransactionManager(sessionFactory);
		
		return tm;
	}
	

}
