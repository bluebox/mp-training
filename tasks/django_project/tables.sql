CREATE TABLE `stores_details`(
    `store_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `store_location` VARCHAR(255) NOT NULL,
    `store_name` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `stores_details` ADD PRIMARY KEY `stores_details_store_id_primary`(`store_id`);
CREATE TABLE `customers_details`(
    `customer_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `Phone_number` VARCHAR(255) NOT NULL,
    `address` VARCHAR(255) NULL
);
ALTER TABLE
    `customers_details` ADD PRIMARY KEY `customers_details_customer_id_primary`(`customer_id`);
CREATE TABLE `pharma Company`(
    `company_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `company_name` VARCHAR(255) NOT NULL,
    `company_place` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `pharma Company` ADD PRIMARY KEY `pharma company_company_id_primary`(`company_id`);
CREATE TABLE `product_details`(
    `product_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_name` VARCHAR(255) NOT NULL,
    `manufacturer_id` INT NOT NULL,
    `selling_price` INT NOT NULL,
    `cost_price` INT NOT NULL
);
ALTER TABLE
    `product_details` ADD PRIMARY KEY `product_details_product_id_primary`(`product_id`);
CREATE TABLE `employee-details`(
    `emp_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `emp_name` VARCHAR(255) NOT NULL,
    `store_id` INT NOT NULL
);
ALTER TABLE
    `employee-details` ADD PRIMARY KEY `employee_details_emp_id_primary`(`emp_id`);
CREATE TABLE `customers_orders`(
    `order_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `customer_id` INT NOT NULL,
    `date` DATE NOT NULL,
    `store_id` INT NOT NULL,
    `emp_id` INT NOT NULL,
    `total_cost` INT NOT NULL
);
ALTER TABLE
    `customers_orders` ADD PRIMARY KEY `customers_orders_order_id_primary`(`order_id`);
CREATE TABLE `customer_order_details`(
    `order_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_id` INT NOT NULL,
    `quantity` INT NOT NULL
);
ALTER TABLE
    `customer_order_details` ADD PRIMARY KEY `customer_order_details_order_id_primary`(`order_id`);
CREATE TABLE `store_inventory`(
    `store_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_id` INT NOT NULL,
    `quantity` INT NOT NULL
);
CREATE TABLE `store_requirements`(
    `order_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `store_id` INT NOT NULL,
    `date` DATE NOT NULL,
    `warehouse_id` INT NOT NULL,
    `emp_id` INT NOT NULL
);
ALTER TABLE
    `store_requirements` ADD PRIMARY KEY `store_requirements_order_id_primary`(`order_id`);
CREATE TABLE `warehouse_details`(
    `warehouse_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `warehouse_name` VARCHAR(255) NOT NULL,
    `warehouse_location` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `warehouse_details` ADD PRIMARY KEY `warehouse_details_warehouse_id_primary`(`warehouse_id`);
CREATE TABLE `store_order_details`(
    `order_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_id` INT NOT NULL,
    `quantity` INT NOT NULL,
    `warehouse_id` INT NOT NULL
);
CREATE TABLE `warehouse_orders`(
    `order_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `warehouse_id` INT NOT NULL,
    `pharma_company_id` INT NOT NULL,
    `total_cost` INT NOT NULL,
    `emp_id` INT NOT NULL
);
ALTER TABLE
    `warehouse_orders` ADD PRIMARY KEY `warehouse_orders_order_id_primary`(`order_id`);
CREATE TABLE `warehouse_order_details`(
    `order_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_id` INT NOT NULL,
    `quantity` INT NOT NULL
);
CREATE TABLE `warehouse_inventory`(
    `warehouse_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_id` INT NOT NULL,
    `quantity` INT NOT NULL
);
ALTER TABLE
    `customers_orders` ADD CONSTRAINT `customers_orders_customer_id_foreign` FOREIGN KEY(`customer_id`) REFERENCES `customers_details`(`customer_id`);
ALTER TABLE
    `store_requirements` ADD CONSTRAINT `store_requirements_store_id_foreign` FOREIGN KEY(`store_id`) REFERENCES `stores_details`(`store_id`);
ALTER TABLE
    `store_inventory` ADD CONSTRAINT `store_inventory_store_id_foreign` FOREIGN KEY(`store_id`) REFERENCES `stores_details`(`store_id`);
ALTER TABLE
    `product_details` ADD CONSTRAINT `product_details_manufacturer_id_foreign` FOREIGN KEY(`manufacturer_id`) REFERENCES `pharma Company`(`company_id`);
ALTER TABLE
    `customers_orders` ADD CONSTRAINT `customers_orders_emp_id_foreign` FOREIGN KEY(`emp_id`) REFERENCES `employee-details`(`emp_id`);
ALTER TABLE
    `store_requirements` ADD CONSTRAINT `store_requirements_emp_id_foreign` FOREIGN KEY(`emp_id`) REFERENCES `employee-details`(`emp_id`);
ALTER TABLE
    `store_inventory` ADD CONSTRAINT `store_inventory_product_id_foreign` FOREIGN KEY(`product_id`) REFERENCES `product_details`(`product_id`);
ALTER TABLE
    `customer_order_details` ADD CONSTRAINT `customer_order_details_product_id_foreign` FOREIGN KEY(`product_id`) REFERENCES `product_details`(`product_id`);
ALTER TABLE
    `store_order_details` ADD CONSTRAINT `store_order_details_order_id_foreign` FOREIGN KEY(`order_id`) REFERENCES `store_requirements`(`order_id`);
ALTER TABLE
    `store_order_details` ADD CONSTRAINT `store_order_details_warehouse_id_foreign` FOREIGN KEY(`warehouse_id`) REFERENCES `warehouse_details`(`warehouse_id`);
ALTER TABLE
    `warehouse_inventory` ADD CONSTRAINT `warehouse_inventory_warehouse_id_foreign` FOREIGN KEY(`warehouse_id`) REFERENCES `warehouse_details`(`warehouse_id`);
ALTER TABLE
    `store_order_details` ADD CONSTRAINT `store_order_details_product_id_foreign` FOREIGN KEY(`product_id`) REFERENCES `product_details`(`product_id`);
ALTER TABLE
    `warehouse_order_details` ADD CONSTRAINT `warehouse_order_details_product_id_foreign` FOREIGN KEY(`product_id`) REFERENCES `product_details`(`product_id`);
ALTER TABLE
    `warehouse_order_details` ADD CONSTRAINT `warehouse_order_details_order_id_foreign` FOREIGN KEY(`order_id`) REFERENCES `warehouse_orders`(`order_id`);
ALTER TABLE
    `warehouse_orders` ADD CONSTRAINT `warehouse_orders_warehouse_id_foreign` FOREIGN KEY(`warehouse_id`) REFERENCES `warehouse_details`(`warehouse_id`);
ALTER TABLE
    `warehouse_orders` ADD CONSTRAINT `warehouse_orders_pharma_company_id_foreign` FOREIGN KEY(`pharma_company_id`) REFERENCES `pharma Company`(`company_id`);
ALTER TABLE
    `warehouse_orders` ADD CONSTRAINT `warehouse_orders_emp_id_foreign` FOREIGN KEY(`emp_id`) REFERENCES `employee-details`(`emp_id`);
ALTER TABLE
    `warehouse_inventory` ADD CONSTRAINT `warehouse_inventory_product_id_foreign` FOREIGN KEY(`product_id`) REFERENCES `product_details`(`product_id`);