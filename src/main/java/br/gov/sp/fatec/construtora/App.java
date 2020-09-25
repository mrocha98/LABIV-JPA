package br.gov.sp.fatec.construtora;

import br.gov.sp.fatec.construtora.dao.HeadhunterCompanyDaoJpa;
import br.gov.sp.fatec.construtora.entity.ConstructionCompany;
import br.gov.sp.fatec.construtora.entity.HeadhunterCompany;
import br.gov.sp.fatec.construtora.entity.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

public class App {
    public static void main(String[] args) {
        EntityManager entityManager = PersistenceManager.getInstance().getEntityManager();

        try {
            entityManager.getTransaction().begin();

            HeadhunterCompanyDaoJpa headhunterCompanyDaoJpa = new HeadhunterCompanyDaoJpa(entityManager);

            HeadhunterCompany headhunterCompany = new HeadhunterCompany("63427009000101", "Juquinha talents", false);
            headhunterCompanyDaoJpa.create(headhunterCompany);
            headhunterCompany.setName("Jurandir Talents");
            headhunterCompanyDaoJpa.update(headhunterCompany);
            headhunterCompanyDaoJpa.delete("63427009000101");

            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();

            ConstructionCompany constructionCompany = new ConstructionCompany("63427009000101", "Zuleica");
            entityManager.persist(constructionCompany);
            entityManager.remove(constructionCompany);

            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        entityManager.close();
    }
}


