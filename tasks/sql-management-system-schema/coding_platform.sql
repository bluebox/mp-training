CREATE TABLE `user`(
    `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `profile_id` INT NOT NULL
);
ALTER TABLE
    `user` ADD PRIMARY KEY `user_user_id_primary`(`user_id`);
CREATE TABLE `profile`(
    `profile_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `full name` VARCHAR(255) NOT NULL,
    `gender` VARCHAR(255) NOT NULL,
    `location` VARCHAR(255) NOT NULL,
    `bio` VARCHAR(255) NOT NULL,
    `work` VARCHAR(255) NOT NULL,
    `education` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `profile` ADD PRIMARY KEY `profile_profile_id_primary`(`profile_id`);
CREATE TABLE `skills`(
    `skills_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `profile_id` INT NOT NULL,
    `skill` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `skills` ADD PRIMARY KEY `skills_skills_id_primary`(`skills_id`);
CREATE TABLE `solved`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `problem_id` INT NOT NULL,
    `solution` VARCHAR(255) NOT NULL,
    `status` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `solved` ADD PRIMARY KEY `solved_id_primary`(`id`);
CREATE TABLE `problems`(
    `problem_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `creator_id` INT NOT NULL,
    `problem_name` VARCHAR(255) NOT NULL,
    `difficulty_level` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `hints` VARCHAR(255) NOT NULL,
    `test_cases` VARCHAR(255) NOT NULL,
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
    `tag_name` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `tag` ADD PRIMARY KEY `tag_tag_id_primary`(`tag_id`);
CREATE TABLE `discussions`(
    `discussion_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `problem_id` INT NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    `discussion` VARCHAR(255) NOT NULL,
    `created_date_time` DATETIME NOT NULL,
    `upvotes` INT NOT NULL,
    `downvotes` INT NOT NULL
);
ALTER TABLE
    `discussions` ADD PRIMARY KEY `discussions_discussion_id_primary`(`discussion_id`);
CREATE TABLE `comments`(
    `comment_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `discussion_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    `comment` INT NOT NULL,
    `created_date_time` DATETIME NOT NULL
);
ALTER TABLE
    `comments` ADD PRIMARY KEY `comments_comment_id_primary`(`comment_id`);
ALTER TABLE
    `solved` ADD CONSTRAINT `solved_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `user`(`user_id`);
ALTER TABLE
    `solved` ADD CONSTRAINT `solved_problem_id_foreign` FOREIGN KEY(`problem_id`) REFERENCES `problems`(`problem_id`);
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
ALTER TABLE
    `comments` ADD CONSTRAINT `comments_discussion_id_foreign` FOREIGN KEY(`discussion_id`) REFERENCES `discussions`(`discussion_id`);
ALTER TABLE
    `discussions` ADD CONSTRAINT `discussions_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `user`(`user_id`);
ALTER TABLE
    `discussions` ADD CONSTRAINT `discussions_problem_id_foreign` FOREIGN KEY(`problem_id`) REFERENCES `problems`(`problem_id`);
