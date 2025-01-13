package com.rutuja.district.controller.service;

import com.rutuja.district.model.DistrictModel;
import com.rutuja.district.model.DistrictResponce;
import com.rutuja.district.repo.DistrictRepository;
import com.rutuja.district.service.DistrictService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;




    @Test
    public void getDistrictByIdSuccess() throws Exception {
       Mockito.when(districtRepository.findByDistrictId(Mockito.anyInt())).thenReturn(Mono.just(new DistrictModel())); ;
        Mono<DistrictModel> districtModelMono=districtService.getDistrictById(1);
        Assert.assertEquals(new DistrictModel().toString(),districtModelMono.block().toString());
    }

    @Test(expected = Exception.class)
    public void getDistrictByIdError() throws Exception {
        Mockito.when(districtRepository.findByDistrictId(Mockito.anyInt())).thenReturn(Mono.error(new Exception("Unable to fetch district details")));
        districtService.getDistrictById(1).block();
    }

//    @Test
//    public void getAllDistrictSuccess() throws Exception {
//        Mockito.when(districtRepository.findAll()).thenReturn(Flux.just(new DistrictModel()));
//        Mockito.when(webClientBuilder.build()).thenReturn(webClient);
//        Mockito.when(webClient.get()).thenReturn();
//        Flux<DistrictResponce> districtResponce=districtService.getAlldistrict();
//        Assert.assertEquals(Flux.just(new DistrictResponce()).blockLast(),districtResponce.blockLast());
//    }
}

