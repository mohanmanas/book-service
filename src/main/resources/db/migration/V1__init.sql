CREATE TABLE book (
    book_id int NOT NULL AUTO_INCREMENT,
    book_name varchar(250) NOT NULL,
    author varchar(250) NOT NULL,
    price decimal(10,2) NOT NULL,
    published_year int NOT NULL,
    PRIMARY KEY (book_id)
); 