-- liquibase formatted sql

-- changeset asoldatov:initial1
-- comment: Добавлена последовательность hibernate_sequence
CREATE SEQUENCE IF NOT EXISTS hibernate_sequence
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
-- rollback DROP SEQUENCE hibernate_sequence;

-- changeset asoldatov:users1
-- comment: Создана таблица users
CREATE TABLE IF NOT EXISTS users
(
    id              BIGINT          PRIMARY KEY,
    username        VARCHAR(255),
    first_name      VARCHAR(255),
    status          VARCHAR(255)    NOT NULL
);
-- rollback DROP TABLE users;

-- changeset asoldatov:stage1
-- comment: Создана таблица stage
CREATE TABLE IF NOT EXISTS stage
(
    id              INT             PRIMARY KEY,
    stage           VARCHAR(50)     NOT NULL,
    status          VARCHAR(50),
    start_date      TIMESTAMP       NOT NULL,
    end_date        TIMESTAMP       NOT NULL
);
-- rollback DROP TABLE stage;

-- changeset asoldatov:tournament1
-- comment: Создана таблица tournament
CREATE TABLE IF NOT EXISTS tournament
(
    id                  INT             PRIMARY KEY,
    venue_name          VARCHAR(255),
    date                TIMESTAMP       NOT NULL,
    home_squad_id       BIGINT          NOT NULL,
    away_squad_id       BIGINT          NOT NULL,
    home_squad_name     VARCHAR(100)    NOT NULL,
    away_squad_name     VARCHAR(100)    NOT NULL,
    status              VARCHAR(50),
    bracket_id          INT,
    stage_id            INT             NOT NULL,
    CONSTRAINT fk_tournament_stage FOREIGN KEY (stage_id) REFERENCES stage (id)
);
-- rollback DROP TABLE tournament;

-- changeset asoldatov:bet1
-- comment: Создана таблица bet
CREATE TABLE IF NOT EXISTS bet
(
    id              BIGINT          PRIMARY KEY,
    tournament_id   INT             NOT NULL,
    user_id         BIGINT          NOT NULL,
    created_at      TIMESTAMP       NOT NULL,
    home_score      INT             NOT NULL,
    away_score      INT             NOT NULL,
    score           INT             NOT NULL,
    status          VARCHAR(20)     NOT NULL,
    CONSTRAINT fk_bet_tournament FOREIGN KEY (tournament_id) REFERENCES tournament (id),
    CONSTRAINT fk_bet_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT uq_tournament_user UNIQUE (tournament_id, user_id)
);
-- rollback DROP TABLE bet;
