CREATE TABLE `Item_table` (
  `Item_id` int PRIMARY KEY,
  `seller_id` int,
  `product_id` int
);

CREATE TABLE `Customer_table` (
  `user_name` varchar(255),
  `user_id` int PRIMARY KEY,
  `user_email` varchar(255),
  `user_mobile` varchar(255)
);

CREATE TABLE `Product_table` (
  `name` varchar(255),
  `description` varchar(255),
  `price` int,
  `rating` int,
  `product_id` int PRIMARY KEY
);

CREATE TABLE `Seller_table` (
  `seller_id` int PRIMARY KEY,
  `seller_name` varchar(255),
  `seller_email` varchar(255),
  `seller_mobile` varchar(255)
);

CREATE TABLE `Order_table` (
  `order_id` int PRIMARY KEY,
  `user_id` int,
  `product_id` int
);

CREATE TABLE `Bought_table` (
  `bought_id` int PRIMARY KEY,
  `user_id` int,
  `product_id` int
);

CREATE TABLE `Sold_table` (
  `sold_id` int PRIMARY KEY,
  `seller_id` int,
  `product_id` int
);

CREATE TABLE `Wishlist_table` (
  `wish_id` int PRIMARY KEY,
  `user_id` int,
  `product_id` int
);

CREATE TABLE `Upcomming_table` (
  `upcomming_id` int PRIMARY KEY,
  `seller_id` int,
  `name` varchar(255),
  `description` varchar(255),
  `price` int,
  `rating` int
);

CREATE TABLE `Follow_table` (
  `user_id` int,
  `seller_id` int
);

CREATE TABLE `Return_table` (
  `return_id` int,
  `product_id` int,
  `user_id` int
);

ALTER TABLE `Item_table` ADD FOREIGN KEY (`seller_id`) REFERENCES `Seller_table` (`seller_id`);

ALTER TABLE `Item_table` ADD FOREIGN KEY (`product_id`) REFERENCES `Product_table` (`product_id`);

ALTER TABLE `Order_table` ADD FOREIGN KEY (`user_id`) REFERENCES `Customer_table` (`user_id`);

ALTER TABLE `Order_table` ADD FOREIGN KEY (`product_id`) REFERENCES `Product_table` (`product_id`);

ALTER TABLE `Bought_table` ADD FOREIGN KEY (`product_id`) REFERENCES `Product_table` (`product_id`);

ALTER TABLE `Bought_table` ADD FOREIGN KEY (`user_id`) REFERENCES `Customer_table` (`user_id`);

ALTER TABLE `Sold_table` ADD FOREIGN KEY (`sold_id`) REFERENCES `Item_table` (`Item_id`);

ALTER TABLE `Wishlist_table` ADD FOREIGN KEY (`user_id`) REFERENCES `Customer_table` (`user_id`);

ALTER TABLE `Wishlist_table` ADD FOREIGN KEY (`product_id`) REFERENCES `Product_table` (`product_id`);

ALTER TABLE `Upcomming_table` ADD FOREIGN KEY (`seller_id`) REFERENCES `Seller_table` (`seller_id`);

ALTER TABLE `Follow_table` ADD FOREIGN KEY (`seller_id`) REFERENCES `Seller_table` (`seller_id`);

ALTER TABLE `Follow_table` ADD FOREIGN KEY (`user_id`) REFERENCES `Customer_table` (`user_id`);

ALTER TABLE `Return_table` ADD FOREIGN KEY (`product_id`) REFERENCES `Product_table` (`product_id`);

ALTER TABLE `Return_table` ADD FOREIGN KEY (`user_id`) REFERENCES `Customer_table` (`user_id`);