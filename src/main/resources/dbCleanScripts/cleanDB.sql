drop table channels;
drop table users;
drop table weather;

delete from flyway_schema_history where version='001';
delete from flyway_schema_history where version='002';
delete from flyway_schema_history where version='003';