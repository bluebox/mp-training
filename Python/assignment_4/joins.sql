select m.title,d.name,m.movie_id,d.director_id from Movies as m
inner join MovieDirectors as md on md.movie_id=m.movie_id
inner join Directors as d on d.director_id=md.director_id


select m.title,d.director_id from Movies as m
left join MovieDirectors as d on d.movie_id=m.movie_id

select d.name,md.movie_id from MovieDirectors as md
right join Directors as d on md.director_id=d.director_id

select m.title,d.director_id from Movies as m
left join MovieDirectors as d on d.movie_id=m.movie_id
union
select d.name,md.movie_id from MovieDirectors as md
right join Directors as d on md.director_id=d.director_id


