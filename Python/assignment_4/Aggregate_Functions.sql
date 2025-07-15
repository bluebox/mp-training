select genre,count(*) as cnt from Movies group by genre;

select director_id,count(*) from MovieDirectors group by director_id having count(*)>=1;

select title from Movies where genre in (select genre from Movies group by genre having avg(duration_minutes)>=150);

select count(*) from Users;

select SUM(duration_minutes) as totalDuration from Movies;

select avg(duration_minutes) as AverageDuration from Movies;

select max(duration_minutes) from Movies;

select min(duration_minutes) from Movies;