CREATE DATABASE demo_books ;
USE demo_books;

create table `書籍`  (
   id integer not null,
   name varchar(255) not null,
   publisher varchar(255) not null,
   page integer(255) not null default 0,
   primary key (id)
);

create table `書籍感想` (
   id  integer not null,
   name  text not null,
   book_id 	 integer not null,
   primary key (id),
   FOREIGN KEY (book_id) REFERENCES `書籍` (id)
);