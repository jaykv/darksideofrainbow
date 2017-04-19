package com.darksideofrainbow.config;

import com.darksideofrainbow.service.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ApplicationUserDetailsService applicationUserDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {

        //  Ignore the resources
        web.ignoring().antMatchers("/css/**", "/img/**", "/js/**");
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {

        // Require all requests to be authorized
        http.authorizeRequests()
                .antMatchers("/", "/about", "/api/**", "/contact-us", "/login").permitAll()
                .antMatchers("/admin/**").authenticated()

                // add a login page
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logout-success")
                .permitAll()
        ;

        // turn off cross-site forgery
        http.headers().frameOptions().disable();
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(applicationUserDetailsService)
                .passwordEncoder(new PlaintextPasswordEncoder());

    }
}
