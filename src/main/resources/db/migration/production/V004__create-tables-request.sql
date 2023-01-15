create table tb_request (
id bigint not null auto_increment,
address_cep varchar(255),
address_complement varchar(255),
address_neighborhood varchar(255),
address_number varchar(255),
address_street varchar(255),
cancellation_date datetime(6),
confirmation_date datetime(6),
created_date datetime,
delivery_date datetime(6),
request_status integer,
shipping_fee decimal(19,2),
subtotal decimal(19,2),
total_value decimal(19,2),
address_city_id bigint,
user_client_id bigint not null,
form_payment_id bigint not null,
restaurant_id bigint not null,
primary key (id))
engine = InnoDB;

create table tb_request_item (
id bigint not null auto_increment,
amount bigint,
note varchar(255),
total_price decimal(19,2),
unit_price decimal(19,2),
product_id bigint not null,
request_id bigint not null,
primary key (id))
engine = InnoDB;

alter table tb_request add constraint fk_request_adress_city foreign key (address_city_id) references tb_cities (id);
alter table tb_request add constraint fk_request_restaurant foreign key (user_client_id) references tb_users (id);
alter table tb_request add constraint fk_request_user_client foreign key (form_payment_id) references tb_form_payments (id);
alter table tb_request add constraint fk_request_form_payment foreign key (restaurant_id) references tb_restaurants (id);
alter table tb_request_item add constraint fk_item_request_request foreign key (product_id) references tb_products (id);

alter table tb_request_item add constraint fk_item_request_product foreign key (request_id) references tb_request (id);