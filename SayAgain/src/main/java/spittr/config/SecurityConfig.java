package spittr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import net.sf.ehcache.search.expression.And;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("user").password("1234").roles("USER").and().withUser("admin").password("admin").roles("USER", "ADMIN");
	}

	/**
	 * 如果不加formLogin()开启spring security自带的默认的登陆页面的话，那么被拦截的请求将返回403
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin().and()
		.authorizeRequests().antMatchers("/spitter/JackMa").authenticated()
		.antMatchers("/spitter/register").hasRole("ADMIN")
		.anyRequest().permitAll();
		
	}

}
