/* =========================================================
   USERS (MUST EXIST FIRST)
   ========================================================= */
CREATE TABLE users (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
) ENGINE=InnoDB;


/* =========================================================
   VENDOR PROFILES
   ========================================================= */
CREATE TABLE vendor_profiles (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  uuid VARCHAR(36) NOT NULL UNIQUE,

  user_id BIGINT UNSIGNED NOT NULL,  -- owner/admin user

  vendor_type ENUM('FREELANCER','AGENCY','COMPANY') NOT NULL,

  display_name VARCHAR(150) NOT NULL,
  legal_name VARCHAR(200),
  description TEXT,

  years_of_experience INT,
  team_size INT,
  website VARCHAR(255),

  rating DECIMAL(2,1) DEFAULT 0.0,
  total_projects INT DEFAULT 0,

  verified BOOLEAN DEFAULT FALSE,
  verification_level ENUM('BASIC','KYC','ENTERPRISE') DEFAULT 'BASIC',

  status ENUM('DRAFT','ACTIVE','SUSPENDED','BLACKLISTED') DEFAULT 'DRAFT',

  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
               ON UPDATE CURRENT_TIMESTAMP,

  PRIMARY KEY (id),

  CONSTRAINT fk_vendor_user
    FOREIGN KEY (user_id)
    REFERENCES users(id)
    ON DELETE CASCADE
) ENGINE=InnoDB;


/* =========================================================
   VENDOR CAPABILITIES
   ========================================================= */
CREATE TABLE vendor_capabilities (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,

  vendor_id BIGINT UNSIGNED NOT NULL,

  capability ENUM(
    'SINGLE_LAYER',
    'DOUBLE_LAYER',
    'MULTILAYER',
    'HDI',
    'RF',
    'FLEX',
    'RIGID_FLEX',
    'HIGH_SPEED',
    'POWER_ELECTRONICS'
  ) NOT NULL,

  min_layer INT,
  max_layer INT,
  notes VARCHAR(255),

  PRIMARY KEY (id),

  CONSTRAINT fk_vendor_capabilities_vendor
    FOREIGN KEY (vendor_id)
    REFERENCES vendor_profiles(id)
    ON DELETE CASCADE
) ENGINE=InnoDB;
