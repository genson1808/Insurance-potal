package com.gen.com.Insurance_portal.models.responseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarModel {

    private String name;

    private String code;

    private Integer percent;

    private String carBrandCode;

    private String carBrandName;
}
