package br.gov.sp.fatec.construtora.dao;

import br.gov.sp.fatec.construtora.entity.ConstructionCompany;

public interface ConstructionCompanyDao {

    public ConstructionCompany findByCnpj(String cnpj);

    public void create(ConstructionCompany constructionCompany);

    public void update(ConstructionCompany constructionCompany);

    public void delete(String cnpj);
}
