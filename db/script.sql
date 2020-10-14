CREATE DATABASE IF NOT EXISTS Autobase;

CREATE TABLE drivers(
id_driver INTEGER NOT NULL PRIMARY KEY autoincrement,
driver_name text,
driver_sirname text);

CREATE TABLE car_mark(
id_mark INTEGER NOT NULL PRIMARY KEY autoincrement,
mark text);

CREATE TABLE car_info(
id_car INTEGER NOT NULL PRIMARY KEY autoincrement,
car_number TEXT,
id_mark_info INTEGER NOT NULL,
FOREIGN KEY (id_mark_info) REFERENCES car_mark (id_mark));

CREATE TABLE dc_connection(
id_d_con INTEGER NOT NULL,
id_c_con INTEGER NOT NULL,
FOREIGN KEY (id_d_con) REFERENCES drivers (id_drivers),
FOREIGN KEY (id_c_con) REFERENCES car_info (id_car));

INSERT INTO drivers (driver_name) VALUES
('Ivan Ivanov'), ('Alex Simonov'), ('Oleg Smirnov'),
('Olga Kravec'), ('Irina Bogdanova'), ('Sergei Volodin'),
('Denis Volodin'), ('Olga Borisova'), ('Vadim Privalov'),
('Anna Pozdner'), ('Arina Panova'), ('Vasiliy Pupkin'),
('Vladimir Medvedev'), ('Ksenia Sobchak'), ('Oleg Orlov'),
('Bogdan Smirnov'), ('Sara Konnor'), ('Vera Belova'),
('Yana Chernova'), ('Julia Vern'), ('Roman Obramov');

INSERT INTO car_mark (mark) VALUES
('Audi'), ('BMW'), ('Ferrari'), ('Ford'), ('Honda'),
('Infiniti'), ('Jeep'), ('KIA'), ('Lada'), ('Skoda'),
('Smart'), ('Subaru'), ('Zhiguli');

INSERT INTO car_info (car_number, id_mark_info) VALUES
('а123хх', 2), ('в123хх', 3), ('р007ор', 5), ('с888сс', 10),
('д090ит', 9), ('н112пп', 11), ('а362вы', 1), ('и777тс', 6),
('л930на', 12), ('д032ит', 7), ('ж668ур', 13), ('х404на', 13),
('т378ао', 10), ('в746ла', 5), ('в855ду', 2), ('л757вв', 8);

INSERT INTO dc_connection (id_d_con, id_c_con) VALUES
(1, 1), (1, 4), (2, 1), (3, 9), (4, 12), (5, 12),
(6, 10), (7, 3), (7, 7), (8, 6), (9, 13), (10, 1),
(11, 9), (11, 15), (11, 16), (12, 3), (13, 5),
(14, 5), (15, 9), (16, 8), (16, 12), (17, 13),
(17, 14), (18, 8), (19, 10), (19, 2), (20, 11),
(21, 15), (21, 16);