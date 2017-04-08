package com.darksideofrainbow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {

        //  Ignore the resources
        web.ignoring().antMatchers("/css/**", "/img/**", "/js/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        // Require all requests to be authorized
        http.authorizeRequests()

                .antMatchers("/", "/about", "/contact-us").permitAll()
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
    }

}
