CREATE TABLE GROUP1(grp_id int primary key,grp_name varchar[10]);
create table Student(adm_id int primary key, stud_id int, first_name varchar[15],second_name varchar[20], DOA date, grp_id int, foreign key(grp_id) references Group1(grp_id));

create table fee_details(grp_id int, year1 int , fee int, foreign key(grp_id) references Group1(grp_id));
create table fee_payment(adm_id int,grp_id int, first_name varchar[15], second_name VARCHAR[10],year1 int,foreign key(adm_id) references student(adm_id),foreign key(grp_id) references Group1(grp_id))
fee_amount int,


INSERT INTO group1 values(1,'MSCS'),(2,'MECS'),(3,'MPCS'),(4,'BBA'),(5,'BCA');
insert into student values(2201,901,'Bhargavi','L','2022-06-02',1),(2202,902,'Sahithi','A','2022-06-09',2),(2203,903,'Samba','R','2022-07-01',1),
(2206,906,'srikar','K','2022-06-07',3),(2208,907,'vijay','P','2022-07-02',4),(2209,905,'Abhi','S','2022-07-02',3)
insert into fee_details values (1,1,40000),(2,1,40000),(3,1,40500),(4,1,38000),(5,1,45000)
insert into fee_payment values(2203,1,'samba','R',1),(2208,4,'vinay','P',1),(2201,1,'bhargavi','L',1)
alter table fee_payment add payment_status varchar[10]
update fee_payment set payment_status="unpaid" where grp_id=3
update fee_payment set payment_status="paid" where grp_id=1
update fee_payment set payment_status="unpaid" where grp_id=4

--QUERIES

--1. no. of students optted in each group
select i.grp_name as Groupname,count(i.grp_id) from group1 i join student s where i.grp_id=s.grp_id group by grp_name
                        
--2. fee details of the students joined in every stream
select i.grp_name,j.fee from group1 i join fee_details j where i.grp_id=j.grp_id

--3.student who joined in computer science stream
select i.first_name from student i join group1 j where i.grp_id=j.grp_id and (grp_name like'%cs' or grp_name ='bca')

--4.students whose names starts with s
select first_name from student where first_name like"s%"

--5.students who joine4d in the month of june in physics branch
select i.first_name,i.doa from student i join group1 j where i.grp_id =j.grp_id and i.doa between '2022-06-01' and '2022-07-00'

--6. student details who paid their fee along with amount
select i.first_name,j.payment_status,k.fee from Student i join fee_payment j on i.adm_id=j.adm_id join fee_details k 
on j.grp_id = k.grp_id where payment_status="paid"
