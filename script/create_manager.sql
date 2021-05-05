create table managers
(
    email varchar(64) not null,
    password varchar(255) not null,
    first_name varchar(32) not null,
    last_name varchar(32),
    phone varchar(32) not null,
    gender varchar(32),
    dob date,
    privilege int default 1
);

create unique index MANAGERS_EMAIL_UINDEX
	on managers (email);

create unique index MANAGERS_PHONE_UINDEX
	on managers (phone);

alter table managers
    add constraint MANAGERS_PK
        primary key (email);