set foreign_key_checks = 0;

delete from tb_cities;
delete from tb_kitchens;
delete from tb_states;
delete from tb_form_payments;
delete from tb_groups;
delete from tb_groups_permissions;
delete from tb_permissions;
delete from tb_products;
delete from tb_restaurants;
delete from tb_restaurant_form_payment;
delete from tb_users;
delete from tb_user_groups;

set foreign_key_checks = 1;

alter table tb_cities auto_increment = 1;
alter table tb_kitchens auto_increment = 1;
alter table tb_states auto_increment = 1;
alter table tb_form_payments auto_increment = 1;
alter table tb_groups auto_increment = 1;
alter table tb_permissions auto_increment = 1;
alter table tb_products auto_increment = 1;
alter table tb_restaurants auto_increment = 1;
alter table tb_users auto_increment = 1;

insert into tb_kitchens (id, name) values (1, 'Tailandesa');
insert into tb_kitchens (id, name) values (2, 'Indiana');

insert into tb_states (id, name) values (1, 'Minas Gerais');
insert into tb_states (id, name) values (2, 'São Paulo');
insert into tb_states (id, name) values (3, 'Ceará');

insert into tb_cities (id, name, state_id) values (1, 'Uberlândia', 1);
insert into tb_cities (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into tb_cities (id, name, state_id) values (3, 'São Paulo', 2);
insert into tb_cities (id, name, state_id) values (4, 'Campinas', 2);
insert into tb_cities (id, name, state_id) values (5, 'Fortaleza', 3);

insert into tb_form_payments (id, description) values (1, 'Cartão de crédito');
insert into tb_form_payments (id, description) values (2, 'Cartão de débito');
insert into tb_form_payments (id, description) values (3, 'Dinheiro');
insert into tb_form_payments (id, description) values (4, 'Pix');

insert into tb_permissions (id, name, description) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into tb_permissions (id, name, description) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into tb_products (name, description, price, active, restaurant_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into tb_products (name, description, price, active, restaurant_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into tb_products (name, description, price, active, restaurant_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);

insert into tb_products (name, description, price, active, restaurant_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into tb_products (name, description, price, active, restaurant_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);

insert into tb_users (id, name, email, password, created_date) values
(1, 'João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp),
(3, 'José Souza', 'jose.aux@algafood.com', '123', utc_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp);