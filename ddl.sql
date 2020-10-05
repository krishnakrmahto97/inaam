create database inaam;

---------------------------------------------------
--  Run after switching to inaam database
---------------------------------------------------


create table admin
(
    id     uuid primary key,
    name   text,
    secret text
);

create table realm
(
    id              uuid primary key,
    name            text,
    currency_symbol text
);

create table realm_attribute
(
    realm_id uuid references realm (id),
    name     text,
    value    text,
    primary key (realm_id, name)
);
create index on realm_attribute (realm_id);


create table client
(
    id       uuid primary key,
    realm_id uuid references realm (id),
    name     text,
    secret   text,
    unique (realm_id, name)
);
create index on client (realm_id);
create index on client (name);

create table user_details
(
    id       uuid primary key,
    realm_id uuid references realm (id),
    name     text
);
create index on user_details (realm_id);
create index on user_details (name);

create table user_attribute
(
    user_id uuid references user_details (id),
    name    text,
    value   text,
    primary key (user_id, name)
);

create table group_details
(
    id       uuid unique,
    realm_id uuid references realm (id),
    name     text not null,
    primary key (id)
);

create table user_group
(
    user_id  uuid references user_details (id),
    group_id uuid references group_details (id),
    primary key (user_id, group_id)
);

create table coin
(
    id              uuid,
    realm_id uuid references realm (id),
    name            text not null,
    conversion_rate numeric(10, 2),
    primary key (realm_id, id),
    unique (realm_id, name)
);

create table user_coin
(
    user_id uuid references user_details (id),
    coin_id uuid references user_details (id),
    balance numeric,
    primary key (user_id, coin_id)
);