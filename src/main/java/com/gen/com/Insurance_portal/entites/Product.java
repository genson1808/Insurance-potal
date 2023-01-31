package com.gen.com.Insurance_portal.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gen.com.Insurance_portal.common.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Product extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @Lob
    @Column(nullable = false)
    private String shortDescription;

    @Lob
    @Column(nullable = false)
    private String detailedDescription;

    private String avatarImage;

    private String bannerImage;

    private String indemnityTemplate;

    private String indemnityInstruction;

    @Lob
    @Column(nullable = false)
    private String indemnityInstructionContent = "guide";

    @Enumerated(EnumType.STRING)
    private EffectiveDateType effectiveDateType = EffectiveDateType.T;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FeeType feeType = FeeType.FIXED;

    @Column(nullable = false)
    private ProductStatus productStatus = ProductStatus.APPROVED;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderApply genderApply;

    private Boolean isActive =  true;

    private Boolean duplicateBuyerInfo;

    private Boolean hideBeneficiary;

    // số lựa chọn ngày có hiệu lực
    private Integer EffectiveDateRangeSelectionNumber = 0;


    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @JsonIgnore
    private Boolean enableIndemnity;

    @JsonIgnore
    private Boolean canBuyMultiple;

    @JsonIgnore
    private Boolean isSelfInsurance;

    @JsonIgnore
    private Boolean hideBuyerInfo;

    private String videoUrl;

    private Double priceObj = 200000000.0;

    private String insuredRule;

    @Column(nullable = false)
    private Integer componentFee;  // bộ phận

    @Column(nullable = false)
    private Integer numberComponent;

    @Column(nullable = false)
    private Integer scratchedFee;  // trầy xước

    @Column(nullable = false)
    private Integer numberScratched;

    @Column(nullable = false)
    private Integer repaintFee;  // sơn lại

    @Column(nullable = false)
    private Integer numberRepaint;

    @Column(nullable = false)
    private Integer bringingFee;  // cứu hộ

    @Column(nullable = false)
    private Integer numberBringing;

    @Column(nullable = false)
    private Integer rearViewMirror;  // gương chiếu hậu

    @Column(nullable = false)
    private Integer numberRearViewMirror;

}
