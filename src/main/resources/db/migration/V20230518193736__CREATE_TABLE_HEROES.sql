create table if not exists heroes (
    id bigserial not null,
    email varchar(255) not null ,
    name varchar(45) not null ,
    power_id integer not null ,
    primary key (id)
);
alter table heroes
    add constraint FK_heroes_power_id
        foreign key (power_id)
            references hero_power
