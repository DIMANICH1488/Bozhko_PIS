create table student
(
    id         serial PRIMARY KEY,
    first_name varchar(64),
    last_name  varchar(64) NOT NULL,
    email      varchar(64) NOT NULL UNIQUE,
    password   varchar(16) NOT NULL
);

create table professor
(
    id         serial PRIMARY KEY,
    first_name varchar(64),
    last_name  varchar(64) NOT NULL,
    email      varchar(64) NOT NULL UNIQUE,
    password   varchar(16) NOT NULL
);
create table exam
(
    id           serial PRIMARY KEY,
    name         varchar(64),
    date         timestamp NOT NULL,
    professor_id serial REFERENCES professor
);

create table exam_result
(
    exam_id    serial REFERENCES exam,
    student_id serial REFERENCES student,
    grade      int
        CONSTRAINT valid_grade CHECK ( grade >= 0 and grade <= 100)
);

create table specialization
(
    id                 serial PRIMARY KEY,
    name               varchar(64),
    max_student_number int NOT NULL
);


create table enrollment
(
    student_id        serial REFERENCES student,
    specialization_id serial REFERENCES specialization,
    priority          int UNIQUE
        CONSTRAINT valid_grade CHECK ( priority >= 1 and priority <= 3)
);

CREATE VIEW users AS
SELECT s.email, s.password
FROM student s
UNION
SELECT pr.email, pr.password
FROM professor pr;

CREATE VIEW user_roles AS
SELECT s.email, 'student' as role
FROM student s
UNION
SELECT pr.email, 'professor'
FROM professor pr;