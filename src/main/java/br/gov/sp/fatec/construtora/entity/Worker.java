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
@Table(name = "wrk_worker")
@PrimaryKeyJoinColumn(name = "wrk_id")
public class Worker extends User {

    public Worker(String cpf, String name, String password, Date admissionDate) {
        super(cpf, name, password, admissionDate);
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "workers")
    private Set<Building> buildings;
}
