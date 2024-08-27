CREATE TABLE ressource (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           type VARCHAR(20) NOT NULL,
                           quantity INT NOT NULL,
                           supplier_info TEXT NOT NULL
);
