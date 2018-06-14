package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2RestOperations;

public class GithubOrgsAuthoritiesExtractor implements AuthoritiesExtractor{
	
    private OAuth2RestOperations template;

    GithubOrgsAuthoritiesExtractor(OAuth2RestOperations template){
	this.template= template;
    }

    @Override
    public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
	String url = (String) map.get("organizations_url");
	System.err.println("url:"+url);
	@SuppressWarnings("unchecked")
	    List<Map<String, Object>> orgs = template.getForObject(url, List.class);
	System.err.print("orgs:");
	System.err.println(orgs);
	for(Map<String, Object> org : orgs) {
	    if("simplonco".equals(org.get("login"))) {
		return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER, ROLE_ADMIN");
	    }
	}
	return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
	//	throw new BadCredentialsException("Not in required organization");

    }
}