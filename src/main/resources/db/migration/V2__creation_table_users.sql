CREATE TABLE users (
                       id_user   BIGSERIAL    PRIMARY KEY,
                       email     VARCHAR(255) NOT NULL UNIQUE,
                       password  VARCHAR(255) NOT NULL,
                       role      VARCHAR(20)  NOT NULL
);