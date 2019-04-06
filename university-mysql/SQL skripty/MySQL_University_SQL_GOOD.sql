CREATE TABLE course (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    enddate date,
    name character varying(255),
    startdate date,
    professor_id bigint    
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_slovak_ci;

CREATE TABLE student (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstname character varying(255),
    lastname character varying(255),
    state integer
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_slovak_ci;

CREATE TABLE curriculum (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description character varying(255),
    FOREIGN KEY(id) REFERENCES course(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_slovak_ci;

CREATE TABLE professor (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstname character varying(255),
    lastname character varying(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_slovak_ci;

CREATE TABLE course_student (
    courses_id bigint NOT NULL,
    students_id bigint NOT NULL,
	PRIMARY KEY(courses_id, students_id),
    FOREIGN KEY(courses_id) REFERENCES course(id),
    FOREIGN KEY(students_id) REFERENCES student(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_slovak_ci;

alter table course
    add constraint fk_course_professor 
    foreign key (professor_id)
    references professor(id);
