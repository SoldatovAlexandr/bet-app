create table if not exists country_code
(
    id           bigint primary key,
    name      text,
    code varchar(2)
);

INSERT INTO country_code (name, code)
VALUES ('Austria', 'AT'),
       ('Albania', 'AL'),
       ('England', 'EN'),
       ('Belgium', 'BE'),
       ('Hungary', 'HU'),
       ('Germany', 'DE'),
       ('Georgia', 'GE'),
       ('Denmark', 'DK'),
       ('Spain', 'ES'),
       ('Italy', 'IT'),
       ('Netherlands', 'NL'),
       ('Poland', 'PL'),
       ('Portugal', 'PT'),
       ('Romania', 'RO'),
       ('Serbia', 'RS'),
       ('Slovakia', 'SK'),
       ('Slovenia', 'SI'),
       ('Turkey', 'TR'),
       ('Ukraine', 'UA'),
       ('France', 'FR'),
       ('Croatia', 'HR'),
       ('Czech Republic', 'CZ'),
       ('Switzerland', 'CH'),
       ('Scotland', 'SC');
