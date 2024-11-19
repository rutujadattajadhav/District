package com.rutuja.district.repo;


import com.rutuja.district.entity.SequenceEntity;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SequenceRepository extends ReactiveCrudRepository<SequenceEntity,Integer> {


    @Query(value="select value from sequence where name= 'district'")
    public Mono<Integer> selectValue();


    @Modifying
    @Query(value="UPDATE sequence SET value =:sequecnceValue  WHERE name = 'district'")
    public Mono<Integer> updateSequencedistrict(Integer sequecnceValue  );

}
