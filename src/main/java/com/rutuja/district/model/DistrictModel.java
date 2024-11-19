package com.rutuja.district.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "district")
@Data
public class DistrictModel {
    @Id
    @Column(value="districtId")
    private Integer districtId;

    @Column(value="districtName")
    private String districtName;

    @Column(value="stateId")
    private Integer stateId;

    @Column(value="action")
    private String action;
}
