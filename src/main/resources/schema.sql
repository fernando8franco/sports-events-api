DROP TABLE IF EXISTS t_event;
DROP TABLE IF EXISTS t_dependency;
DROP TYPE IF EXISTS dependency_category;

CREATE TYPE dependency_category AS ENUM ('HIGH_SCHOOL', 'UNIVERSITY', 'DIRECTION');

CREATE TABLE t_event (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    ins_start_date DATE NOT NULL,
    ins_end_date DATE NOT NULL
);

CREATE TABLE t_dependency (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    category dependency_category NOT NULL
);