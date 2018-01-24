CREATE DATABASE demo_books ;
USE demo_books;

create table `books`  (
   id integer not null,
   name varchar(255) not null  COLLATE 'utf8_general_ci',
   publisher varchar(255) not null COLLATE 'utf8_general_ci',
   page integer(255) not null default 0,
   primary key (id)
);

create table `impression` (
   id  integer not null,
   name  text not null  COLLATE 'utf8_general_ci',
   book_id 	 integer not null,
   primary key (id),
   FOREIGN KEY (book_id) REFERENCES `books` (id)
);


insert into `books` (id, name, publisher, page) values (1, 'おはようございます', '中山様', 77);
insert into `books` (id, name, publisher, page) values (2, 'QUEEN', 'BSan', 100);
insert into `books` (id, name, publisher, page) values (3, 'SON', 'ASan', 67);


INSERT INTO `comment` VALUES (1,'Wonderfull !! it made my year',3),
(2,'sugoi desu ne, kandoushimasu',4),
(3,'i just want to say to the publisher',5),
(4,'Its a good book',5),
(6,'Amazing',3),
(7,'Wonderfull',3),
(8,'So awsome',3);