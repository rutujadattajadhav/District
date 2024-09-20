package com.rutuja.district.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "district")
@Data
public class DistrictModel {
    @Id
    @Column(name="districtId")
    private Integer districtId;

    @Column(name="districtName")
    private String districtName;
}
