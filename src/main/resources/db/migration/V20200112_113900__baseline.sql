create table bank
(
	id uuid constraint bank_pk primary key,
	name varchar(255) not null,
	swift_code varchar(11) not null
);

create table account_type
(
	id serial constraint account_type_pk primary key,
	type varchar(255) not null
);

create table currency
(
	id serial constraint currency_pk primary key,
	iso_code char(3)
);

create table customer
(
	id uuid constraint customer_pk primary key,
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	middle_name varchar(255),
	email varchar(255)
);

create table account
(
    id uuid constraint account_pk  primary key,
    bank_id uuid not null constraint account_bank_id_fk references bank,
    name varchar(255) not null,
    customer_id uuid not null constraint account_customer_id_fk references customer (id),
    current_balance numeric not null,
    currency_id int not null constraint account_currency_id_fk references currency,
    status int not null,
    type_id int not null constraint account_account_type_id_fk references account_type
);

create table transfer
(
	id uuid constraint transfer_pk primary key,
	from_account_id uuid not null constraint transfer_account_id_fk_1 references account,
	to_account_id uuid not null constraint transfer_account_id_fk_2 references account,
	amount numeric not null,
	details text not null,
	status int not null,
	failure_reason text
);


create table transaction
(
	id uuid constraint transaction_pk primary key,
	type int not null,
	account_id uuid not null constraint transaction_account_id_fk references account,
	amount numeric not null,
	transfer_id uuid not null constraint transaction_transfer_id_fk references transfer
);