insert into brokerLevels (id,description) values (1,"Broker fist Level")
insert into brokerLevels (id,description) values (2,"Broker second Level")
insert into brokerLevels (id,description) values (3,"Broker third Level")

insert into users (login,password) values ("user1",md5("1234"));
insert into users (login,password) values ("user2",md5("1111"));

insert into brokers (firstname,lastname,birthDay,sex,level_id,user_id) values ("Recorb num 1", "Broker num 1", "2000,12,01",1,1,1);
insert into brokers (firstname,lastname,birthDay,sex,level_id,user_id) values ("RecorbW num 2", "BrokerW num 2", "2001,01,01",0,3,2);

insert into stocks (name_,code) values ("Microsoft","MSFT");
insert into stocks (name_,code) values ("Apple"," AAPL");

delete from sales where id > 2

insert into sales (broker_id,stock_id,price,amount,date_) values (3,2,111.78,10,"2015-11-20");
insert into sales (broker_id,stock_id,price,amount,date_) values (4,1,53.58,5,"2015-11-20");
insert into sales (broker_id,stock_id,price,amount,date_) values (4,1,53.50,5,"2015-11-22");
insert into sales (broker_id,stock_id,price,amount,date_) values (4,2,100.00,10,"2015-11-24");
insert into sales (broker_id,stock_id,price,amount,date_) values (3,1,50.00,5,"2015-11-24");
insert into sales (broker_id,stock_id,price,amount,date_) values (3,1,50.00,5,"2015-11-24");

insert into sales (broker_id,stock_id,price,amount,date_) values (3,1,50.00,5,"2015-11-24 16:30:20");

select stocks.id, stocks.name_, stocks.code from stocks

select * from users

select * from stocks

select users.id from users where users.login = "user1" and users.password = md5("1234");

select brokerLevels.id,brokerLevels.description, brokers.id, brokers.firstname, brokers.lastname, brokers.birthDay, brokers.sex, brokers.level_id, brokers.user_id from brokers inner join brokerLevels on brokers.level_id = brokerLevels.id where brokers.user_id = 1;