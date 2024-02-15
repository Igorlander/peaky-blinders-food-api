set foreign_key_checks = 0;

delete from product;
delete from restaurant_payment_Method;
delete from restaurant;
delete from kitchen;
delete from city;
delete from state;
delete from payment_method;
delete from tb_group;
delete from group_permission;
delete from permission;
delete from tb_user;
delete from user_group;
delete from restaurant_user_responsible;
delete from ticket;
delete from item_order;
delete from photo_product;
set foreign_key_checks = 1;


alter table city auto_increment = 1;
alter table kitchen auto_increment = 1;
alter table state auto_increment = 1;
alter table payment_method auto_increment = 1;
alter table tb_group auto_increment = 1;
alter table permission auto_increment = 1;
alter table product auto_increment = 1;
alter table restaurant auto_increment = 1;
alter table tb_user auto_increment = 1;
alter table ticket auto_increment = 1;
alter table item_order auto_increment = 1;

insert into kitchen (id,name) values (1,'Tailandesa');
insert into kitchen (id,name) values (2,'Indiana');
insert into kitchen (id,name) values (3,'Argentina');
insert into kitchen (id,name) values (4,'Brasileira');

insert into state (id,name) values (1,'Minas Gerais');
insert into state (id,name) values (2,'São Paulo');
insert into state (id,name) values (3,'Ceará');

insert into city (id,name, state_id) values (1,'Uberlândia', 1);
insert into city (id,name, state_id) values (2,'Belo Horizonte', 1);
insert into city (id,name, state_id) values (3,'São Paulo', 2);
insert into city (id,name, state_id) values (4,'Campinas', 2);
insert into city (id,name, state_id) values (5,'Fortaleza', 3);

insert into restaurant (id,name, shipping_fee, kitchen_id, address_city_id, address_postal_code,address_public_place,address_number,address_complement,address_neighborhood, registration_date, update_date,active,opening) values (1,'Thai Gourmet', 10, 1, 1,'38400-999', 'Rua João Pinheiro', '1000','fundos','Centro', utc_timestamp, utc_timestamp, true, true);
insert into restaurant (id,name, shipping_fee, kitchen_id, address_city_id, address_postal_code,address_public_place,address_number,address_complement,address_neighborhood, registration_date, update_date,active,opening) values (2,'Thai Delivery', 9.50, 1, 3, '38400-444', 'Rua Capitão Machado', '150', 'fundo','Chacara Sto Antônio', utc_timestamp, utc_timestamp, true, true);
insert into restaurant (id,name, shipping_fee, kitchen_id, address_city_id, address_postal_code,address_public_place,address_number,address_complement,address_neighborhood, registration_date, update_date,active,opening) values (3,'Tuk Tuk Comida Indiana', 15, 2, 3, '38400-444', 'Rua Capitão Machado', '150', 'fundo','Chacara Sto Antônio', utc_timestamp, utc_timestamp, true, true);
insert into restaurant (id,name, shipping_fee, kitchen_id, address_city_id, address_postal_code,address_public_place,address_number,address_complement,address_neighborhood, registration_date, update_date,active,opening) values (4,'Java Steakhouse', 12, 1, 1,'38400-999', 'Rua João Pinheiro', '1000','fundos','Centro', utc_timestamp, utc_timestamp, true, true);
insert into restaurant (id,name, shipping_fee, kitchen_id, address_city_id, address_postal_code,address_public_place,address_number,address_complement,address_neighborhood, registration_date, update_date,active,opening) values (5,'Lanchonete do Tio Sam', 11, 2, 3, '38400-444', 'Rua Capitão Machado', '150', 'fundo','Chacara Sto Antônio', utc_timestamp, utc_timestamp, true, true);
insert into restaurant (id,name, shipping_fee, kitchen_id, address_city_id, address_postal_code,address_public_place,address_number,address_complement,address_neighborhood, registration_date, update_date,active,opening) values (6,'Tuk Tuk Comida Indiana', 15, 2, 3, '38400-444', 'Rua Capitão Machado', '150', 'fundo','Chacara Sto Antônio', utc_timestamp, utc_timestamp, true, true);



insert into payment_method (id,description) values (1,'Cartão de crédito');
insert into payment_method (id,description) values (2,'Cartão de débito');
insert into payment_method (id,description) values (3,'Dinheiro');

insert into permission (id,name, description) values (1,'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permission (id,name, description) values (2,'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurant_payment_Method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);



insert into product (id,name, description, price, active, restaurant_id) values (1,'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into product (id,name, description, price, active, restaurant_id) values (2,'Camarão tailandês', '16 camarões grandes ao molho picante', 110, 0, 1);
insert into product (id,name, description, price, active, restaurant_id) values (3,'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into product (id,name, description, price, active, restaurant_id) values (4,'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into product (id,name, description, price, active, restaurant_id) values (5,'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into product (id,name, description, price, active, restaurant_id) values (6,'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into product (id,name, description, price, active, restaurant_id) values (7,'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into product (id,name, description, price, active, restaurant_id) values (8,'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into product (id,name, description, price, active, restaurant_id) values (9,'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

insert into tb_group (id,name) values (1,'Gerente'), (2,'Vendedor'), (3,'Secretária'), (4,'Cadastrador');

insert into group_permission (group_id,permission_id)  values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1);


insert into tb_user (id, name, email, password, registration_date) values
(1, 'João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp),
(3, 'José Souza', 'jose.aux@algafood.com', '123', utc_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp),
(5, 'Manoel Lima', 'manoel.loja@gmail.com','123', utc_timestamp);

insert into user_group (user_id, group_id) values (1, 1), (1, 2), (2, 2);

insert into restaurant_user_responsible (restaurant_id, user_id) values (1, 5), (3, 5);

insert into ticket (id,code, restaurant_id, user_client_id, payment_method_id, address_city_id, address_postal_code,
    address_public_place, address_number, address_complement, address_neighborhood,
    status, creation_date, total_price, shipping_fee, amount)
values (1,'5dc62ec2-9a13-4a80-ad72-6f6a387e1309', 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil',
'CRIADO', utc_timestamp, 298.90, 10, 308.90);

insert into item_order (id, ticket_id, product_id, amount, unit_price, total_price, observation)
values (1, 1, 1, 1, 78.9, 78.9, null);

insert into item_order (id, ticket_id, product_id, amount, unit_price, total_price, observation)
values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');


insert into ticket (id, code, restaurant_id, user_client_id, payment_method_id, address_city_id, address_postal_code,
    address_public_place, address_number, address_complement, address_neighborhood,
    status, creation_date, total_price, shipping_fee, amount)
values (2,'a36b1079-5a52-4b51-a737-43edd3ba8472', 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
'CRIADO', utc_timestamp, 79, 0, 79);

insert into item_order (id, ticket_id, product_id, amount, unit_price, total_price, observation)
values (3, 2, 6, 1, 79, 79, 'Ao ponto');