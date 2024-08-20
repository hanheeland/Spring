insert into users(id, name, join_date, password, ssn) values (9001, 'kim', now(), 'pass1', '960406-1111111');
insert into users(id, name, join_date, password, ssn) values (9002, 'lee', now(), 'pass2', '960406-1111112');
insert into users(id, name, join_date, password, ssn) values (9003, 'park', now(), 'pass3', '960406-1111113');

insert into post(description, user_id) values ('My first post', 9001);
insert into post(description, user_id) values ('My first second', 9001);