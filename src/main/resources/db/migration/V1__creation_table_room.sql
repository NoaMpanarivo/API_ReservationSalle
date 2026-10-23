CREATE TABLE room (
                      id_room   BIGSERIAL    PRIMARY KEY,
                      name      VARCHAR(255) NOT NULL UNIQUE,
                      capacity  INTEGER      NOT NULL CHECK (capacity > 0),
                      floor     INTEGER      NOT NULL
);