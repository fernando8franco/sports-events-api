DROP TABLE IF EXISTS t_event;
DROP TABLE IF EXISTS t_dependencies_sports;
DROP TABLE IF EXISTS t_dependency;
DROP TABLE IF EXISTS t_sport;
DROP TYPE IF EXISTS dependency_category;
DROP TYPE IF EXISTS type_gender;
DROP SEQUENCE IF EXISTS t_dependency_id_seq;
DROP SEQUENCE IF EXISTS t_sport_id_seq;
DROP SEQUENCE IF EXISTS t_dependencies_sports_id_seq;

CREATE TYPE dependency_category AS ENUM ('HIGH_SCHOOL', 'UNIVERSITY', 'DIRECTION');
CREATE TYPE type_gender AS ENUM ('MALE', 'FEMALE', 'NO_BINARY', 'OTHER');

CREATE SEQUENCE t_dependency_id_seq INCREMENT 1 START 100;
CREATE SEQUENCE t_sport_id_seq INCREMENT 1 START 100;
CREATE SEQUENCE t_dependencies_sports_id_seq INCREMENT 1 START 1;

CREATE TABLE t_dependency (
    id INTEGER PRIMARY KEY DEFAULT NEXTVAL('t_dependency_id_seq'),
    name VARCHAR(150) NOT NULL,
    category dependency_category NOT NULL
);

CREATE TABLE t_sport (
    id INTEGER PRIMARY KEY DEFAULT NEXTVAL('t_sport_id_seq'),
    name VARCHAR(150) NOT NULL,
    gender type_gender NOT NULL,
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
