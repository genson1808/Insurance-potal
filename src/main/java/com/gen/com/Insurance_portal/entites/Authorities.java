package com.gen.com.Insurance_portal.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(value = { "authoritiesGroup" })
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    @Column(unique = true)
    private String name;

    private String description;

    private Boolean isActive = true;

    @ManyToMany(mappedBy = "authorities")
    private List<Role> roles = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorities_group_id")
    private AuthoritiesGroup authoritiesGroup;

    public Authorities(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Authorities(String code, String name, AuthoritiesGroup authoritiesGroup) {
        this.code = code;
        this.name = name;
        this.authoritiesGroup = authoritiesGroup;
    }

}
