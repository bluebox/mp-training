show databases;

use social_media_database;
CREATE TABLE User(
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NULL DEFAULT NULL,
  `firstName` VARCHAR(50) NULL DEFAULT NULL,
  `lastName` VARCHAR(50) NULL DEFAULT NULL,
  `mobile` VARCHAR(15) NULL,
  `email` VARCHAR(50) NULL,
  `password` VARCHAR(32) NOT NULL,
  `register time` DATETIME NOT NULL,
  `lastLogin time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_username` (`username` ASC),
  UNIQUE INDEX `uq_mobile` (`mobile` ASC),
  UNIQUE INDEX `uq_email` (`email` ASC) );
  
  
  CREATE TABLE social_media_database.Follower(
  `id` INT NOT NULL AUTO_INCREMENT,
  `sourceUserId` INT NOT NULL,
  `targetFollowerId` INT NOT NULL,
  `createdAt` DATETIME NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `index_follower_source` (`sourceUserId` ASC),
  INDEX `index_follower_target` (`targetFollowerId` ASC),
  CONSTRAINT `fk_ufollower_source`
  FOREIGN KEY (`sourceUserId`)
  REFERENCES social_media_database.User(`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    
-- altering the targetfollowerid
alter table social_media_database.Follower add
 constraint fk_ufollower_target
FOREIGN KEY (targetFollowerId)
REFERENCES social_media_database.User(`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION;	
     
     
     
 -- add altering the unique valies to sourceUserid and TartgetFollowerId 
 alter table social_media_database.Follower add 
 unique uniquelist_me_follow(sourceUserId,targetFollowerId);
 
 
 
 create table  Profile(
 id  int not null auto_increment,
 user_id int not null,
 image  blob,
 location varchar(40),
 working varchar(20),
 biography varchar(500),
 status varchar(20),
  CONSTRAINT  FOREIGN KEY (user_id)  
  REFERENCES User(id)  
  ON DELETE CASCADE  
  ON UPDATE CASCADE  ,
  primary key(id));
 
--  select * from Profile;
--  select * from Follower;
--  select * from User;
 

-- --creating    post table 
  create table post (
  id int not null auto_increment,
  userid int not null,
  profile_id int not null,
  senderPostId int not null,
  message text null default null,
  image blob,
  posted_at datetime not null,
  index index_upost_id (userid asc),
   PRIMARY KEY (`id`),
  constraint foreign key (userid)
  references User(id)
  ON DELETE NO ACTION
    ON UPDATE NO ACTION,
   constraint foreign key (profile_id)
  references Profile(id)
  ON DELETE NO ACTION
    ON UPDATE NO ACTION);
  alter table post add 
  foreign key(senderPostId) references User(id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;


alter table post add column no_of_likes int not null ;
ALTER TABLE post Modify column no_of_likes varchar(10) null default '0';  

--  select * from post;
-- use Training;
-- select * from Animals;

 -- creating the message table
 create table MessageUser(
 id int not null auto_increment,
 senderid int not null,
 receiverid int not null,
 message tinytext null default null,
 send_at datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
`updated_at` DATETIME NULL DEFAULT NULL,
primary key(id),
index index_message_sender(senderid asc),
index index_message_receiver(receiverid asc),
constraint fk_umessage_sender foreign key(senderid asc)
references User(id)
on delete no action
on update no action,
constraint fk_umessage_receiver foreign key(receiverid asc)
references User(id)
on delete no action
on update no action);



create  table postLikes(
id int not null auto_increment,
postid int not null,
likedby int not null,
posteduserId int not null,
primary key(id),
constraint fk__umessage_postid foreign key(postid asc)
references post(id)
on delete no action
on update no action,
constraint fk__uliked_likedby foreign key(likedby asc)
references User(id)
on delete no action
on update no action,
constraint fk__uliked_posteduserId foreign key(posteduserId asc)
references User(id)
on delete no action
on update no action,
index index_liked_postId(postid asc),
index index_liked_postLikedby(likedby asc),
index index_liked_postUserId(posteduserId asc)) ;





create  table postcomments(
id int not null auto_increment,
postid int not null,
commentedUserby int not null,
posteduserId int not null,
primary key(id),
constraint fk__ucomment_postid foreign key(postid asc)
references post(id)
on delete no action
on update no action,
constraint fk__ucomment_commentedby foreign key(commentedUserby asc)
references User(id)
on delete no action
on update no action,
constraint fk__ucomment_posteduserId foreign key(posteduserId asc)
references User(id)
on delete no action
on update no action);

