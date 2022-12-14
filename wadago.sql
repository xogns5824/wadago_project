DROP DATABASE WADAGO_PROJECT;
DROP user 'wadago_admin'@'localhost';

CREATE DATABASE WADAGO_PROJECT;
CREATE USER 'wadago_admin'@'localhost' IDENTIFIED WITH mysql_native_password BY '1234';

grant select, insert, update, delete on WADAGO_PROJECT.* to 'wadago_admin'@localhost;
USE WADAGO_PROJECT;

CREATE TABLE MEMBER(
id VARCHAR(255) NOT NULL,
pw VARCHAR(255) NOT NULL,
name VARCHAR(255) NOT NULL,
phone VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL,
birth DATE NOT NULL,
signup_time DATETIME DEFAULT CURRENT_TIMESTAMP(),
grade TINYINT NOT NULL DEFAULT 1 COMMENT '0 관리자 1 일반회원 2 탈퇴',
CONSTRAINT PRIMARY KEY(ID),
CONSTRAINT UNIQUE(PHONE),
CONSTRAINT UNIQUE(EMAIL)
);

INSERT INTO MEMBER
(ID,PW,NAME,PHONE,EMAIL,BIRTH,grade)
VALUES
('admin','1234','관리자','01012345678','admin@gmail.com','1997-08-13',0),
('user1','1234','유저1','01012345679','user1@gmail.com','1997-08-13',1),
('user2','1234','유저2','01012345677','user2@gmail.com','1997-08-13',1);


CREATE TABLE BOARD(
board_num INT NOT NULL AUTO_INCREMENT,
writer VARCHAR(255) NOT NULL,
contents VARCHAR(255) DEFAULT '',
post_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
PRIMARY KEY(board_num),
FOREIGN KEY(writer) REFERENCES MEMBER(id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO BOARD
(writer,contents)
VALUES
('user1','모델 정확도가 얼마나 되나요?'),
('user1','제가 입력한 사진이 왜 결과 목록에 없나요?');



CREATE TABLE REPLY(
comment_num INT NOT NULL AUTO_INCREMENT,
writer VARCHAR(255) NOT NULL,
contents VARCHAR(255) DEFAULT '',
board_num INT NOT NULL,
count INT default 0,
post_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
PRIMARY KEY(COMMENT_NUM),
FOREIGN KEY(writer) REFERENCES MEMBER(id) ON UPDATE CASCADE ON DELETE CASCADE, 
FOREIGN KEY(board_num) REFERENCES BOARD(board_num) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO REPLY
(writer, contents, board_num)
VALUES
('admin','99,7% 입니다!',1),
('admin','관리자가 승인을 해주면 보실 수 있습니다.',2);



CREATE TABLE IMG(
img_num INT NOT NULL AUTO_INCREMENT,
user VARCHAR(255) NOT NULL,
img_name VARCHAR(255),
post_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
description VARCHAR(255), 
grade TINYINT NOT NULL DEFAULT 0 COMMENT '0 대기 1 승인 2 거부',
PRIMARY KEY(img_num),
FOREIGN KEY(user) REFERENCES MEMBER(id)
);





