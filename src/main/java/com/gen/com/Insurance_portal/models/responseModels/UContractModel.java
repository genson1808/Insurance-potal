package com.gen.com.Insurance_portal.models.responseModels;

import com.gen.com.Insurance_portal.common.enums.ContractStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UContractModel {
    private String code;

    private String idNumber;

    private ContractStatus status;

    private Date effectiveDate;

    private String fullName;

    private String partner;

    private String product;

    private String productCode;
}
