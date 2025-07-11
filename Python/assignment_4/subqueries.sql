select title from Movies where genre in (select genre from Movies group by genre having avg(duration_minutes)>=150);

select name from Users where user_id in (select a.user_id from WatchHistory as a
inner join Movies as b on b.movie_id=a.movie_id
where b.genre=(select genre from Movies where title='Inception'))

select title from Movies where movie_id in( select movie_id from MovieDirectors where director_id=(select director_id from MovieDirectors as md
inner join Movies as m on m.movie_id=md.movie_id 
where title='Titanic'))
