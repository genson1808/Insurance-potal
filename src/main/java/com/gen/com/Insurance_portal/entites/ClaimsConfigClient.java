package com.gen.com.Insurance_portal.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ClaimsConfigClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String claimConfigFormBanner;

    @Lob
    private String claimConfigFormBody;

    @Lob
    private String claimConfigFormHeader;

    public ClaimsConfigClient(String claimConfigFormBanner, String claimConfigFormBody, String claimConfigFormHeader) {
        this.claimConfigFormBanner = claimConfigFormBanner;
        this.claimConfigFormBody = claimConfigFormBody;
        this.claimConfigFormHeader = claimConfigFormHeader;
    }
}
