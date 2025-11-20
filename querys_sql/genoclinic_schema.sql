-- GenoClinic schema (MySQL)
-- Database for Clinical microservice (NestJS)
CREATE DATABASE IF NOT EXISTS `GenoClinic` CHARACTER SET = 'utf8mb4' COLLATE = 'utf8mb4_unicode_ci';
USE `GenoClinic`;

-- Patients table
CREATE TABLE IF NOT EXISTS `patient` (
  `id` CHAR(36) NOT NULL PRIMARY KEY,
  `firstName` VARCHAR(150) NOT NULL,
  `lastName` VARCHAR(150) NOT NULL,
  `birthDate` DATE NULL,
  `gender` ENUM('Male','Female','Other') NOT NULL,
  `status` ENUM('Activo','Seguimiento') NOT NULL DEFAULT 'Activo',
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tumor types (catalog)
CREATE TABLE IF NOT EXISTS `tumorType` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL UNIQUE,
  `systemAffected` VARCHAR(255) NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Clinical records: links patient -> tumor type
CREATE TABLE IF NOT EXISTS `clinicalRecord` (
  `id` CHAR(36) NOT NULL PRIMARY KEY,
  `patientId` CHAR(36) NOT NULL,
  `tumorTypeId` INT NOT NULL,
  `diagnosisDate` DATE NULL,
  `stage` ENUM('IIA','IV') NULL,
  `treatmentProtocol` TEXT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX (`patientId`),
  INDEX (`tumorTypeId`),
  CONSTRAINT `fk_clinrec_patient` FOREIGN KEY (`patientId`) REFERENCES `patient`(`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_clinrec_tumortype` FOREIGN KEY (`tumorTypeId`) REFERENCES `tumorType`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Triggers to auto-generate UUID() for CHAR(36) primary keys when not provided
DELIMITER $$
DROP TRIGGER IF EXISTS `patients_before_insert`$$
CREATE TRIGGER `patients_before_insert`
BEFORE INSERT ON `patient`
FOR EACH ROW
BEGIN
  IF NEW.id IS NULL OR NEW.id = '' THEN
    SET NEW.id = UUID();
  END IF;
END$$

DROP TRIGGER IF EXISTS `clinical_records_before_insert`$$
CREATE TRIGGER `clinical_records_before_insert`
BEFORE INSERT ON `clinicalRecord`
FOR EACH ROW
BEGIN
  IF NEW.id IS NULL OR NEW.id = '' THEN
    SET NEW.id = UUID();
  END IF;
END$$
DELIMITER ;

-- Helpful indexes
CREATE INDEX `idx_patients_name` ON `patient` (`lastName`, `firstName`);
