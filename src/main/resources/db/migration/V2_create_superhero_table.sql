CREATE TABLE super_hero
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    name         VARCHAR(255)          NOT NULL,
    gender       VARCHAR(5)            NOT NULL,
    race         VARCHAR(5)            NOT NULL,
    alignment    VARCHAR(5)            NOT NULL,
    publisher_id BIGINT,
    CONSTRAINT pk_super_hero PRIMARY KEY (id)
);

ALTER TABLE super_hero
    ADD CONSTRAINT FK_SUPER_HERO_ON_PUBLISHERID FOREIGN KEY (publisher_id) REFERENCES publisher (id);
