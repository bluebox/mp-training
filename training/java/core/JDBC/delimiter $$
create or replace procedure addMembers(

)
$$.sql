Drop procedure if exists addMembers
delimiter $$
create procedure addMembers(
	IN songs json
)
Begin
	Declare i int default 0; 
    select count(*) into i from practice.tables where table_name=song;
	if i=0 then
		create table song(SongId varchar(20) primary key,songname varchar(20),singer varchar(20));
	end if;
End
$$