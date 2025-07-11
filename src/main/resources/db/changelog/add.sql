ALTER TABLE tournament
ADD winner BIGINT;

ALTER TABLE tournament
ADD home_score INT;

ALTER TABLE tournament
ADD away_score INT;

ALTER TABLE tournament
ADD home_penalty_score INT;

ALTER TABLE tournament
ADD away_penalty_score INT;
