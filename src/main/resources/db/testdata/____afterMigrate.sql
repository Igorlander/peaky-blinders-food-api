delete FROM  product cascade;
delete from restaurant_payment_Method cascade;
delete from restaurant cascade;
delete from kitchen cascade ;
delete from city cascade;
delete from state cascade;
delete from payment_method cascade;
delete from tb_group cascade;
delete from group_permission cascade;
delete from permission cascade ;
delete from tb_user cascade;
delete from user_group cascade;



insert into kitchen (name) values ('Tailandesa');
insert into kitchen (name) values ('Indiana');

insert into state (name) values ('Minas Gerais');
insert into state (name) values ('São Paulo');
insert into state (name) values ('Ceará');

insert into city (name, state_id) values ('Uberlândia', 1);
insert into city (name, state_id) values ('Belo Horizonte', 1);
insert into city (name, state_id) values ('São Paulo', 2);
insert into city (name, state_id) values ('Campinas', 2);
insert into city (name, state_id) values ('Fortaleza', 3);

insert into restaurant (name, shipping_fee, kitchen_id, address_city_id, address_postal_code,address_public_place,address_number,address_complement,address_neighborhood, registration_date, update_date) values ('Thai Gourmet', 10, 1, 1,'38400-999', 'Rua João Pinheiro', '1000','fundos','Centro', current_timestamp, current_timestamp);
insert into restaurant (name, shipping_fee, kitchen_id, address_city_id, address_postal_code,address_public_place,address_number,address_complement,address_neighborhood, registration_date, update_date) values ('Thai Delivery', 9.50, 1, 3, '38400-444', 'Rua Capitão Machado', '150', 'fundo','Chacara Sto Antônio', current_timestamp, current_timestamp);
insert into restaurant (name, shipping_fee, kitchen_id, address_city_id, address_postal_code,address_public_place,address_number,address_complement,address_neighborhood, registration_date, update_date) values ('Tuk Tuk Comida Indiana', 15, 2, 3, '38400-444', 'Rua Capitão Machado', '150', 'fundo','Chacara Sto Antônio', current_timestamp, current_timestamp);
insert into restaurant (name, shipping_fee, kitchen_id, address_city_id, address_postal_code,address_public_place,address_number,address_complement,address_neighborhood, registration_date, update_date) values ('Java Steakhouse', 12, 1, 1,'38400-999', 'Rua João Pinheiro', '1000','fundos','Centro', current_timestamp, current_timestamp);
insert into restaurant (name, shipping_fee, kitchen_id, address_city_id, address_postal_code,address_public_place,address_number,address_complement,address_neighborhood, registration_date, update_date) values ('Lanchonete do Tio Sam', 11, 2, 3, '38400-444', 'Rua Capitão Machado', '150', 'fundo','Chacara Sto Antônio', current_timestamp, current_timestamp);
insert into restaurant (name, shipping_fee, kitchen_id, address_city_id, address_postal_code,address_public_place,address_number,address_complement,address_neighborhood, registration_date, update_date) values ('Tuk Tuk Comida Indiana', 15, 2, 3, '38400-444', 'Rua Capitão Machado', '150', 'fundo','Chacara Sto Antônio', current_timestamp, current_timestamp);



insert into payment_method (description) values ('Cartão de crédito');
insert into payment_method (description) values ('Cartão de débito');
insert into payment_method (description) values ('Dinheiro');

insert into permission (name, description) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permission (name, description) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurant_payment_Method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);



insert into product (name, description, price, active, restaurant_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, true, 1);
insert into product (name, description, price, active, restaurant_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, false, 1);
insert into product (name, description, price, active, restaurant_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, true, 2);
insert into product (name, description, price, active, restaurant_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, true, 3);
insert into product (name, description, price, active, restaurant_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, true, 3);
insert into product (name, description, price, active, restaurant_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, true, 4);
insert into product (name, description, price, active, restaurant_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, true, 4);
insert into product (name, description, price, active, restaurant_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, true, 5);
insert into product (name, description, price, active, restaurant_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, true, 6);

