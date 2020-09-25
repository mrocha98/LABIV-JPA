package br.gov.sp.fatec.construtora.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("headhunter")
public class HeadhunterCompany extends Company {

    public HeadhunterCompany(String cnpj, String name, Boolean hasBlacklist) {
        super(cnpj, name);
        this.setHasBlacklist(hasBlacklist);
    }

    @Column(name = "hdh_has_blacklist")
    private Boolean hasBlacklist;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "headhunterRecruiter")
    private Set<User> hiredEmployees;
}
