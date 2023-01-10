create table dishes(
    id serial primary key,
    name VARCHAR(50) NOT NULL unique,
    cooking_time bigint,
    available boolean default true
);