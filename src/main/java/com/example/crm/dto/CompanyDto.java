package com.example.crm.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "dto for company")
public class CompanyDto {
    private Long id;
    private String name;
    private String description;
    private String industry;
}
