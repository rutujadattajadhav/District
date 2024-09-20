package com.rutuja.district.service;

import com.rutuja.district.model.DistrictModel;
import com.rutuja.district.repo.DistrictRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

@Service
public class DistrictService {

    Logger log= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DistrictRepository districtRepository;

    public DistrictModel getDistrictById(Integer districtId) throws Exception{
        if (districtRepository.findById(districtId).isPresent()) {
            log.debug("district Id is present");
            return districtRepository.findById(districtId).get();

        }
        throw  new Exception("District not found");
    }

    public List<DistrictModel> getAlldistrict() throws Exception{
        List<DistrictModel> listOfDistrict=new ArrayList<>();
       Iterable<DistrictModel> districts= districtRepository.findAll();
       if(districts!=null){
        districts.forEach((district)->{
            listOfDistrict.add(district);});
        return listOfDistrict;
       }
       throw new Exception("Districts is not found");
    }

    public List<DistrictModel> getDistricts(List<Integer> districtIds) throws Exception {
        List<DistrictModel> listOfDistrict=new ArrayList<>();
       Iterable<DistrictModel> districts =districtRepository.findAllById(districtIds);
       if(districts!=null){
           districts.forEach((district)->{
               listOfDistrict.add(district);
           });
           return listOfDistrict;
       }
        throw new Exception("Districts not found");
    }
}
