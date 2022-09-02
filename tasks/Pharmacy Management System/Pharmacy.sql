create database Pharmacy;
show databases;
use Pharmacy;

show tables;


create table customer(cust_id int primary key, first_name varchar(50) not null, last_name varchar(50), contact varchar(10) not null, email varchar(50) not null unique, address varchar(250) not null);
create table manufacturer(company_id int primary key, name varchar(50) not null, contact varchar(13) not null, email varchar(50) not null, address varchar(250) not null);
create table doctor(doc_id int primary key, first_name varchar(50) not null, last_name varchar(50), specialization varchar(50) not null ,contact varchar(10) not null, email varchar(50) not null unique);
create table employee(emp_id int primary key, first_name varchar(50) not null, last_name varchar(50), doj date not null, designation varchar(50) not null, job_type varchar(20) not null, salary int not null, contact varchar(10) not null, email varchar(50) not null unique, address varchar(250) not null);
create table drug(drug_id int primary key, drug_name varchar(100) not null, dist_id int not null, company_id int not null, mrp numeric(5,5) not null, discount numeric(5,5) not null, stock varchar(10) not null, quantity int not null, mfg_date date not null, expiry date not null);
create table distributor(dist_id int primary key, dist_name varchar(50) not null, contact varchar(10) not null, email varchar(50) not null, address varchar(250) not null);
create table sales(sale_id int primary key, sale_date date not null, cust_id int not null, emp_id int not null, quantity int not null, price numeric(10,5) not null);
create table prescribe(cust_id int not null, doc_id int not null, drug_id int not null, quantity int not null);
create table supply(purchase_id int, purchase_date date, drug_id int, dist_id int, company_id int, quantity int, price numeric(20,5));
create table purchase(purchase_id int primary key, purchase_date date not null, drug_id int not null, drug_name varchar(100) not null);

alter table drug add foreign key (company_id) references manufacturer(company_id);
alter table drug add foreign key (dist_id) references distributor(dist_id);
alter table sales add foreign key (cust_id) references customer(cust_id);
alter table sales add foreign key (emp_id) references employee(emp_id);
alter table prescribe add foreign key (cust_id) references customer(cust_id);
alter table prescribe add foreign key (doc_id) references doctor(doc_id);
alter table prescribe add foreign key (drug_id) references drug(drug_id);
alter table supply add foreign key (purchase_id) references purchase(purchase_id);
alter table supply add foreign key (drug_id) references drug(drug_id);
alter table supply add foreign key (dist_id) references distributor(dist_id);
alter table supply add foreign key (company_id) references manufacturer(company_id);
alter table purchase add foreign key (drug_id) references drug(drug_id);



show tables;