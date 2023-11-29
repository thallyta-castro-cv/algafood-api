create table tb_product_photo (
	product_id bigint not null,
	file_name varchar(255) not null,
	description varchar(255),
	content_type varchar(255) not null,
	size bigint not null,

	primary key (product_id),
	constraint fk_product_photo foreign key (product_id) references tb_products (id)
) engine=InnoDB default charset=utf8;
