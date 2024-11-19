package com.rutuja.district.repo;

import com.rutuja.district.model.DistrictModel;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface DistrictRepository extends ReactiveCrudRepository<DistrictModel,Integer > {

    public Mono<DistrictModel> findByDistrictName(String districtName);

    @Query("select * from district where stateId = :stateId")
    public Flux<DistrictModel> findByStateId(Integer stateId);

   @Modifying
    @Query("update district set action = 'delete' where districtId = :districtId")
    public Mono<Integer> updateByAction(Integer districtId);

    @Query("SELECT * FROM district WHERE action = 'active'")
    public Flux<DistrictModel> findAllByAction();

    @Query("SELECT * FROM district WHERE action = 'active' AND districtId IN (:districtId)")
    public Flux<DistrictModel> findAllByAction(List<Integer> districtId);

    @Query("SELECT * FROM district WHERE action = 'active' AND districtId = :districtId")
    public Mono<DistrictModel> findByDistrictId(Integer districtId);

}
