package com.example.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.security.security.ApplicationUserRole.*;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }                                                                       // Enable Password encoder from PasswordConfig(BCrypt)

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api", "/api/users").permitAll()                 //grants access to routes "/api" and "/api/user" without authentication
                .antMatchers("/api/**").hasRole(STUDENT.name())                 //grants acces to routes"/api/.. for students, not for admin
//                .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())                      commented antmatchers out because use of GlobalMethodSecurity in the endpoints, enabled in this class
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
//                .roles(ADMIN.name())                     //ROLE_ADMIN authentication role
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails robUser = User.builder()                                //For now we create a new user instead of retrieving from database
                .username("rob")
                .password(passwordEncoder.encode("password2"))  //Encode password
//                .roles(ADMINTRAINEE.name())                     //ROLE_ADMIN authentication role
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
                .build();

        UserDetails robertUser = User.builder()                             //For now we create a new user instead of retrieving from database
                .username("robert")
                .password(passwordEncoder.encode("password3")) //Encode password
//                .roles(STUDENT.name())                                      //ROLE_STUDENT if there's no static import from ApplicationUserRole, you use ApplicationUserRole.ADMIN.name()
                .authorities(STUDENT.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(bobUser, robertUser, robUser);
    }
}
