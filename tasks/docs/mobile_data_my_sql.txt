
--1.

CREATE TABLE mobile_brand_table 
(
    mobile_brand varchar(300),
    b_id int  primary key
);

INSERT INTO mobile_brand_table (mobile_brand,b_id) VALUES ('apple', '1');
INSERT INTO mobile_brand_table (mobile_brand,b_id) VALUES ('samsung', '2');
INSERT INTO mobile_brand_table (mobile_brand,b_id) VALUES ('oppo', '3');
INSERT INTO mobile_brand_table (mobile_brand,b_id) VALUES ('vivo', '4');

--2.

CREATE TABLE mobile_model_table 
(
    model_name varchar(300),
    model_id int primary key,
    b_id	int,
    foreign key b_id references  mobile_brand_table (b_id)
);

INSERT INTO mobile_model_table (model_name,model_id,b_id) VALUES ('iphone12', '101', '1');
INSERT INTO mobile_model_table (model_name,model_id,b_id) VALUES ('iphone13', '102', '1');
INSERT INTO mobile_model_table (model_name,model_id,b_id) VALUES ('iphone14', '103', '1');
INSERT INTO mobile_model_table (model_name,model_id,b_id) VALUES ('s20', '201', '2');
INSERT INTO mobile_model_table (model_name,model_id,b_id) VALUES ('s21', '202', '2');
INSERT INTO mobile_model_table (model_name,model_id,b_id) VALUES ('s22', '203', '2');
INSERT INTO mobile_model_table (model_name,model_id,b_id) VALUES ('f9pro', '301', '3');
INSERT INTO mobile_model_table (model_name,model_id,b_id) VALUES ('f10pro', '302', '3');
INSERT INTO mobile_model_table (model_name,model_id,b_id) VALUES ('f20pro', '303', '3');
INSERT INTO mobile_model_table (model_name,model_id,b_id) VALUES ('v21', '401', '4');
INSERT INTO mobile_model_table (model_name,model_id,b_id) VALUES ('v30', '402', '4');
INSERT INTO mobile_model_table (model_name,model_id,b_id) VALUES ('v40', '403', '4');


--3.

CREATE TABLE mobile_manufacturer_table 
(
    m_id int primary key,
    manufacturer_place varchar(300),
    b_id int,
    foreign key b_id references  mobile_brand_table (b_id)
);

INSERT INTO mobile_manufacturer_table (m_id,manufacturer_place,b_id) VALUES ('101', 'hyderabad', '1');
INSERT INTO mobile_manufacturer_table (m_id,manufacturer_place,b_id) VALUES ('102', 'visakhapatnam', '1');
INSERT INTO mobile_manufacturer_table (m_id,manufacturer_place,b_id) VALUES ('201', 'chennai', '2');
INSERT INTO mobile_manufacturer_table (m_id,manufacturer_place,b_id) VALUES ('202', 'delhi', '2');
INSERT INTO mobile_manufacturer_table (m_id,manufacturer_place,b_id) VALUES ('301', 'banglore', '3');
INSERT INTO mobile_manufacturer_table (m_id,manufacturer_place,b_id) VALUES ('302', 'mysore', '3');
INSERT INTO mobile_manufacturer_table (m_id,manufacturer_place,b_id) VALUES ('401', 'mumbai', '4');
INSERT INTO mobile_manufacturer_table (m_id,manufacturer_place,b_id) VALUES ('402', 'pune', '4');


--4.

CREATE TABLE mobile_vendor_table 
(
    vendor_name varchar(300),
    vendor_id int primary key
);

INSERT INTO mobile_vendor_table (vendor_name,vendor_id) VALUES ('amazon', '1001');
INSERT INTO mobile_vendor_table (vendor_name,vendor_id) VALUES ('flipkart', '1002');
INSERT INTO mobile_vendor_table (vendor_name,vendor_id) VALUES ('snapdeal', '1003');
INSERT INTO mobile_vendor_table (vendor_name,vendor_id) VALUES ('paytm', '1004');

--5.
CREATE TABLE customer_table 
(
    customer_name varchar(300),
    model_id int,
    vendor_id int,
    foreign key model_id references mobile_model_table(model_id),
    foreign key vendor_id references mobile_vendor_table(vendor_id),	
);

INSERT INTO customer_table (CUSTOMER_NAME,MODEL_ID,VENDOR_ID) VALUES ('HARSHA', '103', '1001');
INSERT INTO customer_table (CUSTOMER_NAME,MODEL_ID,VENDOR_ID) VALUES ('SREE', '103', '1002');
INSERT INTO customer_table (CUSTOMER_NAME,MODEL_ID,VENDOR_ID) VALUES ('SHYAM', '102', '1003');
INSERT INTO customer_table (CUSTOMER_NAME,MODEL_ID,VENDOR_ID) VALUES ('SAMBA', '102', '1004');
INSERT INTO customer_table (CUSTOMER_NAME,MODEL_ID,VENDOR_ID) VALUES ('SAHITHI', '203', '1002');
INSERT INTO customer_table (CUSTOMER_NAME,MODEL_ID,VENDOR_ID) VALUES ('DHANSUH', '301', '1004');
INSERT INTO customer_table (CUSTOMER_NAME,MODEL_ID,VENDOR_ID) VALUES ('SAI', '103', '1002');
INSERT INTO customer_table (CUSTOMER_NAME,MODEL_ID,VENDOR_ID) VALUES ('HARSHA', '103', '1004');
INSERT INTO customer_table (CUSTOMER_NAME,MODEL_ID,VENDOR_ID) VALUES ('TEJA', '101', '1003');
INSERT INTO customer_table (CUSTOMER_NAME,MODEL_ID,VENDOR_ID) VALUES ('BALI', '202', '1002');
INSERT INTO customer_table (CUSTOMER_NAME,MODEL_ID,VENDOR_ID) VALUES ('BABU', '401', '1001');
INSERT INTO customer_table (CUSTOMER_NAME,MODEL_ID,VENDOR_ID) VALUES ('HARI', '201', '1004');
INSERT INTO customer_table (CUSTOMER_NAME,MODEL_ID,VENDOR_ID) VALUES ('VISWAS', '403', '1002');
INSERT INTO customer_table (CUSTOMER_NAME,MODEL_ID,VENDOR_ID) VALUES ('HARSHU', '101', '1003');
INSERT INTO customer_table (CUSTOMER_NAME,MODEL_ID,VENDOR_ID) VALUES ('JAHU', '203', '1004');
INSERT INTO customer_table (CUSTOMER_NAME,MODEL_ID,VENDOR_ID) VALUES ('TARAK', '103', '1001');
INSERT INTO customer_table (CUSTOMER_NAME,MODEL_ID,VENDOR_ID) VALUES ('RAM', '203', '1004');
INSERT INTO customer_table (CUSTOMER_NAME,MODEL_ID,VENDOR_ID) VALUES ('RAO', '303', '1003');
INSERT INTO customer_table (CUSTOMER_NAME,MODEL_ID,VENDOR_ID) VALUES ('RAHIM', '401', '1004');



/* query 1: get the list of mobile models with their respective brand names   */

select 
mt.model_name,
bt.mobile_brand
from Mobile_model_table mt 
join Mobile_brand_table bt on mt.b_id = bt.b_id ;


/* query 2: get the list of mobile brands and there manufacturer location   */

select 

bt.mobile_brand,
mt.manufacturer_place
from Mobile_manufacturer_table mt 
join Mobile_brand_table bt on mt.b_id = bt.b_id ;

/* query 3: get the list of customers with their mobile model name and brand order by names of customers   */

select 
ct.customer_name,
mt.model_name,
bt.mobile_brand
from Mobile_model_table mt 
join Mobile_brand_table bt on mt.b_id = bt.b_id 
join Customer_table ct on ct.MODEL_ID = mt.model_id 
order by customer_name;
/* query 4: get the list of vendors and no of mobiles sold by them   */

select 
vt.vendor_name,
count(ct.CUSTOMER_NAME) as "no of mobiles sold"
from customer_table ct 
join Mobile_vendor_table vt on vt.vendor_id=ct.VENDOR_ID
group by vt.vendor_name;


/* query 5: get the costliest mobile with name and brand   */

select 
bt.mobile_brand,
mt.model_name,
mt.price
from Mobile_brand_table bt
join Mobile_model_table mt on mt.b_id=bt.b_id 
order by price desc
limit 1;
;

/* query 6: get the list of customers who bought apple mobile and include the model and vendor name  */

select 
ct.customer_name,
mt.model_name,
vt.vendor_name
from Mobile_brand_table bt
join Mobile_model_table mt on mt.b_id=bt.b_id
join Customer_table ct on ct.model_id = mt.model_id
join Mobile_vendor_table vt on vt.vendor_id=ct.vendor_id
where bt.mobile_brand="apple";


/* query 7: get the list of customers,model name , price where price of mobile range is 20000 to 70000 both prices exclusive  */

select 
ct.customer_name,
mt.model_name,
mt.price

from Mobile_brand_table bt
join Mobile_model_table mt on mt.b_id=bt.b_id
join Customer_table ct on ct.model_id = mt.model_id
where mt.price >20000 and mt.price < 70000 
order by mt.price;



/* query 8: get the list of mobile models with their respective brand names  and their respective manufacturing places */

select 
mt.model_name,
bt.mobile_brand,
at.manufacturer_place

from Mobile_model_table as mt 
join Mobile_brand_table as bt on mt.b_id = bt.b_id 
join Mobile_manufacturer_table as at on at.b_id = bt.b_id ;



/* query 9: get the list of cutomers and their modiles model and brand whose name starts with h   */

select 
ct.CUSTOMER_NAME,
mt.model_name,
bt.mobile_brand
from Mobile_model_table as mt 
join Customer_table as ct on  ct.model_id = mt.model_id
join Mobile_brand_table as bt on bt.b_id=mt.b_id
WHERE ct.Customer_Name LIKE 'h%'
;

/* query 10: get the list of customers who brougnt from amazon and apple brand and model iphone14  */

select 
ct.CUSTOMER_NAME,
mt.model_name,
bt.mobile_brand,
vt.vendor_name
from Mobile_model_table as mt 
join Customer_table as ct on  ct.model_id = mt.model_id
join Mobile_brand_table as bt on bt.b_id=mt.b_id
join Mobile_vendor_table as vt on vt.vendor_id=ct.vendor_id
WHERE vt.vendor_name="amazon" and  bt.mobile_brand="apple" and mt
