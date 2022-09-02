CREATE TABLE `user`(
    `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(30) NOT NULL,
    `email` VARCHAR(30) NOT NULL,
    `password` VARCHAR(30) NOT NULL,
    `profile_id` INT NOT NULL
);
ALTER TABLE
    `user` ADD PRIMARY KEY `user_user_id_primary`(`user_id`);
CREATE TABLE `profile`(
    `profile_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `full name` VARCHAR(30) NOT NULL,
    `gender` VARCHAR(10) NOT NULL,
    `location` VARCHAR(30) NOT NULL,
    `bio` VARCHAR(100) NOT NULL,
    `work` VARCHAR(50) NOT NULL,
    `education` VARCHAR(50) NOT NULL
);
ALTER TABLE
    `profile` ADD PRIMARY KEY `profile_profile_id_primary`(`profile_id`);
CREATE TABLE `skills`(
    `skills_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `profile_id` INT NOT NULL,
    `skill` VARCHAR(20) NOT NULL
);
ALTER TABLE
    `skills` ADD PRIMARY KEY `skills_skills_id_primary`(`skills_id`);
CREATE TABLE `solved`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `problem_id` INT NOT NULL,
    `solution` VARCHAR(500) NOT NULL,
    `status` VARCHAR(20) NOT NULL
);
ALTER TABLE
    `solved` ADD PRIMARY KEY `solved_id_primary`(`id`);
CREATE TABLE `problems`(
    `problem_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `creator_id` INT NOT NULL,
    `problem_name` VARCHAR(30) NOT NULL,
    `difficulty_level` VARCHAR(10) NOT NULL,
    `description` VARCHAR(500) NOT NULL,
    `hints` VARCHAR(100) NOT NULL,
    `test_cases` VARCHAR(500) NOT NULL,
    `likes` INT NOT NULL,
    `dislikes` INT NOT NULL
);
ALTER TABLE
    `problems` ADD PRIMARY KEY `problems_problem_id_primary`(`problem_id`);
CREATE TABLE `topic_tags`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `problem_id` INT NOT NULL,
    `tag_id` INT NOT NULL
);
ALTER TABLE
    `topic_tags` ADD PRIMARY KEY `topic_tags_id_primary`(`id`);
CREATE TABLE `tag`(
    `tag_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `tag_name` VARCHAR(20) NOT NULL
);
ALTER TABLE
    `tag` ADD PRIMARY KEY `tag_tag_id_primary`(`tag_id`);
ALTER TABLE
    `user` ADD CONSTRAINT `user_profile_id_foreign` FOREIGN KEY(`profile_id`) REFERENCES `profile`(`profile_id`);
ALTER TABLE
    `skills` ADD CONSTRAINT `skills_profile_id_foreign` FOREIGN KEY(`profile_id`) REFERENCES `profile`(`profile_id`);
ALTER TABLE
    `topic_tags` ADD CONSTRAINT `topic_tags_problem_id_foreign` FOREIGN KEY(`problem_id`) REFERENCES `problems`(`problem_id`);
ALTER TABLE
    `problems` ADD CONSTRAINT `problems_creator_id_foreign` FOREIGN KEY(`creator_id`) REFERENCES `user`(`user_id`);
ALTER TABLE
    `topic_tags` ADD CONSTRAINT `topic_tags_tag_id_foreign` FOREIGN KEY(`tag_id`) REFERENCES `tag`(`tag_id`);
