
create table brokerLevels
(
id int not null primary key,
description varchar(500) not null
)

create table users
(
id int not null primary key AUTO_INCREMENT,
login varchar (50) unique,
password char(32) 
)

create table brokers
(
id int not null primary key AUTO_INCREMENT,
firstname varchar(100) not null,
lastname varchar(100) not null,
birthDay date,
sex bit,
level_id int,
user_id int,

foreign key (level_id) references brokerLevels(id),
foreign key (user_id) references users(id)
)


create table stocks
(
id int not null primary key AUTO_INCREMENT,
name_ varchar(10) not null,
code varchar(100) not null
)


create table sales
(
id int not null primary key AUTO_INCREMENT,
broker_id int,
stock_id int,

foreign key (broker_id) references brokers(id),
foreign key (stock_id) references stocks(id),

price decimal,
amount int,

date_ datetime
)

drop table sales

select 
brokerLevels.id,
brokerLevels.description,

brokers.id,
brokers.firstname,
brokers.lastname,
brokers.birthDay,
brokers.sex,

stocks.id,
stocks.name_,
stocks.code,


sales.id,
sales.price,
sales.amount 

from sales
inner join brokers on sales.broker_id = brokers.id
inner join stocks on sales.stock_id = stocks.id
inner join brokerLevels on brokers.level_id = brokerLevels.id
where stocks.id = 1 and sales.date_ = "2015-11-20"

select * from sales

insert into sales (broker_id,stock_id,price,amount,date_) values (3,1,50.00,5,"2015-11-24 16:30:20");

delete from sales where id > 2