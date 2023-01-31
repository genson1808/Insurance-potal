package com.gen.com.Insurance_portal.models.RequestModels;

import com.gen.com.Insurance_portal.common.enums.TransactionProcessName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistoryModel {

    @NotBlank(message = "name is required!")
    private String name;

    @NotBlank(message = "numberPlate is required!")
    private String numberPlate;

    @NotBlank(message = "carMaker is required!")
    private String carMaker;

    @NotBlank(message = "email is required!")
    @Email(message = "email invalid!")
    private String email;

    @NotBlank(message = "phoneNumber is required!")
    private String phoneNumber;

    @NotBlank(message = "address is required!")
    private String address;

    @Min(value = 1, message = "price must be greater than 0")
    @NotNull(message = "price is required!")
    private Double price;

    @Schema(description = "value in { BuyNew, Extend, Cancel}")
    @NotNull(message = "processName is required!")
    private TransactionProcessName processName;

    @NotNull(message = "productId is required!")
    private Long productId;

    @NotNull(message = "customerId is required!")
    private Long customerId;

    private String carBrandCode;

    private String carBrandName;

    private String carModelCode;

    private String carModelTitle;

}
