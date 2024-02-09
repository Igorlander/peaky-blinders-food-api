alter table restaurant add opening tinyint(1) not null;
update restaurant set opening = true;