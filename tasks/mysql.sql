use training;
show create table Movie;
select * from Movie;
select * from Hero;
show tables;
drop table Movie;
drop table Hero;

create table Movie(
	Movie_id varchar(50) primary key,
    Movie_name varchar(50) not null,
    Hero_name varchar(50),
    Heroine_name varchar(50),
    foreign key(Hero_name) references Hero(Hero_name)
);


insert into Movie values('m1','movie1','hero1','heroine');

insert into Movie(Movie_id,Movie_name,Hero_name) values('m2','movie2','hero2');

insert into Movie values
('m3','movie3','hero3','heroine2'),
('m4','movie4','hero4','heroine3'),
('m5','movie5','hero5','heroine4'),
('m6','movie6','hero6','heroine5');

UPDATE 	Movie SET Heroine_name = 'heroine1' where Heroine_name = null;

create table Hero(
	hero_name varchar(20) primary key ,
    remuneration int,
    num_of_movies int
);

ALTER TABLE Hero CHANGE hero_name Hero_name varchar(20);

delete from Movie where Movie_id = 'm6';

select * from Movie  where Movie_name like '%3'; 

insert into Hero values
('hero1',10000,2),
('hero2',15000,1),
('hero3',20000,3),
('hero4',25000,5),
('hero5',30000,3);

select * from Hero order by remuneration desc;


select * from Hero order by num_of_movies asc;

select min(num_of_movies) from Hero;

select avg(num_of_movies) from Hero;

select max(num_of_movies) from Hero;

select distinct  num_of_movies from Hero;

