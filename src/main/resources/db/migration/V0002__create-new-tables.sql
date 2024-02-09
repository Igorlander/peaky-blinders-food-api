create table ticket (
  id bigint not null auto_increment,
  amount decimal(10,2) not null,
  shipping_fee decimal(10,2) not null,
  total_price decimal(10,2) not null,

  restaurant_id bigint not null,
  user_client_id bigint not null,
  payment_method_id bigint not null,

  address_city_id bigint(20) not null,
  address_postal_code varchar(9) not null,
  address_public_place varchar(100) not null,
  address_number varchar(20) not null,
  address_complement varchar(60) null,
  address_neighborhood varchar(60) not null,

  status varchar(10) not null,
  creation_date datetime not null,
  confirm_date datetime null,
  cancellation_date datetime null,
  delivery_date datetime null,

  primary key (id),

  constraint fk_ticket_restaurant foreign key (restaurant_id) references restaurant (id),
  constraint fk_ticket_tb_user foreign key (user_client_id) references tb_user (id),
  constraint fk_ticket_payment_method foreign key (payment_method_id) references payment_method (id)
) engine=InnoDB default charset=utf8;


create table item_order (
  id bigint not null auto_increment,
  amount smallint(6) not null,
  unit_price decimal(10,2) not null,
  total_price decimal(10,2) not null,
  observation varchar(255) null,
  ticket_id bigint not null,
  product_id bigint not null,

  primary key (id),
  unique key uk_item_fk_item_order_product (ticket_id, product_id),

  constraint fk_item_order_ticket foreign key (ticket_id) references ticket (id),
  constraint fk_item_order_product foreign key (product_id) references product (id)
) engine=InnoDB default charset=utf8;