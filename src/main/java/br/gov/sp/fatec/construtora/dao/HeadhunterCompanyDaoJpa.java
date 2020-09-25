package br.gov.sp.fatec.construtora.dao;

import br.gov.sp.fatec.construtora.entity.HeadhunterCompany;
import br.gov.sp.fatec.construtora.entity.PersistenceManager;
import br.gov.sp.fatec.construtora.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class HeadhunterCompanyDaoJpa implements HeadhunterCompanyDao {

    private final EntityManager entityManager;

    public HeadhunterCompanyDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public HeadhunterCompanyDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    @Override
    public HeadhunterCompany findByCnpj(String cnpj) {
        String jpql = "select h from HeadhunterCompany h where h.cnpj = :cnpj";

        TypedQuery<HeadhunterCompany> query = entityManager.createQuery(jpql, HeadhunterCompany.class);
        query.setParameter("cnpj", cnpj);

        return query.getSingleResult();
    }

    @Override
    public void create(HeadhunterCompany headhunterCompany) {
        entityManager.persist(headhunterCompany);
    }

    @Override
    public void update(HeadhunterCompany headhunterCompany) {
        if (headhunterCompany.getId() == null) {
            create(headhunterCompany);
        } else {
            entityManager.merge(headhunterCompany);
        }
    }

    @Override
    public void delete(String cnpj) {
        HeadhunterCompany headhunterCompany = findByCnpj(cnpj);

        if (headhunterCompany == null) {
            throw new RuntimeException("No companies were found");
        }

        entityManager.remove(headhunterCompany);
    }
}
