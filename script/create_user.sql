create table CUSTOMERS
(
    id int generated always as identity,
    username varchar(32) not null,
    first_name varchar(32) not null,
    last_name varchar(32),
    gender varchar(32),
    dob DATE,
    email varchar(64) not null,
    phone varchar(32),
    password varchar(255) not null
);

create unique index CUSTOMERS_EMAIL_UINDEX
    on CUSTOMERS (email);

create unique index CUSTOMERS_ID_UINDEX
    on CUSTOMERS (id);

create unique index CUSTOMERS_PHONE_UINDEX
    on CUSTOMERS (phone);

create unique index CUSTOMERS_USERNAME_UINDEX
    on CUSTOMERS (username);

alter table CUSTOMERS
    add constraint CUSTOMERS_PK
        primary key (id);