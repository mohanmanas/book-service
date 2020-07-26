CREATE TABLE book (
    book_id int NOT NULL AUTO_INCREMENT,
    book_name varchar(250) NOT NULL,
    author varchar(250) NOT NULL,
    price decimal(10,2) NOT NULL,
    published_year int NOT NULL,
    book_count int NOT NULL,
    price_per_day  decimal(10,2) NOT NULL,
    created_user varchar(250),
    created_date_time DATE,
    modified_user varchar(250),
    modified_date_time DATE,
    version BIGINT,
    PRIMARY KEY (book_id)
); 