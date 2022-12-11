insert into tb_kitchens (id, name) values (1, 'Tailandesa');
insert into tb_kitchens (id, name) values (2, 'Indiana');

insert into tb_restaurants (id, name, shipping_fee, kitchen_id) values (1, 'Thai Gourmet', 10, 1);
insert into tb_restaurants (id, name, shipping_fee, kitchen_id) values (2, 'Thai Delivery', 9.50, 1);
insert into tb_restaurants (id, name, shipping_fee, kitchen_id) values (3, 'Tuk Tuk Comida Indiana', 15, 2);

insert into tb_states (id, name) values (1, 'Minas Gerais');
insert into tb_states (id, name) values (2, 'São Paulo');
insert into tb_states (id, name) values (3, 'Ceará');

insert into tb_cities (id, name, state_id) values (1, 'Uberlândia', 1);
insert into tb_cities (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into tb_cities (id, name, state_id) values (3, 'São Paulo', 2);
insert into tb_cities (id, name, state_id) values (4, 'Campinas', 2);
insert into tb_cities (id, name, state_id) values (5, 'Fortaleza', 3);

