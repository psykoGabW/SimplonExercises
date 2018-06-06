create schema if not exists spring_exo01;

SET search_path to spring_exo01;

drop table if exists foo;

drop sequence if exists foo_id_seq;

create sequence foo_id_seq minvalue 1 increment by 1 start with 1 ;

create table foo(id bigint primary key default nextval('spring_exo01.foo_id_seq'), name varchar(255));

