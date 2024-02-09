alter table ticket add code varchar(36) not null after id;
update ticket set code = uuid();
alter table ticket add constraint uk_ticket_code unique (code);

