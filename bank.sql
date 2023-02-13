create database bank_app_db;
use bank_app_db;
create table accounts
(
 accId bigint primary key,
 accName varchar(50) not null,
 accUsername varchar(30) not null unique,
 accPassword varchar(30) not null,
 accBalance double not null default 0.0,
 accType varchar(20) not null,
 dob date not null,
 gender varchar(10) not null,
 address varchar(40) not null,
 email varchar(40) not null unique,
 phone bigint not null unique,
 branchName varchar(20) not null,
 ifscCode varchar(10) not null
);

describe accounts;
select * from accounts;
drop table admin;
create table admin
(
 id int primary key,
 username varchar(20) not null unique,
 password varchar(20) not null,
 email varchar(30) not null unique
);

describe admin;
create table transactions
(
  transactionId bigint primary key,
  transactionDate date not null,
  transactionAmount double not null, 
  transactionType varchar(10) not null,
  senderAccountId bigint not null,
  receiverAccountId bigint not null
);


