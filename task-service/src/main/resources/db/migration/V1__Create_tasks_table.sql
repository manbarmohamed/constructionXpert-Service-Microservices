CREATE TABLE task (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      description TEXT NOT NULL,
                      start_date DATE NOT NULL,
                      end_date DATE NOT NULL,
                      status VARCHAR(20) NOT NULL,
                      project_id BIGINT NOT NULL
);
