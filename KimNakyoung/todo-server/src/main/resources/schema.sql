

CREATE TABLE task (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      status VARCHAR(64) NOT NULL,
                      title VARCHAR(128) NOT NULL,
                      description VARCHAR(128) NOT NULL,
                      due_date DATE,
                      created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      update_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      PRIMARY KEY (id)
);

