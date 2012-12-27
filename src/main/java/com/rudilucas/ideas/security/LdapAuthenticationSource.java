package com.rudilucas.ideas.security;

import org.springframework.security.ldap.authentication.SpringSecurityAuthenticationSource;

public class LdapAuthenticationSource extends SpringSecurityAuthenticationSource {

	@Override
	public String getCredentials() {
		return super.getCredentials();
	}
	@Override
	public String getPrincipal() {
		return super.getPrincipal();
	}
}
