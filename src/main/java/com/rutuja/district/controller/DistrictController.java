package com.rutuja.district.controller;

import com.rutuja.district.model.DistrictModel;
import com.rutuja.district.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
@CrossOrigin
@RestController
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @GetMapping(value = "/{districtId}")
    public DistrictModel getDistrictById(@PathVariable("districtId") Integer districtId) throws Exception {
        return  districtService.getDistrictById(districtId);
    }

    @GetMapping("/getAllDistricts")
    public List<DistrictModel> getAllDistrict() throws Exception {
        return  districtService.getAlldistrict();
    }

    @GetMapping("/getDistricts")
    public List<DistrictModel> getDistricts( @RequestBody  List<Integer> districtIds) throws Exception {
        return  districtService.getDistricts(districtIds);
    }

}
