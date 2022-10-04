CREATE TABLE `Product_details` (
  `id` int PRIMARY KEY,
  `name` varchar(255),
  `price` float,
  `stock` int,
  `product_type` int,
  `status` ENUM ('out_of_stock', 'in_stock', 'running_low'),
  `product_desc` varchar(255),
  `pickup_code` varchar(255)
);

CREATE TABLE `Customer` (
  `id` int primary key,
  `f_name` varchar(255),
  `l_name` varchar(255),
  `contact` integer,
  `address` varchar(255),
  `pincode` integer,
  `order_id` integer
);

CREATE TABLE `Order_list` (
  `order_id` int primary key,
  `customer_id` int,
  `product_id` int,
  `quantity` integer,
  `total_price` float4,
  `order_type` varchar(255)
);

CREATE TABLE `Customer_invoice` (
  `customer_id` int,
  `order_id` int
);

CREATE TABLE `Delivery_emp` (
  `emp_id` int,
  `order_id` int
);

CREATE TABLE `Total_delivered_items` (
  `emp_id` int,
  `order_id` int
);

CREATE TABLE `return_items` (
  `order_id` int,
  `product_id` int,
  `customer_id` int
);

CREATE TABLE `Pickup` (
  `pickup_id` int primary key,
  `ofc_name` varchar(255),
  `address` varchar(255),
  `pincode` integer
);

CREATE TABLE `Employees` (
  `emp_id` int primary key,
  `name` varchar(255),
  `contact` integer,
  `head_id` int,
  `dept` varchar(255)
);

ALTER TABLE `Product_details` ADD FOREIGN KEY (`pickup_code`) REFERENCES `Pickup` (`pickup_id`);

ALTER TABLE `Customer` ADD FOREIGN KEY (`order_id`) REFERENCES `Order_list` (`order_id`);

ALTER TABLE `Order_list` ADD FOREIGN KEY (`customer_id`) REFERENCES `Customer` (`id`);

ALTER TABLE `Order_list` ADD FOREIGN KEY (`product_id`) REFERENCES `Product_details` (`id`);

ALTER TABLE `Customer_invoice` ADD FOREIGN KEY (`customer_id`) REFERENCES `Customer` (`id`);

ALTER TABLE `Customer_invoice` ADD FOREIGN KEY (`order_id`) REFERENCES `Order_list` (`order_id`);

ALTER TABLE `Delivery_emp` ADD FOREIGN KEY (`emp_id`) REFERENCES `Employees` (`emp_id`);

ALTER TABLE `Delivery_emp` ADD FOREIGN KEY (`order_id`) REFERENCES `Order_list` (`order_id`);

ALTER TABLE `Total_delivered_items` ADD FOREIGN KEY (`emp_id`) REFERENCES `Employees` (`emp_id`);

ALTER TABLE `Total_delivered_items` ADD FOREIGN KEY (`order_id`) REFERENCES `Order_list` (`order_id`);

ALTER TABLE `return_items` ADD FOREIGN KEY (`order_id`) REFERENCES `Order_list` (`order_id`);

ALTER TABLE `return_items` ADD FOREIGN KEY (`product_id`) REFERENCES `Product_details` (`id`);

ALTER TABLE `return_items` ADD FOREIGN KEY (`customer_id`) REFERENCES `Customer` (`id`);


