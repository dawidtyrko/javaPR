package com.dogs.projectjava.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecConfig {

        @Bean
        public UserDetailsManager userDetailsManager(DataSource dataSource) {

            return new JdbcUserDetailsManager(dataSource);
        }



        @Bean
        SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.authorizeHttpRequests(config -> config
                            .requestMatchers("/").hasRole("USER")
                            .requestMatchers("/list").hasRole("USER")
                            .requestMatchers("/system/**").hasRole("MANAGER")
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .anyRequest()
                            .authenticated()
                    )
                    .formLogin(form -> form
                            .loginPage("/loginPage")    //create getmapping for this
                            .loginProcessingUrl("/authenticateUser") //no controller needed for this
                            .permitAll()
                    )
                    .logout(LogoutConfigurer::permitAll
                    )
                   .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"));
            return httpSecurity.build();
        }

//    @Bean
//    InMemoryUserDetailsManager userDetailsManager(){
//
//        UserDetails john = User.builder()
//                .username("john")
//                .password("{noop}test123")
//                .roles("USER")
//                .build();
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}test123")
//                .roles("USER","MANAGER")
//                .build();
//        UserDetails susan = User.builder()
//                .username("dave")
//                .password("{noop}test123")
//                .roles("USER","MANAGER","ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(john,mary,susan);
//    }

    }

