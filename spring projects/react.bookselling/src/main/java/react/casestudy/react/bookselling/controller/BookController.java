package react.casestudy.react.bookselling.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import react.casestudy.react.bookselling.domain.Book;
import react.casestudy.react.bookselling.service.BookService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        int result = bookService.addBook(book);
        return result > 0 ? ResponseEntity.ok("Book added successfully")
                          : ResponseEntity.badRequest().body("Failed to add book");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable int id, @RequestBody Book book) {
        boolean updated = bookService.updateBook(id, book);
        return updated ? ResponseEntity.ok("Book updated successfully")
                       : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooksByName(@RequestParam String title) {
        return ResponseEntity.ok(bookService.searchBooksByName(title));
    }
}

/*
create database mystore;
use mystore;

create table Book( bookId Int primary key auto_increment  , 
title VARCHAR(255) NOT NULL , author VARCHAR(255), 
categroy VARCHAR(200) ,cost DECIMAL ,quantity int CHECK(quantity >=0 ),  published DATE );

create table Member(memberId int primary key auto_increment , name varchar(255) NOT NULL , 
email varchar(255) not null unique,  mobile BIGINT NOT NULL UNIQUE ,age int, 
gender char(1) NOT NULL CHECK (gender in ('M' , 'F' )),
address VARCHAR(255) 
);

CREATE TABLE Orders (
    orderId INT PRIMARY KEY AUTO_INCREMENT,
    memberId INT NOT NULL,
    totalCost DECIMAL(10,2) NOT NULL DEFAULT 0,
    purchaseDate DATE,
    FOREIGN KEY (memberId) REFERENCES Member(memberId)
);

-- OrderHistory Table (added PRIMARY KEY and fixed types)
CREATE TABLE OrderHistory (
    orderId INT,
    bookId INT,
    quantity INT,
    totalCost DECIMAL(10,2),
    PRIMARY KEY (orderId, bookId),
    FOREIGN KEY (orderId) REFERENCES Orders(orderId),
    FOREIGN KEY (bookId) REFERENCES Book(bookId)
);

INSERT INTO Book (title, author, categroy, cost, quantity, published) VALUES
('Clean Code', 'Robert C. Martin', 'Programming', 550.00, 10, '2008-08-01'),
('Atomic Habits', 'James Clear', 'Self-help', 450.00, 15, '2018-10-16'),
('The Pragmatic Programmer', 'Andrew Hunt', 'Programming', 600.00, 8, '1999-10-20'),
('The Alchemist', 'Paulo Coelho', 'Fiction', 300.00, 20, '1988-04-15'),
('Deep Work', 'Cal Newport', 'Productivity', 500.00, 12, '2016-01-05'),
('Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', 'History', 750.00, 5, '2011-06-04'),
('Thinking, Fast and Slow', 'Daniel Kahneman', 'Psychology', 670.00, 7, '2011-10-25'),
('Introduction to Algorithms', 'Thomas H. Cormen', 'Computer Science', 950.00, 6, '2009-07-31'),
('To Kill a Mockingbird', 'Harper Lee', 'Classic', 350.00, 14, '1960-07-11'),
('1984', 'George Orwell', 'Dystopian', 400.00, 9, '1949-06-08');

select * from Book;

INSERT INTO Member (name, email, mobile, age, gender, address) VALUES
('Nagabhushan K', 'naga@example.com', 9876543210, 23, 'M', 'Madanapalle'),
('Sravani R', 'sravani@example.com', 9123456789, 22, 'F', 'Hyderabad'),
('Arjun V', 'arjunv@example.com', 9988776655, 25, 'M', 'Bangalore'),
('Priya M', 'priya.m@example.com', 9001234567, 24, 'F', 'Chennai'),
('Rahul S', 'rahul.s@example.com', 8899776655, 28, 'M', 'Mumbai'),
('Meena L', 'meena.l@example.com', 9665544332, 21, 'F', 'Pune'),
('Raj K', 'raj.k@example.com', 8877665544, 27, 'M', 'Delhi'),
('Kavya D', 'kavya.d@example.com', 9776655443, 23, 'F', 'Visakhapatnam'),
('Vikram R', 'vikram.r@example.com', 9654321987, 26, 'M', 'Kolkata'),
('Aishwarya T', 'aishu.t@example.com', 9543217890, 22, 'F', 'Coimbatore');

select * from Member;

select * from Orders;
select * from OrderHistory;

 */
