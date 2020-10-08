create table admin
(
    id     varchar(36) primary key,
    name   varchar(255) unique,
    secret varchar(255)
);

create table realm
(
    id              varchar(36) primary key,
    name            varchar(255) unique,
    status          varchar(10) default 'Active',
    currency_symbol varchar(3) not null
);

create table realm_attribute
(
    realm_id varchar(36) references realm (id),
    name     varchar(255),
    value    varchar(255),
    primary key (realm_id, name)
);
create index realm_attribute_realm_id_idx on realm_attribute (realm_id);


create table client
(
    id       varchar(36) primary key,
    realm_id varchar(36) references realm (id),
    name     varchar(255) not null,
    secret   varchar(255),
    unique (realm_id, name)
);
create index client_realm_id_idx on client (realm_id);
create index client_name_idx on client (name);

create table user_details
(
    id       varchar(36) primary key,
    realm_id varchar(36) references realm (id),
    status   varchar(10) default 'Active',
    name     varchar(255) not null,
    unique (realm_id, name)
);
create index user_details_realm_id_idx on user_details (realm_id);
create index user_details_name_idx on user_details (name);

create table user_attribute
(
    user_id varchar(36) references user_details (id),
    name    varchar(255),
    value   varchar(255),
    primary key (user_id, name)
);

create table group_details
(
    id       varchar(36) primary key,
    realm_id varchar(36) references realm (id),
    name     varchar(255) not null,
    status   varchar(10) default 'Active',
    unique (realm_id, name)
);

create table user_group
(
    user_id  varchar(36) references user_details (id),
    group_id varchar(36) references group_details (id),
    primary key (user_id, group_id)
);

create table coin
(
    id              varchar(36) primary key,
    realm_id        varchar(36) references realm (id),
    name            varchar(255)   not null,
    conversion_rate numeric(10, 2) not null,
    unique (realm_id, name)
);

create table user_coin
(
    user_id varchar(36) references user_details (id),
    coin_id varchar(36) references coin (id),
    balance int,
    primary key (user_id, coin_id)
);

create table coin_transaction
(
    id               varchar(36) primary key,
    realm_id         varchar(36) references realm (id),
    user_id          varchar(36) references user_details (id),
    coin_id          varchar(36) references coin (id),
    type             varchar(20) not null,
    coins_transacted int,
    creation_time    timestamp with time zone default now()
);
