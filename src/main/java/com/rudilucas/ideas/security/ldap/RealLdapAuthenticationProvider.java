package com.rudilucas.ideas.security.ldap;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.authentication.LdapAuthenticator;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

public class RealLdapAuthenticationProvider extends LdapAuthenticationProvider {

    public RealLdapAuthenticationProvider(LdapAuthenticator authenticator) {
        super(authenticator);
    }

    public RealLdapAuthenticationProvider(LdapAuthenticator authenticator, LdapAuthoritiesPopulator authoritiesPopulator) {
        super(authenticator, authoritiesPopulator);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication authenticate = super.authenticate(authentication);
        return authenticate;
    }

    @Override
    protected Authentication createSuccessfulAuthentication(UsernamePasswordAuthenticationToken authentication,
            UserDetails user) {
        Authentication createSuccessfulAuthentication = super.createSuccessfulAuthentication(authentication, user);
        return createSuccessfulAuthentication;
    }

}
