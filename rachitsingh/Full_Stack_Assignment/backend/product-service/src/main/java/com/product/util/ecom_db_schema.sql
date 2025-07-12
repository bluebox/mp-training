CREATE DATABASE ecom_db;
USE ecom_db;

CREATE TABLE users (
  UserId           BIGINT AUTO_INCREMENT PRIMARY KEY,
  Username         VARCHAR(30) NOT NULL UNIQUE,
  Email            VARCHAR(100) NOT NULL UNIQUE,
  FullName         VARCHAR(100) NOT NULL,
  Phone            VARCHAR(20) NOT NULL,
  Gender           CHAR(1) NOT NULL,
  Age              INT NOT NULL,
  Password         VARCHAR(100) NOT NULL,
  Role             VARCHAR(20) NOT NULL NOT NULL,
  CreatedAt        DATETIME DEFAULT CURRENT_TIMESTAMP 
);

CREATE TABLE category_requests (
  CategoryRequestId     INT AUTO_INCREMENT PRIMARY KEY,
  CategoryName          VARCHAR(100) NOT NULL,
  RequestedBy           BIGINT NOT NULL,
  Status                CHAR(1) NOT NULL DEFAULT 'P',
  ApprovedBy            BIGINT,
  CreatedAtDate         DATETIME DEFAULT CURRENT_TIMESTAMP,
  UpdatedAtDate         DATETIME,
  FOREIGN KEY (RequestedBy) REFERENCES users(UserId),
  FOREIGN KEY (ApprovedBy) REFERENCES users(UserId)
);

CREATE TABLE categories (
  CategoryId            INT AUTO_INCREMENT PRIMARY KEY,
  CategoryName          VARCHAR(50) UNIQUE,
  CreatedAtDate         DATETIME DEFAULT CURRENT_TIMESTAMP,
  UpdatedAtDate         DATETIME
);

CREATE TABLE product_requests (
  ProductRequestId      BIGINT AUTO_INCREMENT PRIMARY KEY,
  ProductName           VARCHAR(150) NOT NULL,
  Description           TEXT NOT NULL,
  Price                 DECIMAL(10,2) NOT NULL,
  Quantity              INT NOT NULL,
  RequestedBy           BIGINT NOT NULL,
  Status                CHAR(1) NOT NULL DEFAULT 'P',
  ApprovedBy            BIGINT,
  CreatedAtDate         DATETIME DEFAULT CURRENT_TIMESTAMP,
  UpdatedAtDate         DATETIME,
  FOREIGN KEY (RequestedBy) REFERENCES users(UserId),
  FOREIGN KEY (ApprovedBy)  REFERENCES users(UserId)
);

CREATE TABLE products (
  ProductId             BIGINT AUTO_INCREMENT PRIMARY KEY,
  ProductName           VARCHAR(150) UNIQUE NOT NULL,
  Description           TEXT NOT NULL,
  Price                 DECIMAL(10,2) NOT NULL,
  Quantity              INT NOT NULL,
  AverageRating         DECIMAL(3,2) DEFAULT 0.0,
  CreatedAtDate         DATETIME DEFAULT CURRENT_TIMESTAMP,
  UpdatedAtDate         DATETIME
);

CREATE TABLE category_product_request_mapping (
  MappingRequestId      INT AUTO_INCREMENT PRIMARY KEY,
  ProductId             BIGINT,
  CategoryId            INT,
  RequestedBy           BIGINT,
  Status                CHAR(1) NOT NULL DEFAULT 'P',
  ApprovedBy            BIGINT,
  CreatedAtDate         DATETIME DEFAULT CURRENT_TIMESTAMP,
  UpdatedAtDate         DATETIME,
  FOREIGN KEY (ProductId)  REFERENCES products(ProductId),
  FOREIGN KEY (CategoryId) REFERENCES categories(CategoryId),
  FOREIGN KEY (RequestedBy) REFERENCES users(UserId),
  FOREIGN KEY (ApprovedBy)  REFERENCES users(UserId)
);

CREATE TABLE category_product_mapping (
  ProductId             BIGINT,
  CategoryId            INT,
  CreatedAtDate         DATETIME DEFAULT CURRENT_TIMESTAMP,
  UpdatedAtDate         DATETIME,
  PRIMARY KEY (ProductId, CategoryId),
  FOREIGN KEY (ProductId) REFERENCES products(ProductId),
  FOREIGN KEY (CategoryId) REFERENCES categories(CategoryId)
);

CREATE TABLE carts (
  CartId                BIGINT AUTO_INCREMENT PRIMARY KEY,
  UserId                BIGINT UNIQUE,
  FOREIGN KEY (UserId) REFERENCES users(UserId)
);

CREATE TABLE cart_items (
  CartItemId            BIGINT AUTO_INCREMENT PRIMARY KEY,
  CartId                BIGINT,
  ProductId             BIGINT,
  UserId                BIGINT,
  Quantity              INT NOT NULL,
  AddedAtDate           DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (CartId)    REFERENCES carts(CartId) ON DELETE CASCADE,
  FOREIGN KEY (ProductId) REFERENCES products(ProductId),
  FOREIGN KEY (UserId)    REFERENCES users(UserId)
);

CREATE TABLE orders (
  OrderId               BIGINT AUTO_INCREMENT PRIMARY KEY,
  UserId                BIGINT,
  Address               TEXT NOT NULL,
  TotalAmount           DECIMAL(12,2) NOT NULL,
  Status                CHAR(1) DEFAULT 'P',
  PlacedAtDate          DATETIME DEFAULT CURRENT_TIMESTAMP,
  UpdatedAtDate         DATETIME,
  FOREIGN KEY (UserId) REFERENCES users(UserId)
);

CREATE TABLE order_items (
  OrderItemId           BIGINT AUTO_INCREMENT PRIMARY KEY,
  OrderId               BIGINT,
  ProductId             BIGINT,
  Quantity              INT NOT NULL,
  Price                 DECIMAL(10,2) NOT NULL,
  CreatedAt             DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (OrderId)   REFERENCES orders(OrderId) ON DELETE CASCADE,
  FOREIGN KEY (ProductId) REFERENCES products(ProductId)
);

CREATE TABLE product_ratings (
  RatingId              BIGINT AUTO_INCREMENT PRIMARY KEY,
  ProductId             BIGINT,
  UserId                BIGINT,
  Rating                TINYINT CHECK (Rating BETWEEN 1 AND 5),
  CreatedAt             DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (ProductId) REFERENCES products(ProductId),
  FOREIGN KEY (UserId)   REFERENCES users(UserId)
);
