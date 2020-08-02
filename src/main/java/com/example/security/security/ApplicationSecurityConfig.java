package com.example.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.security.security.ApplicationUserRole.ADMIN;
import static com.example.security.security.ApplicationUserRole.STUDENT;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }                                                                       // Enable Password encoder from PasswordConfig(BCrypt)

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api", "/api/users")
                .permitAll()                                                //grants access to routes "/api" and "/api/user" without authentication
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {                     //Service how you retrieve your users from the database
        UserDetails bobUser = User.builder()                                //For now we create a new user instead of retrieving from database
                .username("bob")
                .password(passwordEncoder.encode("password1"))  //Encode password
                .roles(ADMIN.name())                     //ROLE_ADMIN authentication role
                .build();

        UserDetails robertUser = User.builder()                             //For now we create a new user instead of retrieving from database
                .username("robert")
                .password(passwordEncoder.encode("password2")) //Encode password
                .roles(STUDENT.name())                                      //ROLE_STUDENT if there's no static import from ApplicationUserRole, you use ApplicationUserRole.ADMIN.name()
                .build();
        return new InMemoryUserDetailsManager(bobUser, robertUser);
    }
}
