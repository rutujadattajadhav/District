package com.rutuja.district.config;

import com.rutuja.district.repo.UserRepository;
import com.rutuja.district.service.DistrictUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Optional;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private DistrictUserDetailsService districtUserDetailsService;

    @Bean
public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
 http.cors(corsSpec -> corsSpec.disable())
         .csrf(csrfSpec -> csrfSpec.disable());
         return http.build();
}
}
