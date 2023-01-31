package com.gen.com.Insurance_portal.models.responseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UTransactionHistoryModel {
    private String contractCode;
    private String productName;
    private Date effectiveDate;
    private Date expiredDate;
    private Date transactionDate;
    private Double amount;
}
