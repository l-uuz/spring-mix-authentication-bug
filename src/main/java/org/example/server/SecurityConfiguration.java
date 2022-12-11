package org.example.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails apiuser = User.withUsername("apiuser").password(passwordEncoder().encode("apiuser")).roles("API").build();
        UserDetails webuser = User.withUsername("webuser").password(passwordEncoder().encode("webuser")).roles("WEB").build();
        return new InMemoryUserDetailsManager(apiuser, webuser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // https://docs.spring.io/spring-security/reference/6.0/servlet/architecture.html#servlet-securityfilterchain
    // https://docs.spring.io/spring-security/reference/6.0/servlet/configuration/java.html#_multiple_httpsecurity_instances

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**")
                .csrf().disable()
                .authorizeHttpRequests()
                .anyRequest().hasRole("API").and()
                .httpBasic();
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/resources/**").permitAll()
                .requestMatchers("/login*").permitAll()
                .anyRequest().hasRole("WEB").and()
                .formLogin();
        return http.build();
    }

}
