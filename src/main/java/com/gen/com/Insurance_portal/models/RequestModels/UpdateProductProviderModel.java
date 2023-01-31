package com.gen.com.Insurance_portal.models.RequestModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductProviderModel {
    private String name;

    private String email;

    private String phoneNumber;

    private String introductionContent;

    private String appellation;

    private String contact;

    private String code;

    private String hotline;

    private String images;
}

