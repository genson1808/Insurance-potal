package com.gen.com.Insurance_portal.models.responseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimsInfoModel {

    private String productCode;

    private String customerCode;

    private String contractCode;

    private Integer componentFee;

    private Integer numberComponent;

    private Integer scratchedFee;

    private Integer numberScratched;

    private Integer repaintFee;

    private Integer numberRepaint;

    private Integer bringingFee;

    private Integer numberBringing;

    private Integer rearViewMirror;

    private Integer numberRearViewMirror;

    private Double maximumCompensation;
}
