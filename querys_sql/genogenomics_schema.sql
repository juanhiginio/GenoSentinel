-- GenoGenomics schema (MySQL)
-- Database for Genomics microservice (Django)
CREATE DATABASE IF NOT EXISTS `GenoGenomics` CHARACTER SET = 'utf8mb4' COLLATE = 'utf8mb4_unicode_ci';
USE `GenoGenomics`;

-- Genes of interest
CREATE TABLE IF NOT EXISTS `gene` (
  `id` CHAR(36) NOT NULL PRIMARY KEY,
  `symbol` VARCHAR(50) NOT NULL UNIQUE,
  `fullName` VARCHAR(255) NULL,
  `functionSummary` TEXT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Genetic variants (linked to genes)
CREATE TABLE IF NOT EXISTS `geneticVariant` (
  `id` CHAR(36) NOT NULL PRIMARY KEY,
  `geneId` CHAR(36) NOT NULL,
  `chromosome` VARCHAR(50) NULL,
  `position` BIGINT NULL,
  `referenceBase` VARCHAR(50) NULL,
  `alternateBase` VARCHAR(50) NULL,
  `impact` ENUM('Missense','Frameshift','Nonsense','Synonymous','Other') DEFAULT 'Other',
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX (`geneId`),
  CONSTRAINT `fk_variant_gene` FOREIGN KEY (`geneId`) REFERENCES `gene`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Patient variant reports (references patientId from clinic microservice logically)
CREATE TABLE IF NOT EXISTS `patientVariantReport` (
  `id` CHAR(36) NOT NULL PRIMARY KEY,
  `patientId` CHAR(36) NOT NULL, -- logical reference to GenoClinic.patients.id (NO DB-level FK)
  `variantId` CHAR(36) NOT NULL,
  `detectionDate` DATE NULL,
  `alleleFrequency` DECIMAL(7,6) NULL, -- VAF (0.000000 - 1.000000)
  `notes` TEXT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX (`patientId`),
  INDEX (`variantId`),
  CONSTRAINT `fk_report_variant` FOREIGN KEY (`variantId`) REFERENCES `geneticVariant`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Triggers to populate UUIDs for CHAR(36) primary keys
DELIMITER $$
DROP TRIGGER IF EXISTS `genes_before_insert`$$
CREATE TRIGGER `genes_before_insert`
BEFORE INSERT ON `gene`
FOR EACH ROW
BEGIN
  IF NEW.id IS NULL OR NEW.id = '' THEN
    SET NEW.id = UUID();
  END IF;
END$$

DROP TRIGGER IF EXISTS `genetic_variants_before_insert`$$
CREATE TRIGGER `genetic_variants_before_insert`
BEFORE INSERT ON `geneticVariant`
FOR EACH ROW
BEGIN
  IF NEW.id IS NULL OR NEW.id = '' THEN
    SET NEW.id = UUID();
  END IF;
END$$

DROP TRIGGER IF EXISTS `patient_variant_reports_before_insert`$$
CREATE TRIGGER `patient_variant_reports_before_insert`
BEFORE INSERT ON `patientVariantReport`
FOR EACH ROW
BEGIN
  IF NEW.id IS NULL OR NEW.id = '' THEN
    SET NEW.id = UUID();
  END IF;
END$$
DELIMITER ;

-- Indexes for faster queries
CREATE INDEX `idx_genes_symbol` ON `gene` (`symbol`);
CREATE INDEX `idx_variants_chr_pos` ON `geneticVariant` (`chromosome`, `position`);
