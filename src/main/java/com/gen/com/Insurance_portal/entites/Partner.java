package com.gen.com.Insurance_portal.entites;

import com.gen.com.Insurance_portal.common.enums.PartnerStatus;
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
public class Partner extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private String code;

    private String email;

    @Column(length = 20)
    private String phoneNumber;

    @Column(length = 20)
    private String hotline;

    @Lob
    private String introductionContent;

    @Column(nullable = false)
    private PartnerStatus status = PartnerStatus.PENDING;

    private String avatarImage;

    @Column(nullable = false)
    private String appellation;

    @Column(nullable = false)
    private String contact;

//    @JsonIgnore
//    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
//    private Set<CustomerContactCode> customerContactCodes;

    private Boolean isActive = false;

}
