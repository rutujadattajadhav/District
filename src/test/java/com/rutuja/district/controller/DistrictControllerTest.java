package com.rutuja.district.controller;


import com.rutuja.district.model.DistrictModel;
import com.rutuja.district.service.DistrictService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DistrictControllerTest {

    @InjectMocks
    private DistrictController districtController;

    @Mock
    private  DistrictService districtService;

//    @Test
//    public void getDistrictByIdSuccess() throws Exception {
//        DistrictModel districtModel  =new DistrictModel();
//        districtModel.setDistrictId(9);
//        districtModel.setDistrictName("Dhule");
//        Mockito.when(districtService.getDistrictById(Mockito.anyInt())).thenReturn(districtModel);
//       DistrictModel districtModel1 =districtController.getDistrictById(9);
//        Assert.assertEquals(districtModel.toString(),districtModel1.toString());
//    }
//    @Test
//    public void getAllDistrictSuccess() throws Exception {
//        List<DistrictModel> districtModels=new ArrayList<>();
//        Mockito.when(districtService.getAlldistrict()).thenReturn(districtModels);
//       List<DistrictModel>  districtModel1 =districtController.getAllDistrict();
//       Assert.assertEquals(districtModels.toString(),districtModel1.toString());
//    }
//
//    @Test
//    public void getDistrict() throws Exception {
//        List<DistrictModel> districtModels=new ArrayList<>();
//        Mockito.when(districtService.getDistricts(Mockito.anyList())).thenReturn(districtModels);
//        List<Integer> listInt=new ArrayList<>();
//        listInt.add(1);
//        listInt.add(2);
//        List<DistrictModel> districtModels1=districtController.getDistricts(listInt);
//        Assert.assertEquals(districtModels.toString(),districtModels1.toString());
//    }
}
