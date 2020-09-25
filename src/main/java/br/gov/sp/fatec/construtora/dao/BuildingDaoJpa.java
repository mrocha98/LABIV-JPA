package br.gov.sp.fatec.construtora.dao;

import br.gov.sp.fatec.construtora.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class BuildingDaoJpa implements BuildingDao {

    private final EntityManager entityManager;

    public BuildingDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public BuildingDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    @Override
    public Building findByAddress(String address) {
        String jpql = "select b from Building b where b.address = :address";

        TypedQuery<Building> query = entityManager.createQuery(jpql, Building.class);
        query.setParameter("address", address);

        return query.getSingleResult();
    }

    @Override
    public List<Building> filterByConstructionDate(Date initialDate, Date endDate) {
        return null;
    }

    @Override
    public void create(Building building) {
        entityManager.persist(building);
    }

    @Override
    public void update(Building building) {
        if (building.getId() == null) {
            create(building);
        } else {
            entityManager.merge(building);
        }
    }

    @Override
    public void delete(String address) {
        Building building = findByAddress(address);

        if (building == null) throw new RuntimeException("No buildings were found");

        entityManager.remove(building);
    }

    @Override
    public void associateEngineer(Building building, Engineer engineer) {
        if (engineer.getId() == null) throw new RuntimeException("This engineer don't exists");

        building.getEngineers().add(engineer);
        update(building);
    }

    @Override
    public void associateEngineers(Building building, List<Engineer> engineers) {
        engineers.forEach(engineer ->
            associateEngineer(building, engineer)
        );
    }

    @Override
    public List<Building> filter(Integer floors, String constructionCompanyCnpj) {
        String jpql = "select b from Building b inner join ConstructionCompany c on c.cnpj = :constructionCompanyCnpj and b.floors = :floors";

        TypedQuery<Building> query = entityManager.createQuery(jpql, Building.class);
        query.setParameter("constructionCompanyCnpj", constructionCompanyCnpj);
        query.setParameter("floors", floors);
        return query.getResultList();
    }
}
