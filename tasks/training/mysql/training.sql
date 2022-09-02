use training;
create database training;
drop database trainig;
create table CRICKET_BOARDS(
       board_id varchar(20) primary  key,
       board_name varchar(50) Not null,
       board_president varchar(20) not null
);

create table COACH(
       coach_id varchar(20) primary key,
       coach_name varchar(30) not null,
       coach_country varchar(20),
       coach_exp int
);

create table TEAMS(
       team_id varchar(20) primary key,
       team_name varchar(20) Not null,
       team_rank int not null,
       board_id varchar(20),
       coach_id varchar(20),
       captain_id varchar(20),
       foreign key(board_id) references CRICKET_BOARDS(board_id),
       foreign key(coach_id) references COACH(coach_id),
        foreign key(captain_id) references CAPTAINS(captain_id)
);

create table RUNS(
       batsman_id varchar(30) primary key,
       type_of_batsman varchar(30),
       test_runs int,
       odi_runs int,
       t20_runs int
);

create table WICKETS(
        bowler_id varchar(30) primary key,
        type_of_bowler varchar(30),
        test_wkts int,
        odi_wkts int,
        t20_wkts int
);

create table WK(
       wk_id varchar(20) primary key,
       no_of_catches int,
       no_of_stumpings int,
       no_of_runouts int
);

create table CAPTAINS(
       captain_id varchar(20) primary key,
       no_of_wins int,
       no_of_loses int
);

create table PLAYERS(
       player_id varchar(20) primary key,
       player_name varchar(30) not null,
       player_age int,
       matches int,
       player_country varchar(30) not null,
       batsman_id varchar(30),
       bowler_id varchar(30),
       wk_id varchar(20),
       team_id varchar(20),
       captain_id varchar(20),
       foreign key(batsman_id) references RUNS(batsman_id),
       foreign key(bowler_id) references WICKETS(bowler_id),
       foreign key(wk_id) references WK(wk_id),
       foreign key(team_id) references TEAMS(team_id),
       foreign key(captain_id) references CAPTAINS(captain_id)
);