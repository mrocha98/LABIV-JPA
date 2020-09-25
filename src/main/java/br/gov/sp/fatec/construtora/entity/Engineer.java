package br.gov.sp.fatec.construtora.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "eng_engineer")
@PrimaryKeyJoinColumn(name = "eng_id")
public class Engineer extends User {

    public Engineer(String cpf, String name, String password, Date admissionDate, String crea) {
        super(cpf, name, password, admissionDate);
        this.setCrea(crea);
    }

    @Column(name = "eng_crea")
    private String crea;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "engineers")
    private Set<Building> buildings;
}
