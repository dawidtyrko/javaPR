create schema dogs_project;
create table dogs_project.users(
    username varchar(60) unique primary key not null ,
    password varchar(70) not null ,
    enabled  tinyint not null
);
create  table dogs_project.Dog(
    Id int primary key auto_increment,
    name varchar(70),
    image_link varchar(100),
    playfulness int,
    good_with_children int,
    good_with_other_dogs int,
    energy int
);

create table dogs_project.authorities(
    username varchar(60) not null ,
    authority varchar(60) not null,
    unique key authorities_idx_1 (username,authority),
    constraint authorities_ibfk_1
    foreign key (username) references users(username)
);
create table dogs_project.user_details(
    username varchar(60) not null,
    first_name varchar(60),
    last_name varchar(60),
    email varchar(80),
    country varchar(60),
    foreign key (username) references users(username)

);
-- bcrypt password fun123
INSERT INTO dogs_project.users(username, password,enabled) values ('jano','{bcrypt}$2a$10$NlXsSaheyHCT3pDd9XSWo.tkR0bNmyvDG9Hv2r7.vGozrRqZGUdlW',1);
INSERT INTO dogs_project.users(username, password,enabled) values ('dave','{bcrypt}$2a$10$NlXsSaheyHCT3pDd9XSWo.tkR0bNmyvDG9Hv2r7.vGozrRqZGUdlW',1);
INSERT INTO dogs_project.users(username, password,enabled) values ('jacob','{bcrypt}$2a$10$NlXsSaheyHCT3pDd9XSWo.tkR0bNmyvDG9Hv2r7.vGozrRqZGUdlW',1);
insert into dogs_project.Dog(name, image_link, playfulness, good_with_children, good_with_other_dogs, energy) values ("labrador retriever","https://api-ninjas.com/images/dogs/labrador_retriever.jpg",5,5,5,3);
insert into dogs_project.authorities(username, authority) VALUES ('jano','ROLE_MANAGER');
insert into dogs_project.authorities(username, authority) VALUES ('jano','ROLE_USER');
insert into dogs_project.authorities(username, authority) VALUES ('dave','ROLE_ADMIN');
insert into dogs_project.authorities(username, authority) VALUES ('dave','ROLE_MANAGER');
insert into dogs_project.authorities(username, authority) VALUES ('dave','ROLE_USER');
insert into dogs_project.authorities(username, authority) VALUES ('jacob','ROLE_USER');
insert into dogs_project.user_details(username, first_name, last_name, email, country) VALUES ('dave','Dawid','Tyrko','someEmail@gmail.com','Poland');
insert into dogs_project.user_details(username, first_name, last_name, email, country) VALUES ('jano','Janek','Kowalski','someEmail@gmail.com','Poland');
insert into dogs_project.user_details(username, first_name, last_name, email, country) VALUES ('jacob','Kuba','Stefanowicz','someEmail@gmail.com','Poland');


