package org.example.it_support_ticket_systemcontext.Authentication.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {


    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/api/auth/**")
                                .permitAll()
                                .requestMatchers(POST, "/api/tickets").hasAnyRole("EMPLOYEE", "IT_SUPPORT")
                                .requestMatchers(GET, "/api/tickets").hasAnyRole("EMPLOYEE", "IT_SUPPORT")
                                .requestMatchers(PUT, "/api/tickets/{ticketId}/status").hasRole("IT_SUPPORT")
                                .requestMatchers(POST, "/api/tickets/{ticketId}/comments").hasRole("IT_SUPPORT")
                                .requestMatchers(GET, "/api/tickets/{ticketId}/comments").hasRole( "IT_SUPPORT")
                                .requestMatchers(GET, "/api/tickets/{ticketId}").hasAnyRole("EMPLOYEE", "IT_SUPPORT")
                                .requestMatchers(GET, "/api/tickets/status").hasRole("IT_SUPPORT")
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

}