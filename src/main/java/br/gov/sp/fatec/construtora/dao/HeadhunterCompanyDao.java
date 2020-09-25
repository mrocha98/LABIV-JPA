package br.gov.sp.fatec.construtora.dao;

import br.gov.sp.fatec.construtora.entity.HeadhunterCompany;

public interface HeadhunterCompanyDao {

    public HeadhunterCompany findByCnpj(String cnpj);

    public HeadhunterCompany create(HeadhunterCompany headhunterCompany);

    public HeadhunterCompany update(HeadhunterCompany headhunterCompany);

    public void delete(String cnpj);

}
