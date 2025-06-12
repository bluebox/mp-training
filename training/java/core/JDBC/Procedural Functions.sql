drop procedure if exists updateMember;
delimiter $$
CREATE PROCEDURE updateMember (
	IN sid varchar(20),
    In sname varchar(20)
)  
Begin
    update student set Name=sname where StudentId=sid;
End$$

DELIMITER ;

