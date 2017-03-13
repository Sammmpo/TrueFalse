

/* Replaces all manually inserted data with the default statements. */
DROP TABLE IF EXISTS question;


/* Table for holding statements, name "question" used instead of "statement" to avoid possible programming syntax conflicts with the keyword "statement". */
CREATE TABLE question (
    id integer NOT NULL AUTO_INCREMENT,
    statement VARCHAR(30) NOT NULL,
    answer boolean NOT NULL,
    PRIMARY KEY (id )
);

/* Adds 5 default statements to the database. */

INSERT INTO question (statement, answer)
VALUES ("1 + 1 = 3", false);

INSERT INTO question (statement, answer)
VALUES ("3 + 7 = 10", true);

INSERT INTO question (statement, answer)
VALUES ("5 + 5 = 15", false);

INSERT INTO question (statement, answer)
VALUES ("4 + 8 = 14", false);

INSERT INTO question (statement, answer)
VALUES ("1 + 1 = 2", true);
