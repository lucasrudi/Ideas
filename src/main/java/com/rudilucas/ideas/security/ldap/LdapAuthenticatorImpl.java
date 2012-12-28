package com.rudilucas.ideas.security.ldap;

import javax.naming.ldap.InitialLdapContext;

import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.LdapAuthenticator;

import com.rudilucas.ideas.model.User;

public class LdapAuthenticatorImpl implements LdapAuthenticator {
    private DefaultSpringSecurityContextSource contextFactory;
    private String principalPrefix;
    private User user;

    @Override
    public DirContextOperations authenticate(Authentication authentication) {
        // Grab the username and password out of the authentication object.
        final String name = authentication.getName();
        final String principal = this.principalPrefix + name;
        String password = "";
        if (authentication.getCredentials() != null) {
            password = authentication.getCredentials().toString();
        }
        if (!("".equals(principal.trim())) && !("".equals(password.trim()))) {
            final InitialLdapContext ldapContext = (InitialLdapContext) this.contextFactory.getContext(principal,
                    password);
            // We need to pass the context back out, so that the auth provider
            // can add it to the Authentication object.
            final DirContextOperations authAdapter = new DirContextAdapter();
            authAdapter.addAttributeValue("ldapContext", ldapContext);
            this.user.setName(name);
            return authAdapter;
        } else {
            throw new BadCredentialsException("Blank username and/or password!");
        }
    }

    public void setContextFactory(DefaultSpringSecurityContextSource contextFactory) {
        this.contextFactory = contextFactory;
    }

    public void setPrincipalPrefix(String principalPrefix) {
        this.principalPrefix = principalPrefix;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
