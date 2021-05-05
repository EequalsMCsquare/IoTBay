create table products
(
    product_id int generated always as identity,
    name varchar(64) not null,
    description varchar(255),
    original_price float not null,
    discount float default 0,
    cost float not null,
    stock int not null
);

create unique index PRODUCTS_PRODUCT_ID_UINDEX
	on products (product_id);

alter table products
    add constraint PRODUCTS_PK
        primary key (product_id);
