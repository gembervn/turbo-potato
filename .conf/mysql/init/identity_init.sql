CREATE DATABASE identity_db;

-- Create user if it does not exist
CREATE USER IF NOT EXISTS 'identity_service'@'%' IDENTIFIED BY 'mysql';
CREATE USER IF NOT EXISTS 'identity_service'@'localhost' IDENTIFIED BY 'mysql';

-- Grant privileges
GRANT ALL PRIVILEGES ON identity_db.* TO 'identity_service'@'%';
GRANT ALL PRIVILEGES ON identity_db.* TO 'identity_service'@'localhost';

-- Apply changes
FLUSH PRIVILEGES;