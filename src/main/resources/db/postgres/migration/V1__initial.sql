create table strategy
(
    id   uuid not null,
    primary key (id),

    name varchar(255)
);

insert into strategy (id, name) values (gen_random_uuid(), 'Strategy 1');
insert into strategy (id, name) values (gen_random_uuid(), 'Strategy 2');
insert into strategy (id, name) values (gen_random_uuid(), 'Strategy 3');
insert into strategy (id, name) values (gen_random_uuid(), 'Strategy 4');
insert into strategy (id, name) values (gen_random_uuid(), 'Strategy 5');