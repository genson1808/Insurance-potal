package com.gen.com.Insurance_portal.models.responseModels;

import com.gen.com.Insurance_portal.common.enums.TransactionProcessName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistoryResponse {
    private String name;

    private String numberPlate;

    private String carMaker;

    private String email;

    private String phoneNumber;

    private String address;

    private Double price;

    private TransactionProcessName processName;

    private Long productId;

    private Long customerId;

    private String carBrandCode;

    private String carBrandName;

    private String carModelCode;

    private String carModelTitle;

    private String contractCode;
}
