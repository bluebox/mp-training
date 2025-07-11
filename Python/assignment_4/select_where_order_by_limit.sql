select * from Users where date_of_birth>'1990-01-01'

select title from Movies where genre='Sci-Fi'

select name,email,date_of_birth from Users where name like "J%"

select name,date_of_birth from Users order by date_of_birth asc limit 2

select title,duration_minutes as duration from Movies where duration_minutes<160 order by duration desc

select name,date_of_birth from Users order by date_of_birth desc limit 2

select name,date_of_birth from Users where date_of_birth>'1990-01-01' order by date_of_birth desc limit 3