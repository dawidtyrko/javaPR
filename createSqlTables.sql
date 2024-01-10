create schema dogs_project;
create table dogs_project.User(
    Id int ,
    first_name varchar(50),
    last_name varchar(50),
    username varchar(60) unique primary key ,
    password varchar(70),
    enabled  boolean default true
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

create table dogs_project.roles(
    username varchar(60) primary key,
    role varchar(60) not null,
    foreign key (username) references User(username)
);
INSERT INTO dogs_project.User(Id,first_name, last_name, username, password) values (1,'Jan','Kowalski','jano','{bcrypt}$2a$10$MIlsRU9rS3qtndVwxLMBIuUJGDnduWcvQ/AoAMgpMX5y2qTWTfTgm');
insert into dogs_project.Dog(name, image_link, playfulness, good_with_children, good_with_other_dogs, energy) values ("labrador retriever","https://api-ninjas.com/images/dogs/labrador_retriever.jpg",5,5,5,3);
insert into dogs_project.roles(username, role) VALUES ('jano','ADMIN');