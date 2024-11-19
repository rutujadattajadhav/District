package com.rutuja.district.model;

import lombok.Data;

@Data
public class DistrictRequestBean {
    private Integer districtId;
    private String districtName;
    private State state;
    private String action;
}
