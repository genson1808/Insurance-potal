package com.gen.com.Insurance_portal.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gen.com.Insurance_portal.common.enums.ClaimsStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Claims extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    private String contractCode;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String customerCode;

    private String name;

    private String numberPlate;

    private Boolean repaintFee;

    private Double repaintFeeAmount;

    private Double insuranceRepaintFeeAmount;

    private Integer numberRepaint = 0;

    private Boolean bringingFee;

    private Integer numberBringing = 0;

    private Double bringingFeeAmount;

    private Double insuranceBringingFeeAmount;

    private Boolean rearViewMirror;

    private Integer numberRearViewMirror = 0;

    private Double rearViewMirrorAmount;

    private Double insuranceRearViewMirrorAmount;

    private Boolean componentFee;

    private Integer numberComponent = 0;

    private Double componentFeeAmount;

    private Double insuranceComponentFeeAmount;

    private Boolean scratchedFee;

    private Integer numberScratched = 0;

    private Double scratchedFeeAmount;

    private Double insuranceScratchedFeeAmount;

    private Double amount;

    private ClaimsStatus status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    private String partnerCode;

    private String partnerName;

    private String employeeName;

    private String employeePhoneNumber;

    private String note;
}
