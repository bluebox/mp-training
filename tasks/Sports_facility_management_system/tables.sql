
create schema sports_facility;
use sports_facility;
CREATE TABLE `User`(
    `User_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `User_name` VARCHAR(255) NOT NULL,
    `User_phone` VARCHAR(255) NOT NULL,
    `User_email` VARCHAR(255) NOT NULL,
    `User_password` VARCHAR(255) NOT NULL,
    PRIMARY KEY (User_id),
    UNIQUE (User_phone)
);

CREATE TABLE `Facility`(
    `Facility_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `Facility_name` VARCHAR(255) NOT NULL,
    `Facility_location` VARCHAR(255) NOT NULL,
    `Facility_phone` VARCHAR(255) NOT NULL,
    `Facility_email` VARCHAR(255) NOT NULL,
    `Facility_password` VARCHAR(255) NOT NULL,
    PRIMARY KEY (Facility_id),
    UNIQUE (Facility_phone)
);
CREATE TABLE `sports`(
    `Sport_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `Sport_name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (Sport_id)
);
CREATE TABLE `Facility_sports`(
    `Facility_sport_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `Facility_id` INT UNSIGNED NOT NULL,
    `Sport_id` INT UNSIGNED NOT NULL,
    `Cost_per_slot` INT NOT NULL,
    PRIMARY KEY (Facility_sport_id)
);
CREATE TABLE `slots`(
    `Slot_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `Slot_time` TIME NOT NULL,
    primary key (Slot_id)
);
CREATE TABLE `slots_available_Facility_sports`(
    `Facility_sport_id` INT UNSIGNED NOT NULL,
    `slot_id` INT UNSIGNED NOT NULL

);
CREATE TABLE `equipment`(
    `equip_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `equip_name` VARCHAR(255) NOT NULL,
    `sport_id` INT UNSIGNED NOT NULL,
    `Rent_per_slot` INT NOT NULL,
    primary key (equip_id)
);
CREATE TABLE `booking_data`(
    `booking_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `Facility_sport_id` INT UNSIGNED NOT NULL,
    `user_id` INT UNSIGNED NOT NULL,
    `date_time` DATETIME NOT NULL,
    `reviews` TEXT NULL,
    `ratings` INT NULL,
    primary key (booking_id)
);
CREATE TABLE `slots_booked_for_booking_id`(
    `booking_id` INT UNSIGNED NOT NULL,
    `slot_id` INT UNSIGNED NOT NULL
);
CREATE TABLE `invoice`(
    `invoice_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `booking_id` INT UNSIGNED NOT NULL,
    `total_cost` INT NOT NULL,
    `invoice_pdf` BLOB NOT NULL,
    primary key (invoice_id)
);
CREATE TABLE `equipments_rented_for_booking_id`(
    `booking_id` INT UNSIGNED NOT NULL,
    `equip_id` INT UNSIGNED NOT NULL
);
ALTER TABLE
    `Facility_sports` ADD CONSTRAINT `facility_sports_facility_id_foreign` FOREIGN KEY(`facility_id`) REFERENCES `Facility`(`Facility_id`);
ALTER TABLE
    `booking_data` ADD CONSTRAINT `booking_data_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `User`(`user_id`);
ALTER TABLE
    `booking_data` ADD CONSTRAINT `booking_data_Facility_sport_id_foreign` FOREIGN KEY(`Facility_sport_id`) REFERENCES `Facility_sports`(`Facility_sport_id`);

ALTER TABLE
    `Facility_sports` ADD CONSTRAINT `facility_sports_sport_id_foreign` FOREIGN KEY(`sport_id`) REFERENCES `sports`(`sport_id`);
ALTER TABLE
    `equipment` ADD CONSTRAINT `equipment_sport_id_foreign` FOREIGN KEY(`sport_id`) REFERENCES `sports`(`sport_id`);
ALTER TABLE
    `slots_available_Facility_sports` ADD CONSTRAINT `slots_available_facility_sports_slot_id_foreign` FOREIGN KEY(`slot_id`) REFERENCES `slots`(`slot_id`);
ALTER TABLE
    `slots_available_Facility_sports` ADD CONSTRAINT `slots_available_facility_sports_Facility_sport_id_foreign` FOREIGN KEY(`Facility_sport_id`) REFERENCES `Facility_sports`(`Facility_sport_id`);

ALTER TABLE
    `equipments_rented_for_booking_id` ADD CONSTRAINT `equipments_rented_for_booking_id_equip_id_foreign` FOREIGN KEY(`equip_id`) REFERENCES `equipment`(`equip_id`);
ALTER TABLE
    `equipments_rented_for_booking_id` ADD CONSTRAINT `equipments_rented_for_booking_id_booking_id_foreign` FOREIGN KEY(`booking_id`) REFERENCES `booking_data`(`booking_id`);
ALTER TABLE
    `slots_booked_for_booking_id` ADD CONSTRAINT `slots_booked_for_booking_id_booking_id_foreign` FOREIGN KEY(`booking_id`) REFERENCES `booking_data`(`booking_id`);
ALTER TABLE
    `invoice` ADD CONSTRAINT `invoice_booking_id_foreign` FOREIGN KEY(`booking_id`) REFERENCES `booking_data`(`booking_id`);
ALTER TABLE
    `slots_booked_for_booking_id` ADD CONSTRAINT `slots_booked_for_booking_id_slot_id_foreign` FOREIGN KEY(`slot_id`) REFERENCES `slots`(`slot_id`);