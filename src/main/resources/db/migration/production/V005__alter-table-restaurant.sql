alter table tb_restaurants add active boolean not null;
update tb_restaurants set active = true;
