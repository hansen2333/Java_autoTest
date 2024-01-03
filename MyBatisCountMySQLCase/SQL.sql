create database mybatis_mysql_case;

use mybatis_mysql_case;

create table user(
     id int auto_increment primary key ,
     userName varchar(255) not null ,
     password varchar(255) not null ,
     age int not null ,
     sex char(1) not null,
     permission char(1) not null,
     isDelete char(1) not null
);

insert into user(userName,password,age,sex,permission,isDelete) values
                                                                    ('zhangsan','123456',20,0,0,0),
                                                                    ('lisi','123456',25,1,1,0),
                                                                    ('wangwu','123456',30,0,1,0),
                                                                    ('zhaoliu','123456',40,1,1,0),
                                                                    ('zhang1','123',20,0,0,0),
                                                                    ('zhao2','wqer',20,1,1,0),
                                                                    ('li3','sdffa',30,1,0,0),
                                                                    ('wu5',123,90,1,0,0);

create table addUserCase(
                            id int auto_increment primary key ,
                            userName varchar(255) not null ,
                            password varchar(255) not null ,
                            sex char(1) not null,
                            age int not null ,
                            permission char(1) not null,
                            isDelete char(1) not null,
                            expected enum('true','false') not null
);

insert into addusercase(userName, password, sex, age, permission, isDelete, expected) VALUES
                                                                                              ('zhao9','zhaozhao',0,35,1,0,true);

create table getUserInfoCase(
                                id int auto_increment primary key ,
                                userId int ,
                                expected varchar(255),
                                foreign key (userId) references user(id)
);

insert into getUserInfoCase(id, userId, expected) VALUES (1,1,'getUserInfo');

create table getUserListCase(
  id int auto_increment primary key ,
  userName varchar(255),
  age char(1),
  sex char(1),
  expected varchar(255)
);

insert into getuserlistcase(id, userName, age, sex, expected) values (1,null,null,0,'getUserList');

create table loginCase(
  id int auto_increment primary key ,
  userName varchar(255),
  password varchar(255),
  expected enum('true','false') not null
);

insert into loginCase(id, userName, password, expected) VALUES (1,'zhangsan','123456','true'),(2,'zhangsanerror','123','false');

create table updateUserInfoCase(
  id int auto_increment primary key ,
  userId  int,
  userName varchar(255),
  sex char(1),
  age char(1),
  permission char(1),
  isDelete char(1),
  expected varchar(255),
  foreign key (userId) references user(id)
);

insert into updateuserinfocase(id, userId, userName, sex, age, permission, isDelete, expected) VALUES
                                                                                                   (1,2,'hahahaha',null,null,null,null,'getUpdateUserInfo'),
                                                                                                   (2,8,null,null,null,null,1,'getUpdateUserInfo');

describe getUserListCase;

select * from updateUserInfoCase;

#drop table logincase;