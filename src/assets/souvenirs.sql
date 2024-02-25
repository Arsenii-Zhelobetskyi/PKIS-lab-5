create table souvenirs.manufacturers
(
    id          varchar(100) not null
        primary key,
    name        varchar(100) not null,
    country     varchar(100) not null,
    description varchar(255) null,
    email       varchar(255) null,
    website     varchar(255) null
);

create table souvenirs.souvenirs
(
    id                     varchar(100) not null
        primary key,
    name                   varchar(100) not null,
    manufacturer_s_details varchar(100) null,
    date                   date         null,
    price                  double       not null,
    constraint souvenirs_manufacturers_id_fk
        foreign key (manufacturer_s_details) references souvenirs.manufacturers (id)
            on delete cascade
);

