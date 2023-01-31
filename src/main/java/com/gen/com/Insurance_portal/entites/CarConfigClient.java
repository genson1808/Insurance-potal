package com.gen.com.Insurance_portal.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CarConfigClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String carConfigBannerFile;

    @Lob
    private String carConfigHeaderContent;

    @Lob
    private String carConfigBodyContent;

    public CarConfigClient(String carConfigBannerFile, String carConfigHeaderContent, String carConfigBodyContent) {
        this.carConfigBannerFile = carConfigBannerFile;
        this.carConfigHeaderContent = carConfigHeaderContent;
        this.carConfigBodyContent = carConfigBodyContent;
    }
}
