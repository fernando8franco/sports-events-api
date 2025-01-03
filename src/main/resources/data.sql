INSERT INTO t_event (name, start_date, end_date, ins_start_date, ins_end_date)
VALUES
    ('Event 1', '2024-01-15', '2024-01-17', '2024-01-15', '2024-01-17'),
    ('Event 2', '2024-02-10', '2024-02-12', '2024-02-10', '2024-02-12'),
    ('Event 3', '2024-03-05', '2024-03-07', '2024-03-05', '2024-03-07'),
    ('Event 4', '2024-04-20', '2024-04-22', '2024-04-20', '2024-04-22'),
    ('Event 5', '2024-05-01', '2024-05-03', '2024-05-01', '2024-05-03'),
    ('Event 6', '2024-06-25', '2024-06-27', '2024-06-25', '2024-06-27'),
    ('Event 7', '2024-07-10', '2024-07-12', '2024-07-10', '2024-07-12'),
    ('Event 8', '2024-08-15', '2024-08-17', '2024-08-15', '2024-08-17'),
    ('Event 9', '2024-09-18', '2024-09-20', '2024-09-18', '2024-09-20'),
    ('Event 10', '2024-10-05', '2024-10-07', '2024-10-05', '2024-10-07');

INSERT INTO t_dependency (name, category)
VALUES
    ('Universidad Autonoma del Estado de Hidalgo', 'UNIVERSITY'),
    ('University of Mexico', 'UNIVERSITY'),
    ('High School no. 2', 'HIGH_SCHOOL'),
    ('University of Michigan', 'UNIVERSITY'),
    ('University of Toledo', 'UNIVERSITY'),
    ('HighSchool no. 1', 'UNIVERSITY'),
    ('Computer Center', 'DIRECTION');

INSERT INTO t_sport (name, category, num_players, num_extra_players, has_captain, is_active)
VALUES
    ('Soccer', 'MALE', 11, 3, TRUE, TRUE),
    ('Basketball', 'FEMALE', 5, 2, TRUE, TRUE),
    ('Baseball', 'MALE', 9, 0, TRUE, TRUE),
    ('Volleyball', 'FEMALE', 6, 0, TRUE, TRUE),
    ('Tennis', 'MALE', 1, 0, FALSE, TRUE),
    ('Rugby', 'MALE', 15, 2, TRUE, TRUE),
    ('Cricket', 'MALE', 11, 4, TRUE, TRUE),
    ('Handball', 'FEMALE', 7, 2, TRUE, TRUE),
    ('Badminton', 'FEMALE', 1, 0, FALSE, TRUE),
    ('Table Tennis', 'MALE', 1, 0, FALSE, TRUE);

INSERT INTO t_employee (
    id, account_number, first_name, last_name, email, phone_number, birthday, gender, photo, is_active, role, dependency_id
) VALUES
    (gen_random_uuid(), '352761', 'Fernando', 'Franco', 'franco@example.com', '1234567890', '1990-01-15', 'MALE', '', TRUE, 'SUPERADMIN', 101),
    (gen_random_uuid(), '001235', 'Jane', 'Smith', 'jane.smith@example.com', '1234567891', '1992-03-22', 'FEMALE', '', TRUE, 'ADMIN', 102),
    (gen_random_uuid(), '001236', 'Alice', 'Brown', 'alice.brown@example.com', '1234567892', '1988-06-10', 'FEMALE', '', TRUE, 'EMPLOYEE', 100),
    (gen_random_uuid(), '001237', 'Bob', 'Johnson', 'bob.johnson@example.com', '1234567893', '1985-11-05', 'MALE', '', TRUE, 'EMPLOYEE', 103),
    (gen_random_uuid(), '001238', 'Charlie', 'Davis', 'charlie.davis@example.com', '1234567894', '1995-07-18', 'MALE', '', TRUE, 'EMPLOYEE', 104);

INSERT INTO t_team (
    name, record_date, is_active, dependencies_sports_id, event_id
) VALUES
    ('Team Alpha', CURRENT_DATE, true, 1, 1);

INSERT INTO t_player (
    id, account_number, first_name, last_name, email, phone_number, birthday, gender, photo, is_active, semester, p_group, is_captain, team_id
) VALUES
    (gen_random_uuid(), '1234567890', 'John', 'Doe', 'john.doe@email.com', '123-456-7890', '2000-01-01', 'MALE', 'path/to/photo.jpg', true, 1, 1, false, 1);

