--changeset asoldatov:initial1
create sequence if not exists hibernate_sequence
    increment 1
    minvalue 1
    maxvalue 9223372036854775807
    start 1
    cache 1;
--rollback drop sequence hibernate_sequence;
--comment: Добавлен hibernate_sequence

--changeset asoldatov:team1
create table if not exists team
(
    id      bigint          primary key,
    name    varchar(100)     not null
);
--rollback drop table team;
--comment: Создана таблица team

--changeset asoldatov:match1
create table if not exists match
(
    id              varchar(20)     primary key,
    home_team_id    bigint          not null,
    away_team_id    bigint          not null,
    time            timestamp       not null,
    home_score      int             not null,
    away_score      int             not null,
    result          varchar(10)     not null,
    created_at      timestamp       not null,
    foreign key (home_team_id) references team (id),
    foreign key (away_team_id) references team (id)
);
--rollback drop table match;
--comment: Создана таблица match

--changeset asoldatov:users1
create table if not exists users
(
    id              bigint          primary key,
    username        varchar(255)               ,
    first_name      varchar(255)               ,
    status          varchar(255)       not null
);
--rollback drop table users;
--comment: Создана таблица users

--changeset asoldatov:bet1
create table if not exists bet
(
    id              bigint          primary key,
    match_id        varchar(20)     not null,
    user_id         bigint          not null,
    created_at      timestamp       not null,
    home_score      int             not null,
    away_score      int             not null,
    score           int             not null,
    status          varchar(20)     not null,
    foreign key (match_id) references match (id),
    foreign key (user_id) references users (id),
    unique (match_id, user_id)
);
--rollback drop table bet;
--comment: Создана таблица bet
