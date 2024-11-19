package com.rutuja.district.model;

import lombok.Data;

@Data
public class DistrictResponce {

    private Integer districtId;
    private String districtName;
    private State state;
    private String action;

}
