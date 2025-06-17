USE libraryDB;

CREATE TABLE books_log (
    BookID INT NOT NULL,
    Title VARCHAR(255),
    Author VARCHAR(255),
    Category VARCHAR(255),
    Status VARCHAR(10),
    Availability VARCHAR(10),
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE books (
    BookID INT AUTO_INCREMENT,
    Title VARCHAR(255) NOT NULL UNIQUE
    Author VARCHAR(255),
    Category VARCHAR(255),
    Status CHAR(1),
    Availability CHAR(1),
    PRIMARY KEY (`BookID`),
);
