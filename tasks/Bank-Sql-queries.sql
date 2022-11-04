CREATE TABLE Bank_Details 
(
    bank_id	varchar(300) NOT NULL PRIMARY KEY,
    bank_name	varchar(300),
    IFSC	varchar(300)
);

INSERT INTO Bank_Details (bank_id,bank_name,IFSC) VALUES ('sbi01', 'state bank of india', 'SBIN008118');
INSERT INTO Bank_Details (bank_id,bank_name,IFSC) VALUES ('can02', 'canara bank', 'can01234');
INSERT INTO Bank_Details (bank_id,bank_name,IFSC) VALUES ('ub03', 'union bank', 'un567');
INSERT INTO Bank_Details (bank_id,bank_name,IFSC) VALUES ('bob04', 'bank of baroda', 'bob987');
INSERT INTO Bank_Details (bank_id,bank_name,IFSC) VALUES ('boi05', 'bank of india', 'boi789');
INSERT INTO Bank_Details (bank_id,bank_name,IFSC) VALUES ('pun06', 'punjabi bank', 'pun10');
INSERT INTO Bank_Details (bank_id,bank_name,IFSC) VALUES ('kmb07', 'kotak mahindra bank', 'kmb143');
INSERT INTO Bank_Details (bank_id,bank_name,IFSC) VALUES ('andb08', 'andhra bank', 'andb9666');
INSERT INTO Bank_Details (bank_id,bank_name,IFSC) VALUES ('ab09', 'axis bank', 'axb957');



CREATE TABLE User_Details 
(
    user_id	varchar(300),
    user_name	varchar(300),
    phone_number	varchar(300),
    user_location	varchar(300),
    bank_id	varchar(300)
    FOREIGN KEY bank_id REFERENCES Bank_Details(bank_id)
);

INSERT INTO User_Details (user_id,user_name,phone_number,user_location,bank_id) VALUES ('1', 'hari', '9847868757', 'kadapa', 'sbi01');
INSERT INTO User_Details (user_id,user_name,phone_number,user_location,bank_id) VALUES ('2', 'harsha', '6238798982', 'samalkota', 'can02');
INSERT INTO User_Details (user_id,user_name,phone_number,user_location,bank_id) VALUES ('3', 'shyam', '9666497194', 'kakinada', 'sbi01');
INSERT INTO User_Details (user_id,user_name,phone_number,user_location,bank_id) VALUES ('4', 'achyuth', '9767697233', 'vijayawada', 'can02');
INSERT INTO User_Details (user_id,user_name,phone_number,user_location,bank_id) VALUES ('5', 'prasad', '9505058499', 'pedapudi', 'sbi01');
INSERT INTO User_Details (user_id,user_name,phone_number,user_location,bank_id) VALUES ('6', 'venky', '8239889237', 'pedapudi', 'sbi01');
INSERT INTO User_Details (user_id,user_name,phone_number,user_location,bank_id) VALUES ('7', 'sanath', '7731076677', 'kakinada', 'sbi01');
INSERT INTO User_Details (user_id,user_name,phone_number,user_location,bank_id) VALUES ('8', 'mohan', '7826389798', 'kakinada', 'ub03');
INSERT INTO User_Details (user_id,user_name,phone_number,user_location,bank_id) VALUES ('9', 'uday kiran', '6788309509', 'kakinada', 'bob04');
INSERT INTO User_Details (user_id,user_name,phone_number,user_location,bank_id) VALUES ('10', 'ram', '7234688929', 'tuni', 'kmb07');
INSERT INTO User_Details (user_id,user_name,phone_number,user_location,bank_id) VALUES ('11', 'varun', '8379995985', 'pedapudi', 'bob04');
INSERT INTO User_Details (user_id,user_name,phone_number,user_location,bank_id) VALUES ('12', 'dhanush', '2639875937', 'vishakapatnam', 'ab09');
INSERT INTO User_Details (user_id,user_name,phone_number,user_location,bank_id) VALUES ('13', 'tharun', '9679292998', 'vishakapatnam', 'boi05');
INSERT INTO User_Details (user_id,user_name,phone_number,user_location,bank_id) VALUES ('14', 'sambha', '8976594860', 'hyderabad', 'pun06');
INSERT INTO User_Details (user_id,user_name,phone_number,user_location,bank_id) VALUES ('15', 'srikar', '7236498928', 'hyderabad', 'bob04');
INSERT INTO User_Details (user_id,user_name,phone_number,user_location,bank_id) VALUES ('16', 'nagendra', '8397498299', 'pedapudi', 'kmb07');
INSERT INTO User_Details (user_id,user_name,phone_number,user_location,bank_id) VALUES ('17', 'ravi', '8923749825', 'samarlakota', 'andb08');
INSERT INTO User_Details (user_id,user_name,phone_number,user_location,bank_id) VALUES ('18', 'sahiran', '7892374923', 'siddhipet', 'boi05');





CREATE TABLE Location_Table 
(
    location_id	varchar(300) NOT NULL PRIMARY KEY,
    location_name	varchar(300)
);

INSERT INTO Location_Table (location_id,location_name) VALUES ('L1', 'samarlakota');
INSERT INTO Location_Table (location_id,location_name) VALUES ('L2', 'kakinada');
INSERT INTO Location_Table (location_id,location_name) VALUES ('L3', 'pedapudi');
INSERT INTO Location_Table (location_id,location_name) VALUES ('L4', 'peddapuram');
INSERT INTO Location_Table (location_id,location_name) VALUES ('L5', 'rajahmundry');
INSERT INTO Location_Table (location_id,location_name) VALUES ('L6', 'vizag');
INSERT INTO Location_Table (location_id,location_name) VALUES ('L7', 'mandapeta');
INSERT INTO Location_Table (location_id,location_name) VALUES ('L8', 'mamidada');
INSERT INTO Location_Table (location_id,location_name) VALUES ('L9', 'tuni');
INSERT INTO Location_Table (location_id,location_name) VALUES ('L10', 'annavaram');
INSERT INTO Location_Table (location_id,location_name) VALUES ('L11', 'vijayawada');
INSERT INTO Location_Table (location_id,location_name) VALUES ('L12', 'eluru');






CREATE TABLE Junction_Table 
(
    id	INT,
    bank_id	varchar(300),
    location_id	varchar(300),
    FOREIGN KEY (bank_id) REFERENCES Bank_Details(bank_id),
    FOREIGN KEY (location_id) REFERENCES Location_Table(location_id)
);

INSERT INTO Junction_Table (id,bank_id,location_id) VALUES ('1', 'sbi01', 'L3');
INSERT INTO Junction_Table (id,bank_id,location_id) VALUES ('2', 'can02', 'L6');
INSERT INTO Junction_Table (id,bank_id,location_id) VALUES ('3', 'ub03', 'L8');
INSERT INTO Junction_Table (id,bank_id,location_id) VALUES ('4', 'bob04', 'L9');
INSERT INTO Junction_Table (id,bank_id,location_id) VALUES ('5', 'boi05', 'L12');
INSERT INTO Junction_Table (id,bank_id,location_id) VALUES ('6', 'pun06', 'L5');
INSERT INTO Junction_Table (id,bank_id,location_id) VALUES ('7', 'kmb07', 'L7');
INSERT INTO Junction_Table (id,bank_id,location_id) VALUES ('8', 'andb08', 'L2');
INSERT INTO Junction_Table (id,bank_id,location_id) VALUES ('9', 'ab09', 'L6');
INSERT INTO Junction_Table (id,bank_id,location_id) VALUES ('10', 'sbi01', 'L10');
INSERT INTO Junction_Table (id,bank_id,location_id) VALUES ('11', 'boi05', 'L1');
INSERT INTO Junction_Table (id,bank_id,location_id) VALUES ('12', 'kmb07', 'L1');
INSERT INTO Junction_Table (id,bank_id,location_id) VALUES ('13', 'bob04', 'L3');
INSERT INTO Junction_Table (id,bank_id,location_id) VALUES ('14', 'sbi01', 'L5');



CREATE TABLE Account_Table 
(
    balance_id	varchar(300) NOT NULL PRIMARY KEY,
    balance	varchar(300)
);

INSERT INTO Account_Table (balance_id,balance) VALUES ('1', '20000');
INSERT INTO Account_Table (balance_id,balance) VALUES ('2', '12000');
INSERT INTO Account_Table (balance_id,balance) VALUES ('3', '5000');
INSERT INTO Account_Table (balance_id,balance) VALUES ('4', '100000');
INSERT INTO Account_Table (balance_id,balance) VALUES ('5', '80000');
INSERT INTO Account_Table (balance_id,balance) VALUES ('6', '1000');
INSERT INTO Account_Table (balance_id,balance) VALUES ('7', '2');
INSERT INTO Account_Table (balance_id,balance) VALUES ('8', '1500');
INSERT INTO Account_Table (balance_id,balance) VALUES ('9', '200000');
INSERT INTO Account_Table (balance_id,balance) VALUES ('10', '10');
INSERT INTO Account_Table (balance_id,balance) VALUES ('11', '65000');
INSERT INTO Account_Table (balance_id,balance) VALUES ('12', '15000');
INSERT INTO Account_Table (balance_id,balance) VALUES ('13', '45000');
INSERT INTO Account_Table (balance_id,balance) VALUES ('14', '500000');
INSERT INTO Account_Table (balance_id,balance) VALUES ('15', '150');
INSERT INTO Account_Table (balance_id,balance) VALUES ('16', '18000');
INSERT INTO Account_Table (balance_id,balance) VALUES ('17', '22000');
INSERT INTO Account_Table (balance_id,balance) VALUES ('18', '99999');




--QUERY -1 (person_name, bank_name,account_balance)

SELECT 
	user_name,
	bank_name,
	balance

FROM
	User_Details NATURAL JOIN Bank_Details JOIN Account_Table ON Account_Table.balance_id = User_Details.user_id


--QUERY -2 (number of users for each bank)

SELECT 
	COUNT(user_name) as number_of_users_in_each_bank,
	bank_name

FROM
	User_Details NATURAL JOIN Bank_Details 
GROUP BY 
	Bank_Details.bank_name


--QUERY - 3 (get user names whose location same as their bank location)

SELECT 
	a.user_name,
	a.user_location,
	c.location_name as bank_location

FROM
	User_Details a JOIN Junction_Table b ON a.bank_id = b.bank_id JOIN Location_Table c ON c.location_id = b.location_id
WHERE 
	 a.user_location = c.location_name
	 
--QUERY - 4 (Top 5 highest account balance holders)

SELECT 
	a.user_name,
	b.balance

FROM
	User_Details a JOIN Account_Table b ON a.user_id = b.balance_id 
ORDER BY 
	balance DESC
LIMIT 5


--QUERY - 5 (full details of everyuser)

SELECT 
	user_name,
	bank_name,
	IFSC,
	user_location,
	location_name As bank_location,
	balance

FROM
	User_Details a INNER JOIN Account_Table b ON a.user_id = b.balance_id JOIN
    	Bank_Details c ON c.bank_id = a.bank_id JOIN Junction_Table d ON d.bank_id = c.bank_id NATURAL JOIN Location_Table 


	


