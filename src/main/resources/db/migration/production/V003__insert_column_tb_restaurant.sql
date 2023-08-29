alter table tb_restaurants add open boolean not null;
update tb_restaurants set open = false;