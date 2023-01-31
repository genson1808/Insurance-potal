package com.gen.com.Insurance_portal.models.RequestModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequiredClaimsModel {

    @NotNull(message = "contractCode is required!")
    private String contractCode;

    @NotBlank(message = "numberPlate is required!")
    private String numberPlate;

    @NotBlank(message = "name is required!")
    private String name;

    @NotNull(message = "repaintFee is required!")
    private Boolean repaintFee;

    @NotNull(message = "repaintFeeAmount is required!")
    private Double repaintFeeAmount;

    @NotNull(message = "bringingFee is required!")
    private Boolean bringingFee;

    @NotNull(message = "bringingFeeAmount is required!")
    private Double bringingFeeAmount;

    @NotNull(message = "rearViewMirror is required!")
    private Boolean rearViewMirror;

    @NotNull(message = "rearViewMirrorAmount is required!")
    private Double rearViewMirrorAmount;

    @NotNull(message = "componentFee is required!")
    private Boolean componentFee;

    @NotNull(message = "componentFeeAmount is required!")
    private Double componentFeeAmount;

    @NotNull(message = "scratchedFee is required!")
    private Boolean scratchedFee;

    @NotNull(message = "scratchedFeeAmount is required!")
    private Double scratchedFeeAmount;

    @NotNull(message = "partnerId is required!")
    private Long partnerId;

    @NotBlank(message = "employeeName is required!")
    private String employeeName;

    @NotBlank(message = "employeePhoneNumber is required!")
    private String employeePhoneNumber;

    private String note;
}