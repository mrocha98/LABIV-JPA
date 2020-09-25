package br.gov.sp.fatec.construtora.dao;

import br.gov.sp.fatec.construtora.entity.Engineer;
import br.gov.sp.fatec.construtora.entity.HeadhunterCompany;

public interface EngineerDao {
    public Engineer findByCpf(String cpf);

    public void create(Engineer engineer);

    public void update(Engineer engineer);

    public void delete(String cpf);

    public void associateHeadhunterCompany(Engineer engineer, HeadhunterCompany headhunterCompany);
}
