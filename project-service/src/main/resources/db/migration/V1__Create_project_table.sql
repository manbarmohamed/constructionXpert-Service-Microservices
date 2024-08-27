CREATE TABLE project(
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                          description TEXT NOT NULL,
                          start_date DATE NOT NULL,
                          end_date DATE NOT NULL,
                          budget Double  NOT NULL
)