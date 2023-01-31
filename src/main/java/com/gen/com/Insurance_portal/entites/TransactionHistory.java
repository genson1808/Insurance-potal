package com.gen.com.Insurance_portal.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gen.com.Insurance_portal.common.enums.PaymentMethod;
import com.gen.com.Insurance_portal.common.enums.TransactionProcessName;
import com.gen.com.Insurance_portal.common.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionCode;

    private String processCode;

    private Date transactionDate;

    private Double amount;

    private String customerPhoneNumber;

    private String customerName;

    private String contractCode;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    private String productName;

    private String productCode;

    private Date effectiveDate;

    private Date expiredDate;

    private TransactionProcessName processName;

    private PaymentMethod paymentMethod;

    private TransactionStatus transactionStatus;

    private String customerCode;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
