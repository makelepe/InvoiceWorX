drop table transaction_status;
drop table transaction_detail;
drop table transaction_status_type;
drop table ix_commission_account;
drop table ix_pooler_account;
drop table doc_file;
drop table invoice_status;
drop table invoice_item;
drop table invoice;
drop table invoice_status_type;
drop table otp;
drop table password_history;
drop table login;
drop table _user;
drop table user_type;
drop table org;
drop table person;
drop table address;
drop table contact;
drop table account;


create table contact (
id bigint primary key,
cellphone varchar(30),
homephone varchar(30),
workphone varchar(30),
email varchar(100),
website varchar(100)
);
create table address (
id bigint primary key,
line1 varchar(100),
surburb varchar(50),
city varchar(50),
province varchar(30),
postal_code varchar(4)
);
create table person (
id bigint primary key,
fname varchar(50),
mname varchar(50),
lname varchar(50),
gender varchar(1),
race varchar(30),
id_number varchar(13),
passport varchar(30),

contact_id bigint REFERENCES contact (id),
address_id bigint REFERENCES address (id)
);

create table org (
id bigint primary key,
org_name varchar(100),
org_reg_num varchar(100),
org_reg_date timestamp,

contact_id bigint REFERENCES contact (id),
address_id bigint REFERENCES address (id)
);

create table account (
id bigint primary key,
account_holder varchar(50),
account_type varchar(50),
account_number varchar(50),
bank varchar(50),
branch varchar(50),
branch_code varchar(50)
);
create table user_type (
id integer primary key,
utype varchar(50)
);
insert into user_type (id, utype) values(1, 'ADMIN'); 
insert into user_type (id, utype) values(2, 'NORMAL');
insert into user_type (id, utype) values(3, 'FINANCE');
insert into user_type (id, utype) values(4, 'SUPPLIER');
insert into user_type (id, utype) values(5, 'CORPORATE');
insert into user_type (id, utype) values(6, 'FUNDER');

create table _user(
id bigint primary key,
password varchar(50),
created_ts timestamp,
expiry_ts timestamp,
last_password_changed_ts timestamp,
active boolean,
deactivation_reason text,

user_type_id integer REFERENCES user_type (id),
org_id bigint REFERENCES org (id),
account_id bigint REFERENCES account (id),
person_id bigint REFERENCES person (id)
);

create table login (
id bigint primary key,
login_ts timestamp,
user_id bigint REFERENCES _user (id),
session_id varchar(100)
);
create table password_history (
id bigint primary key,
user_id bigint REFERENCES _user (id),
changed_ts timestamp,
password varchar(100)
);
create table otp (
id bigint primary key,
user_id bigint REFERENCES _user (id),
otp_value varchar(100),
created_ts timestamp,
confirmed boolean,
expired boolean
);

create table invoice_status_type (
id integer primary key,
invtype varchar(200)
);

insert into invoice_status_type (id, invtype) values(1, 'NEW');
insert into invoice_status_type (id, invtype) values(2, 'APPROVED');
insert into invoice_status_type (id, invtype) values(3, 'PENDING_VERIFICATION');
insert into invoice_status_type (id, invtype) values(4, 'ON_SALE');
insert into invoice_status_type (id, invtype) values(5, 'SOLD');
insert into invoice_status_type (id, invtype) values(6, 'SETTLED');
insert into invoice_status_type (id, invtype) values(7, 'AWAITING_PAYMENT');
insert into invoice_status_type (id, invtype) values(8, 'ERROR');

create table invoice (
id bigint primary key,
inv_ref_number varchar(100),
inv_status varchar(50),
inv_amount numeric(19,2),
inv_vat_amount numeric(19,2),
commission numeric(19,2),
funded_amount numeric(19,2),
funder_commission numeric(19,2),
created_by bigint REFERENCES _user (id),
created_ts timestamp,
approved_by bigint REFERENCES _user (id),
approved_ts timestamp,
funded_by bigint REFERENCES _user (id),
funded_ts timestamp,
current_status_id integer REFERENCES invoice_status_type (id)
);
create table invoice_item (
id bigint primary key,
description varchar(200),
unit_price numeric(19,2),
quantity integer,
vat_amount numeric(19,2),
invoice_id bigint REFERENCES invoice (id)
);
create table invoice_status (
id bigint primary key,
created_ts timestamp,
created_by bigint,
status_type_id integer REFERENCES invoice_status_type (id),
status_comment text,
invoice_id bigint REFERENCES invoice (id)
);
create table doc_file (
id integer primary key,
uploaded_ts timestamp,
purchase_order bytea,
delivery_note bytea,
invoice_id integer REFERENCES invoice (id)
);

create table ix_pooler_account (
id integer primary key,
last_updated timestamp,
balance numeric(19,2)
);
create table ix_commission_account (
id integer primary key,
last_updated timestamp,
balance numeric(19,2)
); 

create table transaction_status_type (
id integer primary key,
trantype varchar(200)
);
insert into transaction_status_type (id, trantype) values(1, 'FUNDER_DEBITTED'); --on buy action
insert into transaction_status_type (id, trantype) values(2, 'SUPPLIER_PAID'); -- on buy action
insert into transaction_status_type (id, trantype) values(3, 'CORPORATE_DEBITTED'); -- scheduler
insert into transaction_status_type (id, trantype) values(4, 'FUNDER_PAID'); -- scheduler
insert into transaction_status_type (id, trantype) values(5, 'IX_COMMISSION_PAID');-- scheduler
insert into transaction_status_type (id, trantype) values(6, 'REMAINDER_PAID_TO_SUPPLIER');-- scheduler
insert into transaction_status_type (id, trantype) values(7, 'TRANSACTION_FAILURE');
insert into transaction_status_type (id, trantype) values(8, 'TRANSACTION_TIMED_OUT');
insert into transaction_status_type (id, trantype) values(9, 'BANK_CHARGES');

create table transaction_detail (
id bigint primary key,
tran_ref_number varchar(100),
created_ts timestamp,
tran_amount numeric(19,2),
tran_vat_amount numeric(19,2),
current_status varchar(100),

status_type_id integer REFERENCES transaction_status_type (id),
ix_pooler_acc_id bigint REFERENCES ix_pooler_account (id),
ix_comm_acc_id bigint REFERENCES ix_commission_account (id),

invoice_id bigint REFERENCES invoice (id),
user_id bigint REFERENCES _user (id)
);

create table transaction_status (
id bigint primary key,
created_ts timestamp,
created_by bigint,
status_type_id integer REFERENCES transaction_status_type (id),
status_comment text,
transaction_id bigint REFERENCES transaction_detail (id)
);


