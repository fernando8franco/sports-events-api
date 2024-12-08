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

INSERT INTO t_dependency (name, category) VALUES ('Universidad Autonoma del Estado de Hidalgo', 'UNIVERSITY');
INSERT INTO t_dependency (name, category) VALUES ('University of Mexico', 'UNIVERSITY');
INSERT INTO t_dependency (name, category) VALUES ('High School no. 2', 'HIGH_SCHOOL');
INSERT INTO t_dependency (name, category) VALUES ('University of Michigan', 'UNIVERSITY');
INSERT INTO t_dependency (name, category) VALUES ('University of Toledo', 'UNIVERSITY');
INSERT INTO t_dependency (name, category) VALUES ('HighSchool no. 1', 'UNIVERSITY');
INSERT INTO t_dependency (name, category) VALUES ('Computer Center', 'DIRECTION');