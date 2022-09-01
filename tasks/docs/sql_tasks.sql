create schema world;
drop table nations;
use country;
create table nations(country varchar(20), city varchar(20) primary key, street varchar(20));
insert into nations values("India", "vizag", "NSTL");
insert into nations values("India", "Hyd", "Madhapur");
insert into nations values("japan","tokyo","jp");
insert into nations values("America", "USA", "new york");
insert into nations values("America", "california", "los angeles");
insert into nations values("korea","seoul","koreanst");

-- delete from nations where country="japan"; 
 
select country, count(distinct city) from nations group by country;
select * from nations;

-- alter table nations add primary key (city) ;
desc nations;
create table Person(name varchar(15), company varchar(15), city varchar(15),foreign key (city) references nations(city));

insert into Person values ("Bhargavi", "Medplus", "Hyd");
insert into Person values ("Sahithi", "IBM", "USA");
insert into Person values("Srikar", "Deloitte", "california");
insert into Person values("Samba", "Accenture","Hyd");
insert into Person values("Shyam", "Qualcomm", "Hyd");

select * from nations join Person where nations.city=Person.city;
select * from nations left join Person on nations.city=Person.city where nations.country="India" and Person.name like "S%" ;