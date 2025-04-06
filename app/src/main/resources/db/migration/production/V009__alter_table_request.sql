alter table tb_request add code varchar(36) not null after id;
update tb_request set code = uuid();
alter table tb_request add constraint uk_request_code unique (code);