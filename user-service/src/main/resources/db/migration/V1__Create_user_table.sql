CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      full_name VARCHAR(255) NOT NULL,
                      username VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      role VARCHAR(50) NOT NULL
);