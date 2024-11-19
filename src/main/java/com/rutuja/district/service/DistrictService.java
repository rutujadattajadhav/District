package com.rutuja.district.service;

import com.rutuja.district.model.DistrictModel;
import com.rutuja.district.model.DistrictRequestBean;
import com.rutuja.district.model.DistrictResponce;
import com.rutuja.district.model.State;
import com.rutuja.district.repo.DistrictRepository;
import com.rutuja.district.repo.SequenceRepository;
import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

@Service
public class DistrictService {

    Logger log= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private R2dbcEntityTemplate r2dbcEntityTemplate;

    @Autowired
    @LoadBalanced
    private WebClient.Builder webClientBuilder;

    @Value("${server.servlet.context-path-state}")
    private String stateContextName;


    @Autowired
    private SequenceRepository sequenceRepository;

   //method=getDistrictById
    //path=src/main/java/com/rutuja/district/service/DistrictService.java
    //input=districtId
    //output=Mono<DistrictModel>
    //calling the findByDistrictId method of districtRepository to get the district by districtId
    //if findByDistrictId method returns empty then call switchIfEmpty() and return error message,
    //otherwise call flarMap() and return the Mono<DistrictModel>
    //if any error is occour then call onErrorResume() and return the error msg
    public Mono<DistrictModel> getDistrictById(Integer districtId) throws Exception{
        Mono<DistrictModel> districtModelMono=districtRepository.findByDistrictId(districtId);
        return  districtModelMono.switchIfEmpty(Mono.error(new Exception("District not found")))
                .onErrorResume(error -> {
                    log.error("Error during district retrieval: ", error);
                    return Mono.error(new RuntimeException("Unable to fetch district details"));
                });

    }

    //method=getAlldistrict
    //path=src/main/java/com/rutuja/district/service/DistrictService.java
    //input=void
    //output=Flux<DistrictResponce>
    //calling the findAllByAction method of districtRepository to get all the districts
    //if findAllByAction method returns empty then call switchIfEmpty() and return error message,
    //otherWise call flatMap() and return the Flux of DistrictResponce
    //if any error occurs then call onErrorResume() and return error message
    //call stateAPI to get the state details
    //by using webClientBuilder.build() method
    //then call get() method
    // then call uri() method on the WebClient.RequestHeadersUriSpec object to set the uri
    //then call headers() method and pass the headers
    //then call the retrieve() method on the WebClient.RequestHeadersSpec object to get the WebClient.ResponseSpec object
    //then call the bodyToMono() method on the WebClient.ResponseSpec object to get the mono of state
    //then call onErrorResume() method to handle the error
    //then call map() method on the Mono<State> object to get the Mono<DistrictResponce>
    //then call switchIfEmpty() method to handle the empty response
    //then call onErrorResume() method to handle the error
    //then call log() method to log the error
    //then call error() method to return the error message
    //then call onerrorResume() method to handle the error
    public Flux<DistrictResponce> getAlldistrict() throws Exception{
        Flux<DistrictModel> districtModelFlux= districtRepository.findAllByAction();
              return districtModelFlux
                      .flatMap(districtModel->{
                          DistrictResponce districtResponce   =new DistrictResponce();
                          districtResponce.setDistrictName(districtModel.getDistrictName());
                          districtResponce.setAction(districtModel.getAction());
                          districtResponce.setDistrictId(districtModel.getDistrictId());
                          districtResponce.setState(new State());

                          return webClientBuilder.build()
                                  .get()
                                  .uri("http://STATE" + stateContextName +"/stateById/" + districtModel.getStateId())
                                  .headers(headers ->headers.setBasicAuth("departMentuser", "department"))
                                  .retrieve()
                                  .bodyToMono(State.class)
                                  .onErrorResume(e -> {
                                      log.error("Failed to fetch department", e);
                                      return Mono.empty();
                                  })
                          .map(state -> {


                              districtResponce.getState().setStateName(state.getStateName());
                              return districtResponce;
                          });

                      })
                      .switchIfEmpty(Flux.error(new Exception("States not found")))
                .onErrorResume(error -> {
                    log.error("Error during state retrieval: ", error);
                    return Flux.error(new RuntimeException("Unable to fetch state details"));
                });
    }

    //method=getDistricts
    //path=src/main/java/com/rutuja/district/service/DistrictService.java
    //input=List<Integer> districtIds
    //output=Flux<DistrictModel>
    //calling the findAllByAction method of districtRepository to get the districts by districtIds
    //if findAllByAction method returns empty then call switchIfEmpty() and return error message,
    //otherwise call flatMap() and return the Flux of DistrictModel
    //if any error occurs then call onErrorResume() and return error message
    public Flux<DistrictModel> getDistricts(List<Integer> districtIds) throws Exception {
      return  districtRepository.findAllByAction(districtIds)
                .flatMap(state -> Mono.just(state))
                .switchIfEmpty(Flux.error(new Exception("States not found")))
                .onErrorResume(error -> {
                    log.error("Error during state retrieval: ", error);
                    return Flux.error(new RuntimeException("Unable to fetch state details"));
                });
    }

//method=saveDistrict
//input=DistrictRequestBean
 //output=Mono<String>
//calling the selectValue method of sequenceRepository to get the sequence value
//if selectValue method returns empty then call switchIfEmpty() and return error message,
//otherwise call flatMap() and set the districtId in districtModel
//then call findByDistrictName method of districtRepository to get the district by districtName
//if findByDistrictName method returns empty then call switchIfEmpty() and return error message,
//otherwise call defer() method and return the Mono of districtModel
    //and call insert() method on the r2dbcEntityTemplate object to insert the districtModel
    //if insert method returns empty then call switchIfEmpty() and return error message,
    //otherwise call flatMap() and return the sequenceRepository of updateSequencedistrict
    //if updateSequencedistrict method returns empty then call switchIfEmpty() and return error message,
    //otherwise call flatMap() and return the success message
    //if any error occurs then call onErrorResume() and return error message
    public Mono<String> saveDistrict(DistrictRequestBean districtRequestBean){
        DistrictModel districtModel = new DistrictModel();
        districtModel.setDistrictName(districtRequestBean.getDistrictName());
        districtModel.setStateId(districtRequestBean.getState().getStateId());
        districtModel.setAction("active");
        return sequenceRepository.selectValue()
                .switchIfEmpty(Mono.error(new Exception("Not Found")))
                .flatMap(sequenceValue -> {
                    districtModel.setDistrictId(sequenceValue);
                    return districtRepository.findByDistrictName(districtModel.getDistrictName())
                            .flatMap(exitstate -> Mono.just("District  already exists"))
                            .switchIfEmpty(Mono.defer(() -> {
                                return r2dbcEntityTemplate.insert(districtModel)
                                        .switchIfEmpty(Mono.error(new Exception("District  not saved")))
                                        .flatMap(state -> {
                                            return sequenceRepository.updateSequencedistrict(districtModel.getDistrictId()+1)
                                                  .switchIfEmpty(Mono.error(new Exception("not updated")))
                                                    .flatMap(integer -> Mono.just("Successfully save District"))
                                                    .onErrorResume(error -> {
                                                        log.error("Error during sequence updation: ", error);
                                                        return Mono.just("Please try after some time or contact system admin");
                                                    });
                                        });
                            }));
                })
                .onErrorResume(error -> {
                    log.error("Error during insert updation: ", error);
                    return Mono.just("Error during insert updation: " + error.getMessage());
                });
    }

//method=updateDistrict
    //input=districtRequestBean
    //output=Mono<String>
    //create the districtModel object
    //set the districtId, districtName, stateId, action in districtModel object
    //calling the findById method of districtRepository to get the district by districtId
    //if  findById method returns empty then call switchIfEmpty() and return error message,
    //otherwise call flatMap() and update the districtModel
    //if update method returns empty then call switchIfEmpty() and return error message,
    public Mono<String> updateDistrict(DistrictRequestBean districtRequestBean) throws Exception {
        DistrictModel districtModel = new DistrictModel();
        districtModel.setDistrictId(districtRequestBean.getDistrictId());
        districtModel.setDistrictName(districtRequestBean.getDistrictName());
        districtModel.setStateId(districtRequestBean.getState().getStateId());
        districtModel.setAction("active");
       return districtRepository.findById(districtModel.getDistrictId())
                .flatMap(exitUser->{
                    return r2dbcEntityTemplate.update(districtModel)
                            .flatMap(user -> Mono.just("Success"))
                    .switchIfEmpty(Mono.error(new Exception("not updated")));
                })
                .switchIfEmpty(Mono.error(new Exception("District not found")))
                .onErrorResume(error->{
                    log.error("Error during state update: ", error);
                    return Mono.just("Error");
                });
    }

    //method=deleteDistrict
    //input=districtId
    //output=Mono<String>
    //calling the findById method of districtRepository to get the district by districtId
    //if findById method returns empty then call switchIfEmpty() and return error message,
    //otherwise call flatMap() and call update method of districtRepository
    //if update method returns empty then call switchIfEmpty() and return error message,
    //otherwise call flatMap() and return the success message
    public Mono<String> deleteDistrict(Integer districtId){
        Mono<DistrictModel> districtModelMono= districtRepository.findById(districtId);
        return districtModelMono
                .flatMap(exituser->{
                    return  districtRepository.updateByAction(districtId)
                            .flatMap(user->Mono.just(" deleted"));
                })
                .switchIfEmpty(Mono.just("not found"))
                .onErrorResume(error->{
                    log.error("Error during district delete: ",error);
                    return Mono.just("error");
                });
    }

    //method=getDistrictsByStateId
    //input=stateId
    //output=Flux<DistrictModel>
    //calling the findByStateId method of districtRepository to get the districts by stateId
    //if findByStateId method returns empty then call switchIfEmpty() and return error message,
    //otherWise call flatMap() and return the Flux of DistrictModel
    //if any error occurs then call onErrorResume() and return error message
    public Flux<DistrictModel> getDistrictsByStateId(Integer stateId) {
        return districtRepository.findByStateId(stateId)
                .flatMap(state->Flux.just(state))
                .switchIfEmpty(Flux.error(new Exception("Districts  not foun d")))
                .onErrorResume(error -> {
                    log.error("Error during District retrieval: ", error);
                    return Flux.error(new RuntimeException("Unable to fetch District details"));
                });
    }
}
