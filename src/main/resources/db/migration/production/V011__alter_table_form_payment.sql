alter table tb_form_payments add updated_date datetime null;
update tb_form_payments set updated_date = utc_timestamp;
alter table tb_form_payments modify updated_date datetime not null;