# MyTalk
## 채팅방 게시판 토이 프로젝트
Springboot, Mybatis, MySQL, JSP

***
### SQL DDL

```sql
#drop database talk;
create database talk;
USE talk;

#DROP TABLE FILE;
#DROP TABLE chat;
#DROP TABLE user_chat_room;
#DROP TABLE USER;
#DROP TABLE chat_room;


CREATE TABLE USER(
user_no INT PRIMARY KEY AUTO_INCREMENT,
username varchar(30) UNIQUE,
password varchar(50) not null
);

CREATE TABLE chat_room(
chat_room_no INT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(30) NOT NULL,
DESCRIPTION VARCHAR(100),
created_at datetime DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_chat_room(
user_no INT NOT NULL,
chat_room_no INT NOT NULL,
FOREIGN KEY(user_no) references USER(user_no) ON DELETE CASCADE,
FOREIGN KEY(chat_room_no) references chat_room(chat_room_no) ON DELETE CASCADE
);

CREATE TABLE chat(
chat_no int PRIMARY KEY AUTO_INCREMENT,
user_no INT NOT NULL,
chat_room_no INT NOT NULL,
chat_text VARCHAR(500),
created_at datetime DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY(user_no) references user(user_no) ON DELETE CASCADE,
FOREIGN KEY(chat_room_no) references chat_room(chat_room_no) ON DELETE CASCADE
);

CREATE TABLE FILE(
file_no INT PRIMARY KEY AUTO_INCREMENT,
chat_no int not null,
original_name varchar(100),
saved_path text,
FOREIGN KEY(chat_no) REFERENCES chat(chat_no) ON DELETE CASCADE
);
```
