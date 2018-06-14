package com.example.demo;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests()   // On autorise l'entrée de requete sur /**
		.antMatchers("/np/admin**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/np/private**").hasAuthority("ROLE_USER")
		.antMatchers("/", "/login**","/webjars/**", "/error**", "/np/**").permitAll() // Parmi les requête autorisées /, /login**, /error** sont ok pour tous
		.anyRequest().authenticated() // Les autres nécessitent une authentification
		.and().logout().logoutSuccessUrl("/").permitAll();
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}
	
	@Bean
	public OAuth2RestTemplate oauth2RestTemplate(OAuth2ProtectedResourceDetails resource
						     , OAuth2ClientContext context) {
	    return new OAuth2RestTemplate(resource, context);
	}

	@Bean
	public AuthoritiesExtractor authoritiesExtractor(OAuth2RestOperations template) {
	    return new GithubOrgsAuthoritiesExtractor(template);
	}
	
}