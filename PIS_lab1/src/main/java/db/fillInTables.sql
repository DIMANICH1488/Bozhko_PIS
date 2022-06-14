INSERT INTO student (first_name, last_name, email, password)
VALUES ('Dima', 'Bozhko', 'dima@gmail.com', 'Dima');

INSERT INTO student (first_name, last_name, email, password)
VALUES ('Pavel', 'Koval', 'pavel@gmail.com', 'Pavel');

INSERT INTO student (first_name, last_name, email, password)
VALUES ('Student', 'Student', 'student@gmail.com', 'Student');

INSERT INTO professor (first_name, last_name, email, password)
VALUES ('Andrew', 'Ng', 'ng@gmail.com', 'Andrew');

INSERT INTO professor (first_name, last_name, email, password)
VALUES ('Nadia', 'Ned', 'nn@gmail.com', 'Nadia');

INSERT INTO professor (first_name, last_name, email, password)
VALUES ('Roman', 'Kagarlitskii', 'kagarlitskii@gmail.com', 'Roman');

INSERT INTO exam (name, date, professor_id)
VALUES ('Math', '2022-04-06', 3);

INSERT INTO exam (name, date, professor_id)
VALUES ('ML', '2022-04-08', 2);

INSERT INTO exam (name, date, professor_id)
VALUES ('DS', '2022-05-08', 1);

INSERT INTO exam (name, date, professor_id)
VALUES ('Linear Algebra', '2022-05-10', 1);

INSERT INTO exam (name, date, professor_id)
VALUES ('Calculus', '2022-05-11', 1);

INSERT INTO exam (name, date, professor_id)
VALUES ('Calculus', '2022-05-10', 3);

INSERT INTO exam (name, date, professor_id)
VALUES ('Java', '2022-05-11', 2);

INSERT INTO exam (name, date, professor_id)
VALUES ('Python', '2022-05-11', 2);

INSERT INTO exam (name, date, professor_id)
VALUES ('C++', '2022-05-11', 2);

INSERT INTO specialization(name,max_student_number) VALUES ('Computer Science', 110);
INSERT INTO specialization(name,max_student_number) VALUES ('System Analysis', 120);
