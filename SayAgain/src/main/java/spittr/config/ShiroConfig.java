package spittr.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;

import spittr.repository.SpitterRepository;
import spittr.shiro.SpittrAuthorizingRealm;

@Configuration
public class ShiroConfig implements WebApplicationInitializer {

	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		Dynamic shiroFilter = servletContext.addFilter("myShiroFilter", DelegatingFilterProxy.class);

		shiroFilter.setInitParameter("targetFilterLifecycle", "true");
		shiroFilter.addMappingForUrlPatterns(null, true, "/*");
		
	

	}

	@Bean(name = "myShiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultSecurityManager securityManager) {
		
		ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

		factoryBean.setSecurityManager(securityManager);
		factoryBean.setLoginUrl("/login");
		factoryBean.setUnauthorizedUrl("/login");

		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>(); // LinkedHashMap
		filterChainDefinitionMap.put("/login", "anon"); // 登录接口配置游客权限
		filterChainDefinitionMap.put("/logout", "logout"); // 登出接口……
		filterChainDefinitionMap.put("/*", "authc"); // 全部接口配置都需要权限

		factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return factoryBean;
	}

	@Bean
	public DefaultWebSecurityManager getDefaultSecurityManager(Realm realm) {

		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(realm);
		return securityManager;

	}
	
	@Bean
	public Realm getRealm() {
		
		return new SpittrAuthorizingRealm();
	}
	
	@Bean  
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {  
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();  
        advisorAutoProxyCreator.setProxyTargetClass(true);  
        return advisorAutoProxyCreator;  
    }  
      
    @Bean  
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {  
    	AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    	advisor.setSecurityManager(securityManager);
    	
    	return advisor;
    }  
}
