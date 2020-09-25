package br.gov.sp.fatec.construtora;

import br.gov.sp.fatec.construtora.dao.*;
import br.gov.sp.fatec.construtora.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

public class App {


    public static void main(String[] args) {

        EntityManager entityManager = PersistenceManager.getInstance().getEntityManager();

        EngineerDao engineerDao = new EngineerDaoJpa(entityManager);
        BuildingDao buildingDao = new BuildingDaoJpa(entityManager);
        ConstructionCompanyDao constructionCompanyDao = new ConstructionCompanyDaoJpa(entityManager);
        HeadhunterCompanyDao headhunterCompanyDao = new HeadhunterCompanyDaoJpa(entityManager);

        try {
            entityManager.getTransaction().begin();

            Engineer eng1 = new Engineer("25626393080", "Jurandir", "123", new Date(), "1111111111");
            Engineer eng2 = new Engineer("47460113034", "Rodolfo", "321", new Date(), "2222222222");
            Engineer eng3 = new Engineer("36076499010", "Fausto", "qwerty", new Date(), "3333333333");

            engineerDao.create(eng1);
            engineerDao.create(eng2);
            engineerDao.create(eng3);

            HeadhunterCompany h1 = new HeadhunterCompany("03183699000160", "Juquinha Talents", true);
            headhunterCompanyDao.create(h1);

            engineerDao.associateHeadhunterCompany(
                engineerDao.findByCpf("47460113034"),
                headhunterCompanyDao.findByCnpj("03183699000160")
            );

            engineerDao.associateHeadhunterCompany(
                engineerDao.findByCpf("36076499010"),
                headhunterCompanyDao.findByCnpj("03183699000160")
            );

            ConstructionCompany c1 = new ConstructionCompany("56862264000109", "Atlas Inc");
            constructionCompanyDao.create(c1);

            entityManager.getTransaction().commit();
            entityManager.clear();
            entityManager.getTransaction().begin();

            Building b1 = new Building("r dos bobos n0", new Date(), 6, constructionCompanyDao.findByCnpj("56862264000109"));
            buildingDao.create(b1);

            entityManager.getTransaction().commit();
            entityManager.clear();
            entityManager.getTransaction().begin();

            buildingDao.associateEngineers(
                buildingDao.findByAddress("r dos bobos n0"),
                new LinkedList<>(
                    Arrays.asList(
                        engineerDao.findByCpf("25626393080"),
                        engineerDao.findByCpf("36076499010")
                    )
                ));

            buildingDao.filter(6, "56862264000109").forEach(building -> System.out.println(building.getAddress()));

            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        try {
            entityManager.getTransaction().begin();

            buildingDao.delete("r dos bobos n0");

            constructionCompanyDao.delete("56862264000109");

            new LinkedList<>(Arrays.asList("25626393080", "47460113034", "36076499010")).forEach(engineerDao::delete);

            headhunterCompanyDao.delete("03183699000160");

            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        entityManager.close();
    }
}


