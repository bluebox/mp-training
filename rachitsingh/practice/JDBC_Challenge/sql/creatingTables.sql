CREATE DATABASE IF NOT EXISTS storefront;
USE storefront;

CREATE TABLE IF NOT EXISTS orders (
    order_ID INT PRIMARY KEY AUTO_INCREMENT,
    order_date DATETIME UNIQUE
);

CREATE TABLE IF NOT EXISTS order_details (
    detail_ID INT PRIMARY KEY AUTO_INCREMENT,
    order_ID INT,
    product_name VARCHAR(100),
    quantity INT,
    price DECIMAL(10, 4),

    FOREIGN KEY (order_ID) REFERENCES orders(order_ID)
);
