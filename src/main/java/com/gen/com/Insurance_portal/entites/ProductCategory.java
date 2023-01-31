package com.gen.com.Insurance_portal.entites;

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
public class ProductCategory extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String nameDisplayOnClient;

    @Column(name = "`order`")
    private Integer order;

    private String description;

    public ProductCategory(String name) {
        this.name = name;
    }

    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
