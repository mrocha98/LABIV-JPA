create table cmp_company
(
    cmp_id            bigint unsigned primary key auto_increment,
    cmp_cnpj          varchar(255) not null,
    cmp_name          varchar(255) not null,
    cmp_type          varchar(255) not null,
    hdh_has_blacklist boolean,
    constraint cmp_company_cnpj_uk unique (cmp_cnpj)
);

create table usr_user
(
    usr_id                    bigint unsigned primary key auto_increment,
    usr_cpf                   varchar(255)    not null,
    usr_name                  varchar(255)    not null,
    usr_password              varchar(255)    not null,
    usr_admission_date        date            not null,
    cmp_headhunter_company_id bigint unsigned null,
    constraint usr_cmp_fk foreign key (cmp_headhunter_company_id) references cmp_company (cmp_id),
    constraint usr_user_cpf_uk unique (usr_cpf)
);

create table eng_engineer
(
    eng_id   bigint unsigned primary key auto_increment,
    eng_crea varchar(255) not null,
    constraint eng_usr_fk foreign key (eng_id) references usr_user (usr_id),
    constraint eng_crea_uk unique (eng_crea)
);

create table wrk_worker
(
    wrk_id bigint unsigned primary key auto_increment,
    constraint wrk_usr_fk foreign key (wrk_id) references usr_user (usr_id)
);

create table bld_building
(
    bld_id                      bigint unsigned primary key auto_increment,
    bld_address                 varchar(255)    not null,
    bld_construction_date       date            not null,
    bld_floors                  int unsigned    not null,
    cmp_construction_company_id bigint unsigned not null,
    constraint bld_cmp_fk foreign key (cmp_construction_company_id) references cmp_company (cmp_id),
    constraint bld_address_uk unique (bld_address)
);

create table bpe_building_per_engineer
(
    bpe_id          bigint unsigned primary key,
    bld_building_id bigint unsigned not null,
    eng_engineer_id bigint unsigned not null,
    constraint bpe_bld_fk foreign key (bld_building_id) references bld_building (bld_id),
    constraint bpe_eng_fk foreign key (eng_engineer_id) references eng_engineer (eng_id)
);

create table bpw_building_per_worker
(
    bpw_id          bigint unsigned primary key,
    bld_building_id bigint unsigned not null,
    eng_engineer_id bigint unsigned not null,
    constraint bpw_bld_fk foreign key (bld_building_id) references bld_building (bld_id),
    constraint bpw_eng_fk foreign key (eng_engineer_id) references eng_engineer (eng_id)
);


/*create table hdhn_headhunter_company
(
    hdhn_id   bigint unsigned primary key auto_increment,
    hdhn_cnpj varchar(14)  not null,
    hdhn_name varchar(255) not null,
    constraint hdhn_company_cnpj_uk unique (hdhn_cnpj)
);

create table cnst_construction_company
(
    cnst_id   bigint unsigned primary key auto_increment,
    cnst_cnpj varchar(14)  not null,
    cnst_name varchar(255) not null,
    constraint cmp_company_cnpj_uk unique (cnst_cnpj)
);*/