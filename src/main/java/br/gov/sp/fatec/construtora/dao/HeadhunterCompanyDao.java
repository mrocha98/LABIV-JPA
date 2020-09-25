package br.gov.sp.fatec.construtora.dao;

import br.gov.sp.fatec.construtora.entity.HeadhunterCompany;
import br.gov.sp.fatec.construtora.entity.PersistenceManager;
import br.gov.sp.fatec.construtora.entity.User;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public interface HeadhunterCompanyDao {

    public HeadhunterCompany findByCnpj(String cnpj);

    public void create(HeadhunterCompany headhunterCompany);

    public void update(HeadhunterCompany headhunterCompany);

    public void delete(String cnpj);
}
