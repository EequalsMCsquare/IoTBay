create table customer
(
    username varchar(64) not null,
    first_name varchar(32) not null,
    last_name varchar(32),
    gender varchar(32),
    dob date,
    email varchar(64) not null,
    phone varchar(32) not null,
    password varchar(255) not null
);

create unique index CUSTOMER_EMAIL_UINDEX
	on CUSTOMERS (email);

create unique index CUSTOMER_USERNAME_UINDEX
	on CUSTOMERS (username);

alter table CUSTOMERS
    add constraint CUSTOMER_PK
        primary key (username);


create table customer_address
(
    username varchar(64) not null
        constraint CUSTOMER_ADDRESS_CUSTOMER_USERNAME_FK
            references CUSTOMERS,
    primary_address varchar(255),
    secondary_address varchar(255),
    third_address varchar(255)
);

create unique index CUSTOMER_ADDRESS_USERNAME_UINDEX
    on customer_address (username);