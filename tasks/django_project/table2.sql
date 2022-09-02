CREATE TABLE `stores_details`(
    `store_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `store_location` VARCHAR(255) NOT NULL,
    `store_name` VARCHAR(255) NOT NULL,
    `store_manager_id` INT NOT NULL
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
    `store_id` INT NULL,
    `warehouse_id` INT NULL,
    `emp_username` VARCHAR(255) NOT NULL,
    `emp_password` VARCHAR(255) NOT NULL,
    `emp_type_id` INT NOT NULL
);
ALTER TABLE
    `employee-details` ADD PRIMARY KEY `employee_details_emp_id_primary`(`emp_id`);
CREATE TABLE `orders_data`(
    `order_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `order by` VARCHAR(255) NOT NULL,
    `customer_id` INT NULL,
    `store_id` INT NULL,
    `warehouse_id` INT NULL,
    `pharmacompany_id` INT NULL,
    `total_cost` INT NOT NULL,
    `date` DATE NOT NULL
);
ALTER TABLE
    `orders_data` ADD PRIMARY KEY `orders_data_order_id_primary`(`order_id`);
CREATE TABLE `order_details`(
    `order_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_id` INT NOT NULL,
    `quantity` INT NOT NULL
);
ALTER TABLE
    `order_details` ADD PRIMARY KEY `order_details_order_id_primary`(`order_id`);
CREATE TABLE `store_inventory`(
    `store_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_id` INT NOT NULL,
    `quantity` INT NOT NULL
);
CREATE TABLE `warehouse_details`(
    `warehouse_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `warehouse_name` VARCHAR(255) NOT NULL,
    `warehouse_location` VARCHAR(255) NOT NULL,
    `warehouse_manager` INT NOT NULL
);
ALTER TABLE
    `warehouse_details` ADD PRIMARY KEY `warehouse_details_warehouse_id_primary`(`warehouse_id`);
CREATE TABLE `warehouse_inventory`(
    `warehouse_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_id` INT NOT NULL,
    `quantity` INT NOT NULL
);
CREATE TABLE `employment_type`(
    `emp_type_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `emp_type` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `employment_type` ADD PRIMARY KEY `employment_type_emp_type_id_primary`(`emp_type_id`);
ALTER TABLE
    `orders_data` ADD CONSTRAINT `orders_data_customer_id_foreign` FOREIGN KEY(`customer_id`) REFERENCES `customers_details`(`customer_id`);
ALTER TABLE
    `store_inventory` ADD CONSTRAINT `store_inventory_store_id_foreign` FOREIGN KEY(`store_id`) REFERENCES `stores_details`(`store_id`);
ALTER TABLE
    `stores_details` ADD CONSTRAINT `stores_details_store_manager_id_foreign` FOREIGN KEY(`store_manager_id`) REFERENCES `employee-details`(`emp_id`);
ALTER TABLE
    `product_details` ADD CONSTRAINT `product_details_manufacturer_id_foreign` FOREIGN KEY(`manufacturer_id`) REFERENCES `pharma Company`(`company_id`);
ALTER TABLE
    `orders_data` ADD CONSTRAINT `orders_data_warehouse_id_foreign` FOREIGN KEY(`warehouse_id`) REFERENCES `employee-details`(`emp_id`);
ALTER TABLE
    `employee-details` ADD CONSTRAINT `employee_details_store_id_foreign` FOREIGN KEY(`store_id`) REFERENCES `stores_details`(`store_id`);
ALTER TABLE
    `employee-details` ADD CONSTRAINT `employee_details_warehouse_id_foreign` FOREIGN KEY(`warehouse_id`) REFERENCES `warehouse_details`(`warehouse_id`);
ALTER TABLE
    `employee-details` ADD CONSTRAINT `employee_details_emp_type_id_foreign` FOREIGN KEY(`emp_type_id`) REFERENCES `employment_type`(`emp_type_id`);
ALTER TABLE
    `store_inventory` ADD CONSTRAINT `store_inventory_product_id_foreign` FOREIGN KEY(`product_id`) REFERENCES `product_details`(`product_id`);
ALTER TABLE
    `order_details` ADD CONSTRAINT `order_details_product_id_foreign` FOREIGN KEY(`product_id`) REFERENCES `product_details`(`product_id`);
ALTER TABLE
    `orders_data` ADD CONSTRAINT `orders_data_store_id_foreign` FOREIGN KEY(`store_id`) REFERENCES `stores_details`(`store_id`);
ALTER TABLE
    `orders_data` ADD CONSTRAINT `orders_data_warehouse_id_foreign` FOREIGN KEY(`warehouse_id`) REFERENCES `warehouse_details`(`warehouse_id`);
ALTER TABLE
    `orders_data` ADD CONSTRAINT `orders_data_pharmacompany_id_foreign` FOREIGN KEY(`pharmacompany_id`) REFERENCES `pharma Company`(`company_id`);
ALTER TABLE
    `warehouse_inventory` ADD CONSTRAINT `warehouse_inventory_warehouse_id_foreign` FOREIGN KEY(`warehouse_id`) REFERENCES `warehouse_details`(`warehouse_id`);
ALTER TABLE
    `warehouse_details` ADD CONSTRAINT `warehouse_details_warehouse_manager_foreign` FOREIGN KEY(`warehouse_manager`) REFERENCES `employee-details`(`emp_id`);
ALTER TABLE
    `warehouse_inventory` ADD CONSTRAINT `warehouse_inventory_product_id_foreign` FOREIGN KEY(`product_id`) REFERENCES `product_details`(`product_id`);