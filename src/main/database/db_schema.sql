-- deleting existing tables
drop table if exists scooter cascade;
drop table if exists model cascade;	
drop table if exists scooterRacks cascade;
drop table if exists customer cascade;
drop table if exists PaymentMethod cascade;
drop table if exists Association cascade;
drop table if exists PaymentWithoutSubscription cascade;
drop table if exists PaymentWithSubscription cascade;
drop table if exists UsedSubscription cascade;
drop table if exists RealSubscription cascade;
drop table if exists Admin cascade;
--drop table if exists EndSubscription cascade;
drop table if exists Subscription cascade;
drop table if exists Rental cascade;
--drop domains and sequences
drop domain if exists positive_integer cascade;
drop domain if exists positive_real cascade;
drop domain if exists gender cascade;
drop domain if exists paymentTypes cascade;
drop domain if exists activationType cascade;
drop sequence if exists used_subscription_id_a_seq cascade;
drop sequence if exists real_subscription_id_a_seq cascade;
drop sequence if exists realsubscription_id_a_seq cascade;
drop sequence if exists rental_order_number_seq cascade;
drop sequence if exists sequence_id_sa cascade;
drop sequence if exists sequence_id_ca cascade;

drop schema if exists public cascade;
create schema public;

--creating sequences: serials personalized
create sequence if not exists usedsubscription_id_a_seq as int increment by 1
    MINVALUE 1 NO MAXVALUE NO CYCLE;	
create sequence if not exists rental_order_number_seq as int increment by 1
    MINVALUE 1 NO MAXVALUE NO CYCLE;
create sequence if not exists sequence_id_sa as int increment by 2
    MINVALUE 1 NO MAXVALUE NO CYCLE;
create sequence  if not exists sequence_id_ca as int increment by 2
    MINVALUE 2 NO MAXVALUE NO CYCLE;	

--creating domains
create domain positive_integer as integer
default 0 check (value >=0);

create domain positive_real as real
default 0 check (value >=0);

CREATE DOMAIN gender CHAR(1)
    CHECK (value IN ( 'F' , 'M' ) );

CREATE DOMAIN paymentTypes varchar
    CHECK (value IN ( 'Credit Card', 'Visa Debit', 'Paypal' ) );

CREATE domain activationType varchar
    check ( value IN ('Active', 'Inactive'));


-- creating tables
create table ScooterRacks(
    ID serial,
    total_parking_spots positive_integer not null,
    available_parking_spots positive_integer not null,
    --latitude numeric(20,18) not null,
    --longitude numeric(21,18) not null,
    postalCode char(5) not null,
    address varchar(50),
    constraint key_scooter_rack primary key (ID)
    --constraint unique_coordinate unique (latitude, longitude) --corporate constraints
);

create table Model(
    name varchar(30), 
    brand varchar(30) not null default 'Unknown',
    battery_life time without time zone,
    --weight positive_real default null,
    --height positive_real default null, --in cm
    --length positive_real default null,
    --depth positive_real default null,
    price_per_min numeric(4,3) default '0.10' not null,
    --rate_per_model numeric(4,2) default '1.00' not null,
    constraint key_model primary key (name),
    constraint check_model_price check (price_per_min > 0.0) --corporate constraints
);

create table PaymentMethod(
    ID serial,
    type paymentTypes not null,
    Activation activationType default 'Active',
    constraint key_payment_method primary key (type)
);

create table Customer(
    CF char(16), 
    name varchar(30) not null, 
    surname varchar(30) not null, 
    email varchar(60) unique not null,
    sex gender not null,
    birthdate date not null default '1922-02-02',
    postalCode char(5),
    paymentType paymentTypes,
    constraint key_customer primary key (CF),
    constraint fk_paymentmethod foreign key (paymentType) references PaymentMethod
        on update cascade
        on delete cascade
);



create table Subscription(
    ID serial,
    --type varchar(20), -- it is the typology
    type varchar(60) default '1d' not null,
    daily_unlocks positive_integer default 2 not null,
    validity_per_day time without time zone,
    fixed_price numeric(5,2) default '5.00' not null,
    constraint key_subscription primary key (ID),
    constraint check_subscription_price check(fixed_price > 0) --corporate constraints
);

create table Scooter(
    ID serial,
    date_of_purchase date default current_timestamp, 
    km_traveled positive_real, 
    model varchar(30) not null,--foreign key
    remaining_battery_life decimal(5,2) not null default '100.00',
    id_scooter_rack int not null, --foreign key /*can be null only if the scooter is currently rented*/
    constraint key_scooter primary key (id),
    constraint fk_scooter_scooterrack foreign key (id_scooter_rack) references ScooterRacks
    on update cascade
    on delete restrict, 
    constraint fk_scooter_model foreign key (model) references model 
    on update cascade
    on delete restrict,
    constraint check_scooter_purchase check(date_of_purchase <= current_timestamp) --corporate constraints
);
--need to deleted--
/*create table Association(
    type varchar(20),
    CF char(16),
    constraint key_association primary key (CF, type),
    constraint fk_association_customer foreign key (CF) references Customer
    on update cascade 
    on delete cascade,  
    constraint fk_association_paymentmethod foreign key (type) references PaymentMethod 
    on update cascade 
    on delete cascade 
);*/

create table Rental(
    ID int default nextval('rental_order_number_seq'),
    date_hour_delivery timestamp default null, 
    date_hour_collection timestamp default current_timestamp not null, 
    id_scooter int not null, --foreign
    scooterrack_delivery int default null, --foreign
    scooterrack_collection int not null, --foreign
    customer char(16) not null, --foreign
    km_traveled positive_real default 0,
    constraint key_rental primary key (ID),
    constraint fk_rental_scooter foreign key (id_scooter) references Scooter
    on update cascade 
    on delete restrict,
    constraint fk_rental_scooterrack_collection foreign key (scooterrack_collection) references ScooterRacks 
    on update cascade,
    --on delete the old value is ok,
    constraint fk_rental_scooterrack_delivery foreign key (scooterrack_delivery) references ScooterRacks
    on update cascade,
    --on delete the old value is ok,
    constraint fk_rental_customer foreign key (customer) references customer
    on update cascade 
    on delete restrict,

    constraint check_collection_delivery check(date_hour_delivery > date_hour_collection), --corporate constraints
    constraint check_collection_scooter unique (date_hour_collection, id_scooter),
    constraint check_collection_customer unique (date_hour_collection, customer)
);

create table UsedSubscription(
    ID int default nextval('usedsubscription_id_a_seq'),
    activation_date date default current_timestamp not null, 
    expiration_date date not null,
    remaining_unlocks positive_integer default '2' not null,
    remaining_time_of_usage interval hour to second default '2 hour' not null, 
    customer_CF char(16) not null, -- id fiscale foreign
    subscription_type int not null, --foreign
    constraint key_used_subscription primary key (ID),
    constraint fk_used_subscription foreign key (subscription_type) references Subscription
    on update restrict
    on delete restrict,
    constraint fk_usedsubscription_customer foreign key (customer_CF) references Customer
    on update cascade 
    on delete cascade --corporate constraints (if a customer unsubscribes he loses his subscriptions)
);

create table PaymentWithoutSubscription(
    ID int default nextval('sequence_id_sa') ,
    --int generated by default as identity (start with 1 increment by 2), another chance in addition to sequence
    price numeric(5,2) not null, --computed at the checkout --trigger
    date_hour timestamp default current_timestamp not null,
    order_ID integer unique not null,
    constraint key_without_subscription primary key (ID),
    constraint fk_withoutsubscription_rental foreign key (order_ID) references rental
    on update cascade 
    on delete restrict,
    constraint check_withoutpayment_price check(price > 0) --corporate constraints
);

create table PaymentWithSubscription(
    ID int default nextval('sequence_id_ca') ,
    --generated by default as identity (start with 2 increment by 2),
    date_hour timestamp default current_timestamp not null, 
    order_ID integer unique not null,
    usedSubscription_ID integer not null,
    constraint key_with_subscription primary key (ID),
    constraint fk_withsubscription_rental foreign key (order_ID) references rental
    on update cascade 
    on delete restrict,
    constraint fk_withsubscription_usedsubscription foreign key (usedSubscription_ID) references UsedSubscription
    on update cascade 
    on delete restrict
);

create table Admin(
    ID serial,
    email varchar,
    password varchar,
    name varchar,
    photo BYTEA default null,
	photoMediaType TEXT,
    constraint key_admin primary key (ID),
    CONSTRAINT proper_email CHECK (email ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$')
);
