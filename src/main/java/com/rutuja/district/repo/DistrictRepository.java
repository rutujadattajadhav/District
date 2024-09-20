package com.rutuja.district.repo;

import com.rutuja.district.model.DistrictModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends CrudRepository<DistrictModel,Integer > {
}
