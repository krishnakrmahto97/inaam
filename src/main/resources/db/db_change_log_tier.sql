alter table coin rename column conversion_rate to monetary_value_per_coin;
alter table coin add column monetary_amount_for_one_coin int not null default 1;

alter table coin add column creation_time timestamp not null default now();
alter table coin add column last_modified_time timestamp not null default now();
