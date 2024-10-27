-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za oba user-a je 123



insert into COMPANY (name, adress, description, average_grade, works_from, works_to) values ('Company air time', 'Mise dimitrijevica 40', 'opis komapnije 1', 5.5, '08:00', '17:00');
insert into COMPANY (name, adress, description, average_grade, works_from, works_to) values ('Company 222', 'adresa123', 'opis komapnije 122', 6, '07:00', '18:00');
insert into COMPANY (name, adress, description, average_grade, works_from, works_to) values ('Company 333', 'adresa 55555', 'opis komapnije 133', 9, '08:00', '19:00');
insert into COMPANY (name, adress, description, average_grade, works_from, works_to) values ('Company 444', 'adresa 444', 'opis komapnije 144', 4, '09:00', '20:00');
insert into COMPANY (name, adress, description, average_grade, works_from, works_to) values ('Company 555', 'adresa 567', 'opis komapnije 146', 4, '06:00', '16:00');



INSERT INTO USERS (username, password, first_name, last_name, email, enabled, city, country, phone_number, profesion, information_about_company, last_password_reset_date, penals) VALUES ('mare', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'stupar@gmail.com', true,'Indjija','Serbia','0641360754','programmer','good','2017-10-01 21:58:58.508-07', 0);
INSERT INTO USERS (username, password, first_name, last_name, email, enabled, city, country, phone_number, profesion, information_about_company, last_password_reset_date, penals) VALUES ('nidza', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'nikola@example.com', true,'Indjija','Serbia','0641360754','programmer','good','2017-10-01 21:58:58.508-07', 0);
insert into USERS (username, password, first_name, last_name, email, enabled, city, country, phone_number, profesion, information_about_company, last_password_reset_date, penals) VALUES ('pera', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'PETAR', 'PERIC', 'petar@example.com', true,'Indjija','Serbia','0641360754','programmer','good','2017-10-01 21:58:58.508-07', 0);
insert into USERS (username, password, first_name, last_name, email, enabled, city, country, phone_number, profesion, information_about_company, last_password_reset_date, penals) VALUES ('joca', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'JOVAN', 'JOVANOVIC', 'jovan@example.com', true,'Indjija','Serbia','0641360754','programmer','good','2017-10-01 21:58:58.508-07', 0);

insert into USERS (username, password, first_name, last_name, email, enabled, city, country, phone_number, profesion, information_about_company, last_password_reset_date, company_id, penals) VALUES ('vlada', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'VLADIMIR', 'VLADIMIROVIC', 'vladi@example.com', true,'Indjija','Serbia','0641360754','programmer','good','2017-10-01 21:58:58.508-07', 1, 0);

INSERT INTO ROLE (name) VALUES ('ROLE_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_SYSTEM_ADMIN');
INSERT INTO ROLE (name) VALUES ('ROLE_COMPANY_ADMIN');

INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1); -- user-u dodeljujemo rolu USER
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 1); -- admin-u dodeljujemo rolu USER
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2); -- user-u dodeljujemo rolu SYSTEM_ADMIN
INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 1); -- admin-u dodeljujemo rolu USER
INSERT INTO USER_ROLE (user_id, role_id) VALUES (4, 1); -- user-u dodeljujemo rolu USER
INSERT INTO USER_ROLE (user_id, role_id) VALUES (5, 3); -- user-u dodeljujemo rolu COMPANY_ADMIN






INSERT INTO APPOINTMENT(date_and_time, duration, administrator_id, company_id, status) VALUES ('2024-09-24 13:00:00', 30, 1, 1, 'FREE');
INSERT INTO APPOINTMENT(date_and_time, duration, administrator_id, company_id, status) VALUES ('2024-09-24 13:00:00', 30, 1, 1, 'FREE');

INSERT INTO EQUIPMENT(name, description, price, company_id) VALUES ('bandages', 'opis opreme', 100, 1);
INSERT INTO EQUIPMENT(name, description, price, company_id) VALUES ('bandages2', 'opis opreme23', 100, 1);
INSERT INTO EQUIPMENT(name, description, price, company_id) VALUES ('band aids', 'opis opreme', 100, 2);
INSERT INTO EQUIPMENT(name, description, price, company_id) VALUES ('uniform', 'opis opreme', 100, 3);
INSERT INTO EQUIPMENT(name, description, price, company_id) VALUES ('clogs', 'opis opreme', 100, 4);
INSERT INTO EQUIPMENT(name, description, price, company_id) VALUES ('first aid equipment', 'opis opreme', 100, 5);


INSERT INTO GRADE(grade_value, reason, company_id, guest_id) VALUES (5, 'dobra kompoanija', 1, 4);
INSERT INTO GRADE(grade_value, reason, company_id, guest_id) VALUES (6, 'dobra kompoanija', 1, 4);
INSERT INTO GRADE(grade_value, reason, company_id, guest_id) VALUES (6, 'dobra kompoanija', 2, 4);
INSERT INTO GRADE(grade_value, reason, company_id, guest_id) VALUES (9, 'dobra kompoanija', 3, 4);
INSERT INTO GRADE(grade_value, reason, company_id, guest_id) VALUES (4, 'dobra kompoanija', 4, 4);
INSERT INTO GRADE(grade_value, reason, company_id, guest_id) VALUES (4, 'dobra kompoanija', 5, 4);



INSERT INTO COMPLAINT(guest_id, company_id, company_admin_id, complaint_text, response) VALUES (1, 1, NULL, 'Service quality was unsatisfactory', NULL);

INSERT INTO RESERVATION(status, total_price, appointment_id, user_id) VALUES ('SCHEDULED', 200, 1, 1);
INSERT INTO RESERVATION(status, total_price, appointment_id, user_id) VALUES ('SCHEDULED', 100, 2, 3);

INSERT INTO RESERVATION_EQUIPMENT(equipment_id, reservation_id) VALUES (1, 1);
INSERT INTO RESERVATION_EQUIPMENT(equipment_id, reservation_id) VALUES (2, 1);
INSERT INTO RESERVATION_EQUIPMENT(equipment_id, reservation_id) VALUES (2, 2);