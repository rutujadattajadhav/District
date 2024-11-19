package com.rutuja.district.controller;

import com.rutuja.district.model.DistrictModel;
import com.rutuja.district.model.DistrictRequestBean;
import com.rutuja.district.model.DistrictResponce;
import com.rutuja.district.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
@CrossOrigin
@RestController
public class DistrictController {
    @Autowired
    private DistrictService districtService;
    //method= getDistrictById
    //return type = Mono<DistrictModel>
    //params = Integer districtId
    //method call = districtService.getDistrictById(districtId)
    //method = getDistrictById return type = Mono<DistrictModel>
    @GetMapping(value = "districtId/{districtId}")
    public Mono<DistrictModel> getDistrictById(@PathVariable("districtId") Integer districtId) throws Exception {
        return  districtService.getDistrictById(districtId);
    }

    //method= getAllDistrict
    //return type = Flux<DistrictResponce>
    //params = void
    //method call = districtService.getAlldistrict()
    //method = getAlldistrict return type = Flux<DistrictResponce>
    @GetMapping("/getAllDistricts")
    public Flux<DistrictResponce> getAllDistrict() throws Exception {
        return  districtService.getAlldistrict();
    }

    //method= getDistricts
    //return type = Flux<DistrictModel>
    //params = List<Integer> districtIds
    //method call = districtService.getDistricts(districtIds)
    //method = getDistricts return type = Flux<DistrictModel>
    @GetMapping("/getDistricts")
    public Flux<DistrictModel> getDistricts( @RequestBody  List<Integer> districtIds) throws Exception {
        return  districtService.getDistricts(districtIds);
    }

    //method= saveDistrict
    //return type = Mono<String>
    //params = DistrictRequestBean districtRequestBean
    //method call = districtService.saveDistrict(districtRequestBean)
    //method = saveDistrict,         return type = Mono<String>
    @PostMapping("/saveDistrict")
    public Mono<String> saveDistrict(@RequestBody DistrictRequestBean districtRequestBean) throws Exception {
        return  districtService.saveDistrict(districtRequestBean);
    }

    //method= updateDistrict
    //return type = Mono<String>
    //params = DistrictRequestBean districtRequestBean
    //method call = districtService.updateDistrict(districtRequestBean)
    //method = updateDistrict, return type = Mono<String>
    @PutMapping("/updateDistrict")
    public Mono<String> updateDistrict(@RequestBody DistrictRequestBean districtRequestBean) throws Exception {
        return  districtService.updateDistrict(districtRequestBean);
    }

    //method= deleteDistrict
    //return type = Mono<String>
    //params = Integer districtId
    //method call = districtService.deleteDistrict(districtId)
    //method = deleteDistrict, return type = Mono<String>
    @DeleteMapping(value="deleteDistrict/{deleteDistrictById}")
    public Mono<String> deleteDistrict(@PathVariable("deleteDistrictById") Integer districtId) throws Exception {
        return  districtService.deleteDistrict(districtId);
    }

    //method= getDistrictsByStateId
    //return type = Flux<DistrictModel>
    //params = Integer stateId
    //method call = districtService.getDistrictsByStateId(stateId)
    //method = getDistrictsByStateId , return type = Flux<DistrictModel>
    @GetMapping(value="ststeId/{stateId}")
    public Flux<DistrictModel> getDistrictsByStateId(@PathVariable("stateId") Integer stateId) throws Exception {
        return  districtService.getDistrictsByStateId(stateId);
    }
}
