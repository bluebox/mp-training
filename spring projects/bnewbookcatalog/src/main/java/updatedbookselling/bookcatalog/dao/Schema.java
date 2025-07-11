/*
create database book_catalog;
use book_catalog;

create table Book( 
bookId Int primary key auto_increment  , 
title VARCHAR(255) NOT NULL , 
cost DECIMAL NOT NULL,
quantity int CHECK(quantity >=0 ),
discount DECIMAL
);

create table Member(
memberId int primary key auto_increment , 
name varchar(255) NOT NULL , 
roles varchar(10) not null check (roles in ("user" , "admin")),
memberPassword varchar(255) not null , 
);

create table Discount(
id int primary key auto_increment ,
price decimal not null ,
discount decimal not null
);

CREATE TABLE Orders (
    orderId INT PRIMARY KEY AUTO_INCREMENT,
    memberId INT NOT NULL,
    totalBooks int not null,
    booksDiscount decimal ,
    slabDiscount decimal,
    originalPrice decimal,
    finalPrice DECIMAL(10,2) NOT NULL DEFAULT 0,
    purchaseDate DATE,
    FOREIGN KEY (memberId) REFERENCES Member(memberId)
);

CREATE TABLE OrderHistory (
    orderId INT,
    bookId INT,
    quantity INT,
    originalPrice DECIMAL(10,2),
    finalPrice Decimal(10,2),
    PRIMARY KEY (orderId, bookId),
    FOREIGN KEY (orderId) REFERENCES Orders(orderId),
    FOREIGN KEY (bookId) REFERENCES Book(bookId)
);

create table wishlist(
wishlistId int primary key auto_increment,
memberId int not null,
bookId int not null,
FOREIGN KEY (memberId) REFERENCES Member(memberId),
FOREIGN KEY (bookId) REFERENCES Book(bookId)
);


*/

package updatedbookselling.bookcatalog.dao;

public class Schema {

}
