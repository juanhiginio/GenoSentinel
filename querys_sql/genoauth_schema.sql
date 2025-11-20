-- GenoAuth schema (MySQL)
-- Database for Authentication / Users microservice
CREATE DATABASE IF NOT EXISTS `GenoAuth` CHARACTER SET = 'utf8mb4' COLLATE = 'utf8mb4_unicode_ci';
USE `GenoAuth`;

-- Roles table
CREATE TABLE IF NOT EXISTS `roles` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `role_name` VARCHAR(100) NOT NULL UNIQUE,
  `description` TEXT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Users table
CREATE TABLE IF NOT EXISTS `users` (
  `id` CHAR(36) NOT NULL PRIMARY KEY,
  `username` VARCHAR(150) NOT NULL UNIQUE,
  `email` VARCHAR(255) NULL UNIQUE,
  `password_hash` VARCHAR(255) NOT NULL,
  `role_id` INT NOT NULL,
  `is_active` TINYINT(1) NOT NULL DEFAULT 1,
  `last_login` DATETIME NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX (`username`),
  INDEX (`role_id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `roles`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Trigger to auto-populate UUID for users.id
DELIMITER $$
DROP TRIGGER IF EXISTS `users_before_insert`$$
CREATE TRIGGER `users_before_insert`
BEFORE INSERT ON `users`
FOR EACH ROW
BEGIN
  IF NEW.id IS NULL OR NEW.id = '' THEN
    SET NEW.id = UUID();
  END IF;
END$$
DELIMITER ;

-- Seed a default role
INSERT IGNORE INTO `roles` (`id`, `role_name`, `description`) VALUES (1, 'user', 'User role with full permissions.');
