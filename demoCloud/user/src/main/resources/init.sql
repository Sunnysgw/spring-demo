create table if not exists role
(
    id int auto_increment,
    role_name varchar(128) null,
    user_id int not null,
    role_type int null,
    dis varchar(24) null,
    constraint role_id_uindex
    unique (id),
    constraint role_type_dis_user_id
    unique (role_type, dis, user_id),
    constraint user_id_role_name_dis
    unique (user_id, role_name, dis)
    );

alter table role
    add primary key (id);

create table if not exists userinfo
(
    id int not null,
    user_name varchar(128) not null,
    age int null,
    address varchar(1024) null,
    height double null,
    pic varchar(128) null,
    constraint userinfo_id_uindex
    unique (id)
    );

alter table userinfo
    add primary key (id);

