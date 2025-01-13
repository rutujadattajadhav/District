package com.rutuja.district.controller;


import com.rutuja.district.model.DistrictModel;
import com.rutuja.district.model.DistrictRequestBean;
import com.rutuja.district.model.DistrictResponce;
import com.rutuja.district.model.State;
import com.rutuja.district.service.DistrictService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DistrictControllerTest {

    @InjectMocks
    private DistrictController districtController;

    @Mock
    private  DistrictService districtService;

    @Test
    public void getDistrictByIdSuccess() throws Exception {

        Mockito.when(districtService.getDistrictById(Mockito.anyInt())).thenReturn(Mono.just(new DistrictModel()));
       Mono<DistrictModel> districtModel1 =districtController.getDistrictById(9);
        Assert.assertEquals(new DistrictModel().toString(),districtModel1.block().toString());
    }
    @Test
    public void getAllDistrictSuccess() throws Exception {
        Mockito.when(districtService.getAlldistrict()).thenReturn(Flux.just(new DistrictResponce()));
       Flux<DistrictResponce>  districtModel1 =districtController.getAllDistrict();
       Assert.assertEquals(Flux.just(new DistrictResponce()).toString(),districtModel1.toString());
    }

    @Test
    public void getDistrict() throws Exception {

        Mockito.when(districtService.getDistricts(Mockito.anyList())).thenReturn(Flux.just(new DistrictModel()));
        List<Integer> listInt=new ArrayList<>();
        listInt.add(1);
        listInt.add(2);
        Flux<DistrictModel> districtModels1=districtController.getDistricts(listInt);
        Assert.assertEquals(Flux.just(new DistrictModel()).toString(),districtModels1.toString());
    }

    @Test
    public void saveDistrictSuccess() throws Exception {
        DistrictRequestBean districtRequestBean = new DistrictRequestBean();
        districtRequestBean.setDistrictId(1);
        districtRequestBean.setDistrictName("Ahmednagar");
        State state = new State();
        state.setStateId(1);
        districtRequestBean.setState(state);

        Mockito.when(districtService.saveDistrict(districtRequestBean)).thenReturn(Mono.just("success"));
        Mono<String> saveDistrict = districtController.saveDistrict(districtRequestBean);
        Assert.assertEquals("success", saveDistrict.block());
    }

    @Test
    public void updateDistrictSuccess() throws Exception {
        DistrictRequestBean districtRequestBean = new DistrictRequestBean();
        districtRequestBean.setDistrictId(1);
        districtRequestBean.setDistrictName("Ahmednagar");
        State state = new State();
        state.setStateId(1);
        districtRequestBean.setState(state);

        Mockito.when(districtService.updateDistrict(Mockito.any())).thenReturn(Mono.just("success"));
        Mono<String> updateDistrict = districtController.updateDistrict(districtRequestBean);
        Assert.assertEquals("success", updateDistrict.block());
    }

    @Test
    public void deleteDistrict() throws Exception {
        Mockito.when(districtService.deleteDistrict(Mockito.anyInt())).thenReturn(Mono.just("success"));
        Mono<String> deleteDistrict = districtController.deleteDistrict(1);
        Assert.assertEquals("success", deleteDistrict.block());
    }

    @Test
    public void getDistrictsByStateId() throws Exception {
        Mockito.when(districtService.getDistrictsByStateId(Mockito.anyInt())).thenReturn(Flux.just(new DistrictModel()));
      Flux<DistrictModel> districtModel  =districtController.getDistrictsByStateId(1);
        Assert.assertEquals(Flux.just(new DistrictModel()).toString(),districtModel.toString());
    }
}
