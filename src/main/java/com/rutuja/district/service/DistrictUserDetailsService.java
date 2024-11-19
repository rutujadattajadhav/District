package com.rutuja.district.service;

import com.rutuja.district.entity.UserEntity;
import com.rutuja.district.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
@Service
@EnableWebFluxSecurity
public class DistrictUserDetailsService implements ReactiveUserDetailsService {
    @Autowired
    private UserRepository userRepository;

    //method = findByUsername
    //return type = Mono<UserDetails>
    //params = String username
    //method call = userRepository.findById(username).map(UserEntity::new)
    //method = findById return type = Optional<UserEntity>
    //method = map return type = <U> Optional<U> return modified object like object UserDetails
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username).map(UserEntity::new);
    };
}
