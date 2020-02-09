DROP TABLE IF EXISTS valutes;

CREATE TABLE valutes (
    id                    varchar(10) PRIMARY KEY,
    num_code              int NOT NULL UNIQUE,
    char_code             varchar(10) NOT NULL UNIQUE,
    name                  varchar(50) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS rates;

CREATE TABLE rates (
    nominal               int NOT NULL,
    valute_value          numeric(10, 4) NOT NULL,
    created_at            date NOT NULL,
    valute_id             varchar(10) NOT NULL,
    PRIMARY KEY (created_at, valute_id),
    FOREIGN KEY (valute_id) REFERENCES valutes(id)
);

DROP TABLE IF EXISTS history;

CREATE TABLE history (
    id                    bigserial PRIMARY KEY,
    source_currency       varchar(70) NOT NULL,
    target_currency       varchar(70) NOT NULL,
    source_summ           numeric(10, 4),
    result_summ           numeric(10, 4),
    created_at            date NOT NULL
);

DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id              bigserial PRIMARY KEY,
    password        varchar(80),
    email           varchar(50) UNIQUE,
    name            varchar(50)
);

DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
    id               serial PRIMARY KEY,
    name             varchar(50) NOT NULL
);

DROP TABLE IF EXISTS users_roles;
CREATE TABLE users_roles (
    user_id           int NOT NULL,
    role_id           int NOT NULL,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id)
);

INSERT INTO roles (name) VALUES
('ROLE_CUSTOMER'), ('ROLE_MANAGER'), ('ROLE_ADMIN');

INSERT INTO users (password, email, name) VALUES
('$2y$12$Y3DRzDq/SmCxyquHGbf3iOBfMGDbGXjvrpBLh/H5fJbI1vA8IYg9q','admin@gmail.com','Admin');

INSERT INTO valutes (id, num_code, char_code, name) VALUES
('RUBID', 810, 'RUB', 'Российский рубль');