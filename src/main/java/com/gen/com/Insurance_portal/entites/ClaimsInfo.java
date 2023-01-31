package com.gen.com.Insurance_portal.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ClaimsInfo extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private String productCode;

    @Column(name = "r_product_id")
    private Long productId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String customerCode;

    @Column(name = "r_customer_id")
    private Long customerId;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @Column(name = "r_contract_id")
    private Long contractId;

    private String contractCode;

    private Integer componentFee;  // bộ phận

    private Integer numberComponent;

    private Integer scratchedFee;  // trầy xước

    private Integer numberScratched;

    private Integer repaintFee;  // sơn lại

    private Integer numberRepaint;

    private Integer bringingFee;  // cứu hộ

    private Integer numberBringing;

    private Integer rearViewMirror;  // gương chiếu hậu

    private Integer numberRearViewMirror;

    private Double maximumCompensation = 200000000.0;
}


