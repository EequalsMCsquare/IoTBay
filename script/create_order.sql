create table order_items
(
    order_items_id int generated always as identity,
    product_id int not null
        constraint ORDER_ITEMS_PRODUCTS_PRODUCT_ID_FK
            references PRODUCTS,
    count int default 1
);

create unique index ORDER_ITEMS_ORDER_ITEMS_ID_UINDEX
	on order_items (order_items_id);

alter table order_items
    add constraint ORDER_ITEMS_PK
        primary key (order_items_id);