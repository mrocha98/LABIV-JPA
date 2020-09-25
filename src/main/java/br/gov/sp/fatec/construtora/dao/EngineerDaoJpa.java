package br.gov.sp.fatec.construtora.dao;

import br.gov.sp.fatec.construtora.entity.Engineer;
import br.gov.sp.fatec.construtora.entity.HeadhunterCompany;
import br.gov.sp.fatec.construtora.entity.PersistenceManager;
import br.gov.sp.fatec.construtora.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class EngineerDaoJpa implements EngineerDao {

    private final EntityManager entityManager;

    public EngineerDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EngineerDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    @Override
    public Engineer findByCpf(String cpf) {
        String jpql = "select e from Engineer e where e.cpf = :cpf";

        TypedQuery<Engineer> query = entityManager.createQuery(jpql, Engineer.class);
        query.setParameter("cpf", cpf);

        return query.getSingleResult();
    }

    @Override
    public void create(Engineer engineer) {
        entityManager.persist(engineer);
    }

    @Override
    public void update(Engineer engineer) {
        if (engineer.getId() == null) {
            create(engineer);
        } else {
            entityManager.merge(engineer);
        }
    }

    @Override
    public void delete(String cpf) {
        Engineer engineer = findByCpf(cpf);

        if (engineer == null) throw new RuntimeException("No engineers were found");

        entityManager.remove(engineer);
    }

    @Override
    public void associateHeadhunterCompany(Engineer engineer, HeadhunterCompany headhunterCompany) {
        if (headhunterCompany.getId() == null) throw new RuntimeException("This headhunter company don't exists");

        engineer.setHeadhunterRecruiter(headhunterCompany);
        update(engineer);
    }
}
