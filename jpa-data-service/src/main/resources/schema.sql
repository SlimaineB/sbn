

CREATE TABLE product_category
(
   id bigint PRIMARY KEY NOT NULL,
   name varchar(50)
)
;

CREATE TABLE product
(
   id bigint PRIMARY KEY NOT NULL,
   creation_date date,
   name varchar(50),
   price decimal(10,2),
   category_id bigint
);