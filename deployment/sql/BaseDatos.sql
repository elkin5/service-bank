CREATE SCHEMA servicebank;

create sequence servicebank.person_sec
    maxvalue 2147483647;
create sequence servicebank.user_banking_seq
    maxvalue 2147483647;
create sequence servicebank.account_banking_seq
    maxvalue 2147483647;
create sequence servicebank.transaction_banking_seq
    maxvalue 2147483647;

CREATE TABLE if not EXISTS servicebank.person
(
    id bigint DEFAULT nextval('servicebank.person_sec'::regclass) NOT NULL,
    complete_name varchar(400) NOT NULL,
    gender varchar(1) NOT NULL,
    birth_date timestamp NOT NULL,
    identification bigint NOT NULL UNIQUE,
    address varchar(100),
    phone_number bigint NOT NULL,
    constraint person_id_pkey primary key (id)
);

CREATE TABLE if not EXISTS servicebank.user_banking
(
    id bigint DEFAULT nextval('servicebank.user_banking_seq'::regclass) NOT NULL,
    user_password varchar(50) NOT NULL,
    state bool NOT NULL,
    person_id bigint,
    constraint user_banking_pkey primary key (id),
    CONSTRAINT person_id_fk FOREIGN KEY (person_id) REFERENCES servicebank.person
);


CREATE TABLE if not EXISTS servicebank.account_banking
(
    id bigint DEFAULT nextval('servicebank.account_banking_seq'::regclass) NOT NULL,
    account_number bigint NOT NULL UNIQUE,
    account_type varchar(40) NOT NULL,
    balance numeric(22, 4)  NOT NULL,
    initial_value numeric(22, 4)  NOT NULL,
    state bool NOT NULL,
    person_id bigint,
    CONSTRAINT account_banking_pkey PRIMARY KEY (id),
    CONSTRAINT person_id_fk FOREIGN KEY (person_id) REFERENCES servicebank.person
);

CREATE TABLE if not EXISTS servicebank.transaction_banking
(
    id bigint DEFAULT nextval('servicebank.transaction_banking_seq'::regclass) NOT NULL,
    value bigint NOT NULL,
    initial_value numeric(22, 4) NOT NULL,
    ending_value numeric(22, 4)  NOT NULL,
    transaction_type varchar(40) NOT NULL,
    created_at timestamp NOT NULL,
    account_id bigint,
    CONSTRAINT transaction_banking_pkey PRIMARY KEY (id),
    CONSTRAINT account_id_fk FOREIGN KEY (account_id) REFERENCES servicebank.account_banking
);