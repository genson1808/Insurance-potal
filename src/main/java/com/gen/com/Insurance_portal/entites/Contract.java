package com.gen.com.Insurance_portal.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gen.com.Insurance_portal.common.enums.PaymentStatus;
import com.gen.com.Insurance_portal.common.enums.ContractStatus;
import com.gen.com.Insurance_portal.common.enums.PurchaseMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Contract extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Column(nullable = false)
    private String customerCode;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String idNumber;

    @Enumerated(value = EnumType.STRING)
    private ContractStatus status = ContractStatus.Inactive;

    @Column(nullable = false)
    private Date effectiveDate;

    @Column(nullable = false)
    private Date expiredDate;

    private Date cancelDate;

    private Date createdContractDate;

    private Date activeDate;

    @Column(nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.Paid;

    private Double paidAmount;

    private Date extendSuccessDate;

    private String fullName;

    private Date dob;

    private String gender;

    private String email;

    private String phoneNumber;

    private String address;

    private String buyerIdNumber;

    @Column(nullable = false)
    private PurchaseMethod purchaseMethod;

    private String numberPlate;

    private String carBrand;

    private String carMaker;

    private String productName;

    private String carBrandCode;

    private String carBrandName;

    private String carModelCode;

    private String carModelTitle;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String productCode;

    @ToStringExclude
    @EqualsExclude
    @JsonIgnore
    @OneToOne(mappedBy = "contract", fetch = FetchType.LAZY)
    private TransactionHistory transactionHistory;

    @ToStringExclude
    @EqualsExclude
    @JsonIgnore
    @OneToOne(mappedBy = "contract")
    private ClaimsInfo claimsInfo;

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", customerCode='" + customerCode + '\'' +
                ", customer=" + customer +
                ", idNumber='" + idNumber + '\'' +
                ", status=" + status +
                ", effectiveDate=" + effectiveDate +
                ", expiredDate=" + expiredDate +
                ", cancelDate=" + cancelDate +
                ", createdContractDate=" + createdContractDate +
                ", activeDate=" + activeDate +
                ", paidAmount=" + paidAmount +
                ", extendSuccessDate=" + extendSuccessDate +
                ", fullName='" + fullName + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", buyerIdNumber='" + buyerIdNumber + '\'' +
                ", numberPlate='" + numberPlate + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", carMaker='" + carMaker + '\'' +
                ", productName='" + productName + '\'' +
                ", carBrandCode='" + carBrandCode + '\'' +
                ", carBrandName='" + carBrandName + '\'' +
                ", carModelCode='" + carModelCode + '\'' +
                ", carModelTitle='" + carModelTitle + '\'' +
                ", product=" + product +
                ", productCode='" + productCode + '\'' +
                '}';
    }
}
