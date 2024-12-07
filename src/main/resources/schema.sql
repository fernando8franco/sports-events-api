DROP TABLE IF EXISTS t_employee;
DROP TABLE IF EXISTS t_player;
DROP TABLE IF EXISTS t_team;
DROP TABLE IF EXISTS t_event;
DROP TABLE IF EXISTS t_dependencies_sports;
DROP TABLE IF EXISTS t_dependency;
DROP TABLE IF EXISTS t_sport;

DROP TYPE IF EXISTS dependency_category;
DROP TYPE IF EXISTS sport_category;
DROP TYPE IF EXISTS type_gender;
DROP TYPE IF EXISTS type_role;

DROP SEQUENCE IF EXISTS t_dependency_id_seq;
DROP SEQUENCE IF EXISTS t_sport_id_seq;
DROP SEQUENCE IF EXISTS t_dependencies_sports_id_seq;
DROP SEQUENCE IF EXISTS t_team_seq;

CREATE TYPE dependency_category AS ENUM ('HIGH_SCHOOL', 'UNIVERSITY', 'DIRECTION');
CREATE TYPE sport_category AS ENUM ('MALE', 'FEMALE', 'MIXED');
CREATE TYPE type_gender AS ENUM ('MALE', 'FEMALE', 'NO_BINARY', 'OTHER');
CREATE TYPE type_role AS ENUM ('SUPERADMIN', 'ADMIN', 'EMPLOYEE');

CREATE SEQUENCE t_dependency_id_seq INCREMENT 1 START 100;
CREATE SEQUENCE t_sport_id_seq INCREMENT 1 START 100;
CREATE SEQUENCE t_dependencies_sports_id_seq INCREMENT 1 START 1;
CREATE SEQUENCE t_team_seq INCREMENT 1 START 1;

CREATE TABLE t_dependency (
    id INTEGER PRIMARY KEY DEFAULT NEXTVAL('t_dependency_id_seq'),
    name VARCHAR(150) NOT NULL,
    category dependency_category NOT NULL
);

CREATE TABLE t_sport (
    id INTEGER PRIMARY KEY DEFAULT NEXTVAL('t_sport_id_seq'),
    name VARCHAR(150) NOT NULL,
    category sport_category NOT NULL,
    num_players INTEGER NOT NULL,
    num_extra_players INTEGER NOT NULL,
    has_captain BOOLEAN NOT NULL,
    is_active BOOLEAN NOT NULL
);

CREATE TABLE t_dependencies_sports (
    id INTEGER PRIMARY KEY DEFAULT NEXTVAL('t_dependencies_sports_id_seq'),
    dependency_id INTEGER NOT NULL,
    sport_id INTEGER NOT NULL,
    FOREIGN KEY (dependency_id) REFERENCES t_dependency(id) ON DELETE CASCADE,
    FOREIGN KEY (sport_id) REFERENCES t_sport(id) ON DELETE CASCADE
);

CREATE TABLE t_event (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    ins_start_date DATE NOT NULL,
    ins_end_date DATE NOT NULL
);

CREATE TABLE t_team (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('t_team_seq'),
    name VARCHAR(150) NOT NULL,
    record_date DATE NOT NULL,
    is_active BOOLEAN NOT NULL,
    dependencies_sports_id INTEGER NOT NULL,
    event_id INTEGER NOT NULL,
    FOREIGN KEY (dependencies_sports_id) REFERENCES t_dependencies_sports(id) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES t_event(id) ON DELETE CASCADE
);

CREATE TABLE t_player (
    id UUID PRIMARY KEY,
    account_number VARCHAR(10) NOT NULL,
    first_name VARCHAR(75) NOT NULL,
    last_name VARCHAR(75) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    birthday DATE NOT NULL,
    gender type_gender NOT NULL,
    photo VARCHAR(150) NOT NULL,
    is_active BOOLEAN NOT NULL,
    semester SMALLINT NOT NULL,
    p_group SMALLINT NOT NULL,
    is_captain BOOLEAN NOT NULL,
    team_id BIGINT NOT NULL,
    UNIQUE(account_number, email),
    FOREIGN KEY (team_id) REFERENCES t_team(id) ON DELETE CASCADE
);

CREATE TABLE t_employee (
    id UUID PRIMARY KEY,
    account_number VARCHAR(10) NOT NULL,
    first_name VARCHAR(75) NOT NULL,
    last_name VARCHAR(75) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    birthday DATE NOT NULL,
    gender type_gender NOT NULL,
    photo VARCHAR(150) NOT NULL,
    is_active BOOLEAN NOT NULL,
    role type_role NOT NULL,
    UNIQUE(account_number, email),
    FOREIGN KEY (dependency_id) REFERENCES t_dependency(id) ON DELETE CASCADE
);