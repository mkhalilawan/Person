package com.pf.sa.security;
import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages={"com.pf","com.pf.cit"})
@EnableGlobalMethodSecurity(securedEnabled=true)
@EnableTransactionManagement
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String USER_YML_PROPERTIES = "configuration.yml";
	private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

	
	@Autowired
    CustomSuccessHandler customSuccessHandler;
	
	
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
				auth.authenticationProvider(customAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	
		http
	      .csrf().disable()
	      .authorizeRequests().anyRequest().authenticated().and()
	      .formLogin()
	        .loginPage("/login")
	        .successHandler(customSuccessHandler)
	        .permitAll().and()
	      .logout()
	        .permitAll().and().exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler());
		http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll();
 	}
	

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() throws MalformedURLException {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
		// First load the application file from class path
		String ymlPath = USER_YML_PROPERTIES;
		yaml.setResources(new ClassPathResource(ymlPath));
		try {
			propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
			log.info(">>>> Loading yml file from classpath successful");
		} catch (Exception ex) {
			log.error(ex.getMessage() + " support.yml file load failure through classpath");
		}
		return propertySourcesPlaceholderConfigurer;
	}
	
}