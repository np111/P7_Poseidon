package com.poseidon.app.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

@UtilityClass
public class AuthMock {
    @Retention(RetentionPolicy.RUNTIME)
    @WithSecurityContext(factory = WithUserAuthSecurityContextFactory.class)
    public @interface WithUserAuth {
    }

    private static class WithUserAuthSecurityContextFactory implements WithSecurityContextFactory<WithUserAuth> {
        @Override
        public SecurityContext createSecurityContext(WithUserAuth annotation) {
            SecurityContext ctx = SecurityContextHolder.createEmptyContext();
            UserDetails user = new org.springframework.security.core.userdetails.User(
                    "test-username",
                    "test-password",
                    Collections.singletonList(new SimpleGrantedAuthority("USER")));
            ctx.setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
            return ctx;
        }
    }
}
