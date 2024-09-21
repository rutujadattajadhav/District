package com.rutuja.district.controller.service;

import com.rutuja.district.model.DistrictModel;
import com.rutuja.district.repo.DistrictRepository;
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
import java.util.Optional;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DistrictServiceTest {

    @InjectMocks
    private DistrictService districtService;
    @Mock
    private DistrictRepository districtRepository;

    @Mock
    private Optional<DistrictModel> optional;


    @Test
    public void getDistrictByIdSuccess() throws Exception {
        DistrictModel districtModel1=new DistrictModel();
        Optional<DistrictModel> districtModel2=Optional.of(districtModel1);
        Mockito.when(districtRepository.findById(Mockito.anyInt())).thenReturn(districtModel2);
        Mockito.when(optional.get()).thenReturn(new DistrictModel());
        DistrictModel districtModel=districtService.getDistrictById(1);
        Assert.assertEquals(new DistrictModel().toString(),districtModel.toString());

    }

    @Test(expected = Exception.class)
    public void getDistrictByIdException() throws Exception {
        DistrictModel districtModels=districtService.getDistrictById(37);
    }

    @Test
    public void getAllDistrictSuccess() throws Exception {
        List<DistrictModel> listdistrictModels=new ArrayList<>();
       DistrictModel districtModel= new DistrictModel();
        districtModel.setDistrictId(1);
        districtModel.setDistrictName("Ahmednagar");
        districtModel.setDistrictId(9);
        districtModel.setDistrictName("Dhule");
        listdistrictModels.add(districtModel);
       Mockito.when(districtRepository.findAll()).thenReturn(listdistrictModels);
        List<DistrictModel> districtModelsl=districtService.getAlldistrict();
       Assert.assertEquals(listdistrictModels.toString(),districtModelsl.toString());
    }

    @Test(expected = Exception.class)
    public void getAllDistrictException() throws Exception {
        Mockito.when(districtRepository.findAll()).thenReturn(null);
        List<DistrictModel> districtModelsl=districtService.getAlldistrict();

    }

    @Test
    public void getDistrict() throws Exception {
        List<DistrictModel> listdistrictModels=new ArrayList<>();
        DistrictModel districtModel= new DistrictModel();
//        districtModel.setDistrictId(1);
//        districtModel.setDistrictName("Ahmednagar");
//        districtModel.setDistrictId(9);
//        districtModel.setDistrictName("Dhule");
//        listdistrictModels.add(districtModel);
        Mockito.when(districtRepository.findAll()).thenReturn(listdistrictModels);
        List<Integer> integerList=new ArrayList<>();
        integerList.add(1);
        integerList.add(9);
        List<DistrictModel> districtModels=districtService.getDistricts(integerList);
        Assert.assertEquals(listdistrictModels.toString(),districtModels.toString());
    }

    @Test(expected = Exception.class)
    public void getDistrictException() throws Exception {
        Mockito.when(districtRepository.findAllById(Mockito.anyIterable())).thenReturn(null);
        List<Integer> integerList=new ArrayList<>();
        List<DistrictModel> districtModelsl=districtService.getDistricts(integerList);

    }

}
