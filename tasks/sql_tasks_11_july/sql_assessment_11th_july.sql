create table player_table (
    player_id int primary key,
    name varchar(300),
    captain_id int,
    team_id int,
    stats_id int,
    player_info_id int,
    foreign key (team_id) references team_table(team_id),
    foreign key (stats_id) references stats_table(stats_id),
    foreign key (player_info_id) references player_info_table(player_info_id)
);

create table team_table (
    team_id int primary key,
    team_name varchar(300)
);

create table stats_table (
    stats_id int primary key,
    bat_id int,
    ball_id int,
    foreign key (bat_id) references stats_bat_table(bat_id),
    foreign key (ball_id) references stats_ball_table(ball_id)
);

create table stats_bat_table (
    bat_id int primary key,
    matches_played int,
    runs_scored int,
    centuries int,
    average int,
    strike_rate int,
    best int
);

create table stats_ball_table (
    ball_id int primary key,
    matches_played int,
    wickets int,
    hatricks int,
    economy decimal,
    runouts int,
    stumps int,
    best varchar(300)
);

create table player_info_table (
    player_info_id int primary key,
    debut_date date,
    batting_type varchar,
    bowling_type varchar
);

INSERT INTO player_table (player_id,name,captain_id,team_id,stats_id,player_info_id) VALUES (1, 'jason roy', 2, 2, 1, 1);
INSERT INTO player_table (player_id,name,captain_id,team_id,stats_id,player_info_id) VALUES (2, 'jos buttler', null, 2, 2, 2);
INSERT INTO player_table (player_id,name,captain_id,team_id,stats_id,player_info_id) VALUES (3, 'chahal', 6, 1, 3, 3);
INSERT INTO player_table (player_id,name,captain_id,team_id,stats_id,player_info_id) VALUES (4, 'surya kumar yadav', 6, 1, 4, 4);
INSERT INTO player_table (player_id,name,captain_id,team_id,stats_id,player_info_id) VALUES (5, 'duplesis', null, 3, 5, 5);
INSERT INTO player_table (player_id,name,captain_id,team_id,stats_id,player_info_id) VALUES (6, 'ms dhoni', null, 1, 6, 6);
INSERT INTO player_table (player_id,name,captain_id,team_id,stats_id,player_info_id) VALUES (7, 'jedeja', 6, 1, 7, 7);
INSERT INTO player_table (player_id,name,captain_id,team_id,stats_id,player_info_id) VALUES (8, 'bumrah', 6, 1, 8, 8);
INSERT INTO player_table (player_id,name,captain_id,team_id,stats_id,player_info_id) VALUES (9, 'dale steyn', 5, 3, 9, 9);

INSERT INTO team_table (team_id,team_name) VALUES (1, 'india');
INSERT INTO team_table (team_id,team_name) VALUES (2, 'england');
INSERT INTO team_table (team_id,team_name) VALUES (3, 'south africa');

INSERT INTO player_info_table (player_info_id,debut_date,batting_type,bowling_type) VALUES (1, '1990-07-21', 'right handed', 'right handed');
INSERT INTO player_info_table (player_info_id,debut_date,batting_type,bowling_type) VALUES (2, '1990-09-08', 'right handed', 'right handed');
INSERT INTO player_info_table (player_info_id,debut_date,batting_type,bowling_type) VALUES (3, '1990-07-23', 'right handed', 'right handed');
INSERT INTO player_info_table (player_info_id,debut_date,batting_type,bowling_type) VALUES (4, '1990-09-14', 'right handed', 'right handed');
INSERT INTO player_info_table (player_info_id,debut_date,batting_type,bowling_type) VALUES (5, '1984-07-13', 'right handed', 'right handed');
INSERT INTO player_info_table (player_info_id,debut_date,batting_type,bowling_type) VALUES (6, '1981-07-07', 'right handed', 'right handed');
INSERT INTO player_info_table (player_info_id,debut_date,batting_type,bowling_type) VALUES (7, '1988-12-06', 'left handed', 'left handed');
INSERT INTO player_info_table (player_info_id,debut_date,batting_type,bowling_type) VALUES (8, '1983-12-06', 'right handed', 'right handed');
INSERT INTO player_info_table (player_info_id,debut_date,batting_type,bowling_type) VALUES (9, '1983-06-27', 'right handed', 'right handed');

INSERT INTO stats_table (stats_id,bat_id,ball_id) VALUES (1, 1, 1);
INSERT INTO stats_table (stats_id,bat_id,ball_id) VALUES (2, 2, 2);
INSERT INTO stats_table (stats_id,bat_id,ball_id) VALUES (3, 3, 3);
INSERT INTO stats_table (stats_id,bat_id,ball_id) VALUES (4, 4, 4);
INSERT INTO stats_table (stats_id,bat_id,ball_id) VALUES (5, 5, 5);
INSERT INTO stats_table (stats_id,bat_id,ball_id) VALUES (6, 6, 6);
INSERT INTO stats_table (stats_id,bat_id,ball_id) VALUES (7, 7, 7);
INSERT INTO stats_table (stats_id,bat_id,ball_id) VALUES (8, 8, 8);
INSERT INTO stats_table (stats_id,bat_id,ball_id) VALUES (9, 9, 9);

INSERT INTO stats_bat_table (bat_id,matches_played,runs_scored,centuries,average,strike_rate,best) VALUES (1, 101, 3833, 10, 41.22, 107.58, 180);
INSERT INTO stats_bat_table (bat_id,matches_played,runs_scored,centuries,average,strike_rate,best) VALUES (2, 151, 4120, 10, 41.2, 121.28, 162);
INSERT INTO stats_bat_table (bat_id,matches_played,runs_scored,centuries,average,strike_rate,best) VALUES (3, 61, 66, 0, 9.43, 62.26, 18);
INSERT INTO stats_bat_table (bat_id,matches_played,runs_scored,centuries,average,strike_rate,best) VALUES (4, 7, 267, 0, 53.4, 103.9, 64);
INSERT INTO stats_bat_table (bat_id,matches_played,runs_scored,centuries,average,strike_rate,best) VALUES (5, 143, 5507, 12, 47.47, 88.60, 185);
INSERT INTO stats_bat_table (bat_id,matches_played,runs_scored,centuries,average,strike_rate,best) VALUES (6, 350, 10773, 10, 50.58, 87.56, 183);
INSERT INTO stats_bat_table (bat_id,matches_played,runs_scored,centuries,average,strike_rate,best) VALUES (7, 168, 2411, 0, 32.58, 87.7, 87);
INSERT INTO stats_bat_table (bat_id,matches_played,runs_scored,centuries,average,strike_rate,best) VALUES (8, 70, 45, 0, 6.43, 51.72, 14);
INSERT INTO stats_bat_table (bat_id,matches_played,runs_scored,centuries,average,strike_rate,best) VALUES (9, 125, 365, 0, 9.36, 64.95, 60);

INSERT INTO stats_ball_table (ball_id,matches_played,wickets,hatricks,economy,best,runouts,stumps) VALUES (1, 100, 120, 2, 5, "3-19", 5, 0);
INSERT INTO stats_ball_table (ball_id,matches_played,wickets,hatricks,economy,best,runouts,stumps) VALUES (2, 100, 120, 2, 5, "3-19", 5, 0);
INSERT INTO stats_ball_table (ball_id,matches_played,wickets,hatricks,economy,best,runouts,stumps) VALUES (3, 100, 120, 2, 5, "3-19", 5, 0);
INSERT INTO stats_ball_table (ball_id,matches_played,wickets,hatricks,economy,best,runouts,stumps) VALUES (4, 100, 120, 2, 5, "3-19", 5, 0);
INSERT INTO stats_ball_table (ball_id,matches_played,wickets,hatricks,economy,best,runouts,stumps) VALUES (5, 100, 120, 2, 5, "3-19", 5, 0);
INSERT INTO stats_ball_table (ball_id,matches_played,wickets,hatricks,economy,best,runouts,stumps) VALUES (6, 100, 120, 2, 5, "3-19", 5, 0);
INSERT INTO stats_ball_table (ball_id,matches_played,wickets,hatricks,economy,best,runouts,stumps) VALUES (7, 100, 120, 2, 5, "3-19", 5, 0);
INSERT INTO stats_ball_table (ball_id,matches_played,wickets,hatricks,economy,best,runouts,stumps) VALUES (8, 100, 120, 2, 5, "3-19", 5, 0);
INSERT INTO stats_ball_table (ball_id,matches_played,wickets,hatricks,economy,best,runouts,stumps) VALUES (9, 100, 120, 2, 5, "3-19", 5, 0);

-- 1. get the list of cricketers which should include their name and team name

-- select name, TT.team_name from player_table PT
-- inner join team_table TT on PT.team_id = TT.team_id;

-- 2. get the list of players who plays for a particular team (ex: india)

-- select name from player_table PT
-- inner join team_table TT on PT.team_id = TT.team_id
-- where TT.team_name = "india";

-- 3. get the count of players playing in each team

-- select count(name), TT.team_name from player_table PT
-- inner join team_table TT on PT.team_id = TT.team_id
-- group by TT.team_name

-- 4. get the third highest run getter and runs scored by him

-- select name, (select SBT.runs_scored from stats_table ST
--     inner join stats_bat_table SBT on ST.bat_id = SBT.bat_id
--     order by SBT.runs_scored desc limit 2, 1
-- ) 
-- from player_table PT where stats_id in
-- (select stats_id from stats_table ST
--     inner join stats_bat_table SBT on ST.bat_id = SBT.bat_id
--     order by SBT.runs_scored desc limit 2, 1
-- )

-- 5. get the average centuries from each team

-- select avg(A.strike_rate) from player_table
-- inner join team_table on player_table.team_id = team_table.team_id
-- join (select * from stats_table
-- inner join stats_bat_table on stats_table.bat_id = stats_bat_table.bat_id)
-- as A on player_table.stats_id = A.stats_id group by team_name

-- 6. get the player name along with their name and team name which they are representing

-- select name, TT.team_name,
-- (select name from player_table where PT.captain_id = player_id)
-- from player_table PT
-- inner join team_table TT on PT.team_id = TT.team_id;

-- 7. get the player names who debuted after ‘1990-01-01’ and has atleast 1 century

-- select name from player_table PT
-- where PT.stats_id in
-- (select stats_id from stats_table ST
-- inner join stats_bat_table SBT on ST.bat_id = SBT.bat_id
-- where SBT.centuries > 0
-- )
-- and PT.player_info_id in
-- (select PIT.player_info_id from player_table PT
-- inner join player_info_table PIT on PT.player_info_id = PIT.player_info_id
-- where PIT.debut_date > '1990-01-01'
-- )

-- 8. get the player name whose captain has a minimum of 90 strike rate

-- select PT.name from player_table PT
-- inner join player_table PPT
-- where PT.captain_id = PPT.player_id and 
-- PPT.stats_id in 
-- (select ST.stats_id from stats_table ST
-- inner join stats_bat_table SBT on ST.bat_id = SBT.bat_id
-- where SBT.strike_rate > 90)

-- 9. get the player name, team name whose captain has born after ‘1990-01-01’

-- select PT.name from player_table PT
-- inner join player_table PPT
-- where PT.captain_id = PPT.player_id and 
-- PPT.player_info_id in 
-- (select player_info_id from player_info_table
-- where debut_date > '1990-01-01') 