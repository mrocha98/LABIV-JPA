package br.gov.sp.fatec.construtora.dao;

import br.gov.sp.fatec.construtora.entity.Building;
import br.gov.sp.fatec.construtora.entity.ConstructionCompany;
import br.gov.sp.fatec.construtora.entity.Engineer;

import java.util.Date;
import java.util.List;

public interface BuildingDao {

    public Building findByAddress(String address);

    public List<Building> filterByConstructionDate(Date initialDate, Date endDate);

    public void create(Building building);

    public void update(Building building);

    public void delete(String address);

    public void associateEngineer(Building building, Engineer engineer);

    public void associateEngineers(Building building, List<Engineer> engineers);

    public List<Building> filter(Integer floors, String constructionCompanyCnpj);
}
