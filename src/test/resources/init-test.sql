create table recruitment
(
    recruitment_id bigint auto_increment primary key,
    start_date     date not null,
    end_date       date not null
);

create table enrollee
(
    enrollee_id       bigint auto_increment primary key,
    first_name        varchar(30)                         not null,
    last_name         varchar(30)                         not null,
    address           varchar(50),
    birth_date        date                                not null,
    created           timestamp default CURRENT_TIMESTAMP not null,
    approved          boolean   default false             not null,
    in_recruitment_id bigint                              not null,
    constraint recruitment_rel
        foreign key (in_recruitment_id) references recruitment (recruitment_id)
);

create table chosen_specialty
(
    s_enrollee_id   bigint,
    specialty_uid varchar(56) not null,
    constraint enrollee_rel
        foreign key (s_enrollee_id) references enrollee (enrollee_id)
);
