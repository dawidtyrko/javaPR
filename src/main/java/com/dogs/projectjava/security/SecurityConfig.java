package com.dogs.projectjava.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //query to retrieve user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id,pw,active from members where user_id=?");

        //query to retrieve authorities/roles
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id=?");
        return jdbcUserDetailsManager;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(config -> config
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated())
                .formLogin(form -> form
                        .loginPage("/loginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
                ).logout(logout -> logout.permitAll()
                )
                .exceptionHandling(configurer->configurer.accessDeniedPage("/access-denied"));
        return httpSecurity.build();
    }



    /*
    @Bean
    InMemoryUserDetailsManager userDetailsManager(){

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();
        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER")
                .build();
        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(john,mary,susan);
    }
    */
}