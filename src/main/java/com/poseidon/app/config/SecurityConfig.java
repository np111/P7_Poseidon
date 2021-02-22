package com.poseidon.app.config;

import com.poseidon.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired(required = false)
    private CsrfTokenRepository csrfTokenRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**", "/static/**", "/favicon.ico").permitAll()
                .antMatchers("/", "/login", "/app/error", "/app-logout", "/user/**").permitAll()
                .antMatchers("/bidList/**", "/curvePoint/**", "/rating/**", "/ruleName/**", "/trade/**").hasAnyAuthority("ADMIN", "USER")
                .anyRequest().denyAll()
                .and().formLogin().defaultSuccessUrl("/bidList/list")
                .and().logout().logoutUrl("/app-logout").logoutSuccessUrl("/")
                .and().exceptionHandling().accessDeniedPage("/app/error");
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(3);
        http.csrf().csrfTokenRepository(csrfTokenRepository == null ? new CookieCsrfTokenRepository() : csrfTokenRepository);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}
