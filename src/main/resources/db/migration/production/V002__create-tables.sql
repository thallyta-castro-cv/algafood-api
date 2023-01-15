create table tb_groups_permissions (group_id bigint not null,
permission_id bigint not null) engine = InnoDB;

create table tb_cities (id bigint not null,
name varchar(255) not null,
state_id bigint not null,
primary key (id)) engine = InnoDB;

create table tb_form_payments (id bigint not null auto_increment,
description varchar(255) not null,
primary key (id)) engine = InnoDB;

create table tb_groups (id bigint not null auto_increment,
name varchar(255) not null,
primary key (id)) engine = InnoDB;

create table tb_permissions (id bigint not null auto_increment,
description varchar(255) not null,
name varchar(255) not null,
primary key (id)) engine = InnoDB;

create table tb_products (id bigint not null auto_increment,
active bit,
description varchar(255),
name varchar(255),
price decimal(19,2),
restaurant_id bigint,
primary key (id)) engine = InnoDB;

create table tb_restaurant_form_payment (restaurant_id bigint not null,
form_payment_id bigint not null) engine = InnoDB;

create table tb_restaurants (id bigint not null auto_increment,
address_cep varchar(255),
address_complement varchar(255),
address_neighborhood varchar(255),
address_number varchar(255),
address_street varchar(255),
created_date datetime not null,
name varchar(255) not null,
shipping_fee decimal(19,2) not null,
updated_date datetime not null,
address_city_id bigint,
kitchen_id bigint not null,
primary key (id)) engine = InnoDB;

create table tb_states (id bigint not null auto_increment,
name varchar(255) not null,
primary key (id)) engine = InnoDB;

create table tb_users (id bigint not null auto_increment,
created_date datetime not null,
email varchar(255) not null,
name varchar(255) not null,
password varchar(255) not null,
primary key (id)) engine = InnoDB;

create table tb_user_groups (user_id bigint not null,
group_id bigint not null) engine = InnoDB;

alter table tb_groups_permissions add constraint fk_groups_permissions_permission foreign key (permission_id) references tb_permissions (id);

alter table tb_groups_permissions add constraint fk_groups_permissions_groups foreign key (group_id) references tb_groups (id);

alter table tb_cities add constraint fk_cities_states foreign key (state_id) references tb_states (id);

alter table tb_products add constraint fk_cities_restaurants foreign key (restaurant_id) references tb_restaurants (id);

alter table tb_restaurant_form_payment add constraint fk_restaurant_form_payment_payments foreign key (form_payment_id) references tb_form_payments (id);

alter table tb_restaurant_form_payment add constraint fk_restaurant_form_payment_restaurants foreign key (restaurant_id) references tb_restaurants (id);

alter table tb_restaurants add constraint fk_resturants_cities foreign key (address_city_id) references tb_cities (id);

alter table tb_restaurants add constraint fk_resturants_kitchens foreign key (kitchen_id) references tb_kitchens (id);

alter table tb_user_groups add constraint fk_user_groups_group foreign key (group_id) references tb_groups (id);

alter table tb_user_groups add constraint fk_user_groups_users foreign key (user_id) references tb_users (id);

