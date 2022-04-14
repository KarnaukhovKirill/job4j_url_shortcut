create table shortcuts (
    id serial primary key,
    code varchar(255) unique not null,
    url varchar(255) unique not null,
    counter bigint
);

create table site (
    id serial primary key,
    login varchar(255) unique not null,
    password varchar(255) not null,
    url varchar(255) not null unique
);

create table site_shortcuts (
    site_id bigint references site(id),
    shortcuts_id bigint references shortcuts(id)
);
