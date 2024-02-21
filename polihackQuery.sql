CREATE TABLE Patient (
	id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	name CHARACTER VARYING(200),
	username CHARACTER VARYING(200),
	password CHARACTER VARYING(200),
	email CHARACTER VARYING(200),
	description CHARACTER VARYING(200),
	dateOfBirth DATE,
	homeworks BIGINT[],
	psychologist_id BIGINT
)

DROP TABLE Patient;

INSERT INTO Patient (name, username, password, email, subscription, description, homeworks, psychologist_id) 
VALUES ('asd', 'asd', 'asd', 'asd', 'asd', 'asd', ARRAY[1, 2, 3], 1);

SELECT * FROM Patient;


CREATE TABLE Psychologist (
	id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	name CHARACTER VARYING(200),
	username CHARACTER VARYING(200),
	password CHARACTER VARYING(200),
	email CHARACTER VARYING(200),
	numar_telefon CHARACTER VARYING(200),
	subscription CHARACTER VARYING(200),
	patients BIGINT[]
)

DROP TABLE Psychologist;

INSERT INTO Psychologist (name, username, password, email, numar_telefon, patients) 
VALUES ('asd', 'asd', 'asd', 'asd', 'asd', ARRAY[1, 2, 3]);

SELECT * FROM Psychologist;


CREATE TABLE Tests (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	image_list BIGINT[],
    variants VARCHAR[],
    correct_answer VARCHAR(255),
	description VARCHAR(255)
);

DROP TABLE Tests;

SELECT * FROM Tests;


CREATE TABLE Images (
	id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	image_data BYTEA,
	description VARCHAR(255)
)

DROP TABLE Images;

SELECT * FROM Images;


SELECT Patient.*
FROM Patient
JOIN Psychologist ON Patient.psychologist_id = Psychologist.id
WHERE Psychologist.username = 'john_doe';



-- Insert 10 fake Psychologists
INSERT INTO Psychologist (name, username, password, email, numar_telefon, subscription)
VALUES
  ('John Doe', 'john_doe', 'password1', 'john.doe@example.com', '123-456-7890', 'Gold'),
  ('Jane Smith', 'jane_smith', 'password2', 'jane.smith@example.com', '987-654-3210', 'Silver'),
  ('Michael Johnson', 'michael_johnson', 'password3', 'michael.johnson@example.com', '555-123-4567', 'Bronze'),
  ('Emily Davis', 'emily_davis', 'password4', 'emily.davis@example.com', '111-222-3333', 'Gold'),
  ('Robert Miller', 'robert_miller', 'password5', 'robert.miller@example.com', '999-888-7777', 'Silver'),
  ('Sophia Wilson', 'sophia_wilson', 'password6', 'sophia.wilson@example.com', '777-666-5555', 'Bronze'),
  ('Daniel Brown', 'daniel_brown', 'password7', 'daniel.brown@example.com', '444-555-6666', 'Gold'),
  ('Olivia Taylor', 'olivia_taylor', 'password8', 'olivia.taylor@example.com', '123-987-4561', 'Silver'),
  ('William Anderson', 'william_anderson', 'password9', 'william.anderson@example.com', '789-456-1230', 'Bronze'),
  ('Ava Martinez', 'ava_martinez', 'password10', 'ava.martinez@example.com', '555-789-4562', 'Gold');

-- Insert 10 fake Patients
INSERT INTO Patient (name, username, password, email, dateOfBirth, description, psychologist_id)
VALUES
  ('Patient1', 'patient1', 'password11', 'patient1@example.com', '1995-05-15', 'Patient with anxiety', 1),
  ('Patient2', 'patient2', 'password12', 'patient2@example.com', '1992-08-20', 'Patient with depression', 1),
  ('Patient3', 'patient3', 'password13', 'patient3@example.com', '1988-02-10', 'Patient with stress', 1),
  ('Patient4', 'patient4', 'password14', 'patient4@example.com', '1990-11-28', 'Patient with insomnia', 1),
  ('Patient5', 'patient5', 'password15', 'patient5@example.com', '1998-04-03', 'Patient with phobia', 5),
  ('Patient6', 'patient6', 'password16', 'patient6@example.com', '1985-07-12', 'Patient with trauma', 1),
  ('Patient7', 'patient7', 'password17', 'patient7@example.com', '1993-09-22', 'Patient with addiction', 7),
  ('Patient8', 'patient8', 'password18', 'patient8@example.com', '1997-01-18', 'Patient with OCD', 2),
  ('Patient9', 'patient9', 'password19', 'patient9@example.com', '1987-06-05', 'Patient with bipolar disorder', 3),
  ('Patient10', 'patient10', 'password20', 'patient10@example.com', '1994-12-30', 'Patient with eating disorder', 8);