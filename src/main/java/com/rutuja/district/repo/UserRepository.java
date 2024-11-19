package com.rutuja.district.repo;

import com.rutuja.district.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity,String> {
    public abstract Mono<UserEntity> findByUsername(String username);
}
