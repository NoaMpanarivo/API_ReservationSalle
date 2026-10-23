CREATE TABLE reservation (
                             id_reservation  BIGSERIAL   PRIMARY KEY,
                             start_at        DATE        NOT NULL,
                             end_at          DATE        NOT NULL,
                             status          VARCHAR(20) NOT NULL,
                             room_id         BIGINT      NOT NULL REFERENCES room (id_room),
                             user_id         BIGINT      NOT NULL REFERENCES users (id_user),
                             CONSTRAINT ck_reservation_periode CHECK (start_at < end_at)
);

CREATE INDEX idx_reservation_room_dates ON reservation (room_id, start_at, end_at);
CREATE INDEX idx_reservation_user ON reservation (user_id);