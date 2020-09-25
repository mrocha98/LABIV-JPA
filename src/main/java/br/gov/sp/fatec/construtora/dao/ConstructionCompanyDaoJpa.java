package br.gov.sp.fatec.construtora.dao;

import br.gov.sp.fatec.construtora.entity.ConstructionCompany;
import br.gov.sp.fatec.construtora.entity.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ConstructionCompanyDaoJpa implements ConstructionCompanyDao {

    private final EntityManager entityManager;

    public ConstructionCompanyDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ConstructionCompanyDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    @Override
    public ConstructionCompany findByCnpj(String cnpj) {
        String jpql = "select c from ConstructionCompany c where c.cnpj = :cnpj";

        TypedQuery<ConstructionCompany> query = entityManager.createQuery(jpql, ConstructionCompany.class);
        query.setParameter("cnpj", cnpj);

        return query.getSingleResult();
    }

    @Override
    public void create(ConstructionCompany constructionCompany) {
        entityManager.persist(constructionCompany);
    }

    @Override
    public void update(ConstructionCompany constructionCompany) {
        if (constructionCompany.getId() == null) {
            create(constructionCompany);
        } else {
            entityManager.merge(constructionCompany);
        }
    }

    @Override
    public void delete(String cnpj) {
        ConstructionCompany constructionCompany = findByCnpj(cnpj);

        if (constructionCompany == null) {
            throw new RuntimeException("No companies were found");
        }

        entityManager.remove(constructionCompany);
    }
}
