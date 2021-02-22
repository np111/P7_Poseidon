package com.poseidon.app.util;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@TestConfiguration
public class TestConfig {
    @Primary
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2B, 4);
    }

    @Primary
    @Bean
    public CsrfTokenRepository getCsrfTokenRepository() {
        return new CsrfTokenTestRepository();
    }
}
