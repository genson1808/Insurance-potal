package com.gen.com.Insurance_portal.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AuthoritiesGroup extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "authoritiesGroup")
    private Set<Authorities> authorities;

    @ManyToOne(fetch = FetchType.LAZY)
    private AuthoritiesGroup parentAuthoritiesGroup;

    @OneToMany(mappedBy = "parentAuthoritiesGroup")
    private Set<AuthoritiesGroup> subAuthoritiesGroup = new HashSet<>();

    public AuthoritiesGroup(String name, Set<Authorities> authorities) {
        this.name = name;
        this.authorities = authorities;
    }

    public AuthoritiesGroup(String name) {
        this.name = name;
    }

    public AuthoritiesGroup(String name, AuthoritiesGroup parentAuthoritiesGroup) {
        this.name = name;
        this.parentAuthoritiesGroup = parentAuthoritiesGroup;
    }
}
