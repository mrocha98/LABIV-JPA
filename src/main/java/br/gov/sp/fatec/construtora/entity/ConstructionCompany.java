package br.gov.sp.fatec.construtora.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("construction")
public class ConstructionCompany extends Company {
    public ConstructionCompany (String cpf, String name) {
        super(cpf, name);
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "constructionCompanyOwner")
    private Set<Building> buildings;
}
