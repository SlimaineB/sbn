package com.sbn.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.notify.LoggingNotifier;

@EnableAdminServer
@SpringBootApplication
@EnableDiscoveryClient
public class AdminApplicationStarter {

	
    private final String adminContextPath;

    public AdminApplicationStarter(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }
    
	public static void main(String[] args) {
		SpringApplication.run(AdminApplicationStarter.class, args);
	}

	
	
    @Bean
    @Profile("insecure")
    public SecurityWebFilterChain securityWebFilterChainPermitAll(ServerHttpSecurity http) {
        return http.authorizeExchange().anyExchange().permitAll()//
                   .and().csrf().disable()//
                   .build();
    }
	
    
    @Bean
    @Profile("secure")
    public SecurityWebFilterChain securityWebFilterChainSecure(ServerHttpSecurity http) {
        // @formatter:off
       return http.authorizeExchange()
                .pathMatchers(adminContextPath + "/assets/**").permitAll()
                .pathMatchers(adminContextPath + "/login").permitAll()
                .pathMatchers(adminContextPath + "/actuator/**").permitAll()
                .anyExchange().authenticated()
                .and()
            .formLogin().loginPage(adminContextPath + "/login").and()
            .logout().logoutUrl(adminContextPath + "/logout").and()
            .httpBasic().and()
            .csrf().disable()
            .build();
        // @formatter:on
        
     /*  return  http.authorizeExchange().anyExchange().permitAll()  
        .and().csrf().disable().build();*/
    }
    
    @Bean
    public LoggingNotifier loggerNotifier(InstanceRepository repository) {
        return new LoggingNotifier(repository);
    }
    
	/*
	 * @Configuration public static class SecurityPermitAllConfig extends
	 * WebSecurityConfigurerAdapter {
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.authorizeRequests().anyRequest().permitAll() .and().csrf().disable(); }
	 * }
	 */

	/*
	 * @Configuration public static class SecuritySecureConfig extends
	 * WebSecurityConfigurerAdapter { private final String adminContextPath;
	 * 
	 * public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
	 * this.adminContextPath = adminServerProperties.getContextPath(); }
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * // @formatter:off SavedRequestAwareAuthenticationSuccessHandler
	 * successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
	 * successHandler.setTargetUrlParameter("redirectTo");
	 * 
	 * http.authorizeRequests() .antMatchers(adminContextPath +
	 * "/assets/**").permitAll() .antMatchers(adminContextPath +
	 * "/login").permitAll() .anyRequest().authenticated() .and()
	 * .formLogin().loginPage(adminContextPath +
	 * "/login").successHandler(successHandler).and()
	 * .logout().logoutUrl(adminContextPath + "/logout").and() .httpBasic().and()
	 * .csrf().disable(); // @formatter:on } }
	 */
}
