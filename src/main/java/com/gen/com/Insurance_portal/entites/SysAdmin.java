package com.gen.com.Insurance_portal.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gen.com.Insurance_portal.common.enums.SysAdminType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@Entity
public class SysAdmin extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToStringExclude
    @EqualsExclude
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(value = EnumType.STRING)
    private SysAdminType type;

    @ToStringExclude
    @EqualsExclude
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "product_provider_id")
    private Partner partner;

    private Boolean isActive;

    @Override
    public String toString() {
        return "SysAdmin{" +
                "id=" + id +
                ", type=" + type +
                ", isActive=" + isActive +
                '}';
    }
}
