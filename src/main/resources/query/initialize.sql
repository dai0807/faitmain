-- init sql

DROP TABLE IF EXISTS ban_period CASCADE;
DROP TABLE IF EXISTS cart CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS image CASCADE;
DROP TABLE IF EXISTS inquiry CASCADE;
DROP TABLE IF EXISTS live_chat CASCADE;
DROP TABLE IF EXISTS live_product CASCADE;
DROP TABLE IF EXISTS live_reservation CASCADE;
DROP TABLE IF EXISTS live_user_status CASCADE;
DROP TABLE IF EXISTS live CASCADE;
DROP TABLE IF EXISTS review CASCADE;
DROP TABLE IF EXISTS `order` CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS store_application_document CASCADE;
DROP TABLE IF EXISTS user CASCADE;



-- create sql

CREATE TABLE `user` (
                    `user_number`             INTEGER      NOT NULL AUTO_INCREMENT ,
                    `id`                      VARCHAR(25)  NOT NULL ,
                    `password`                VARCHAR(15)  NOT NULL ,
                    `gender`                  CHAR(4)      NULL ,
                    `address`                 VARCHAR(80)  NOT NULL ,
                    `postal_code`             INT(5)       NULL ,
                    `nickname`                VARCHAR(20)  NULL ,
                    `phone_number`            VARCHAR(12)  NOT NULL ,
                    `name`                    VARCHAR(10)  NOT NULL ,
                    `reg_date`                DATE         NULL ,
                    `join_path`               VARCHAR(5)   NOT NULL DEFAULT 'HOME' ,
                    `book_number`             INT(5)       NULL ,
                    `total_point`             INT          NULL ,
                    `store_logo_image`        VARCHAR(30)  NULL ,
                    `store_introduction`      VARCHAR(200) NULL ,
                    `bank_account_copy_image` VARCHAR(30)  NULL ,
                    `bank_name`               VARCHAR(15)  NULL ,
                    `bank_account_number`     VARCHAR(15)  NULL ,
                    `role`                    VARCHAR(6)   NOT NULL DEFAULT 'user' ,
                    `store_name`              VARCHAR(20)  NULL ,
                    `withdrawal_status`       TINYINT               DEFAULT 0 ,
                    PRIMARY KEY ( `user_number` ) ,
                    UNIQUE INDEX `id_UNIQUE` ( `id` ASC ) VISIBLE ,
                    UNIQUE INDEX `phone_number_UNIQUE` ( `phone_number` ASC ) VISIBLE ,
                    UNIQUE INDEX `nickname_UNIQUE` ( `nickname` ASC ) VISIBLE ,
                    UNIQUE INDEX `store_name_UNIQUE` ( `store_name` ASC ) VISIBLE
                    );

CREATE TABLE `product` (
                       `product_number`        INT(5)      NOT NULL AUTO_INCREMENT ,
                       `product_name`          VARCHAR(50) NOT NULL ,
                       `product_price`                 INT         NOT NULL ,
                       `store_id`              VARCHAR(25) NOT NULL ,
                       `product_main_image`    VARCHAR(30) NOT NULL ,
                       `product_detail`        LONGTEXT    NULL ,
                       `product_category_code` CHAR(2)     NOT NULL ,
                       `product_status`        CHAR(2) DEFAULT '01' ,
                       `reg_date`              DATETIME    NOT NULL ,
                       `delivery_charge`       INT         NOT NULL ,
                       `product_quantity`      INT         NOT NULL ,
                       `product_group_number`  INT(5)      NULL ,
                       PRIMARY KEY ( `product_number` ) ,
                       FOREIGN KEY ( `store_id` ) REFERENCES user ( id )
                       );

CREATE TABLE `order` (
                     order_number              INTEGER      NOT NULL AUTO_INCREMENT ,
                     product_number            INTEGER      NOT NULL ,
                     buyer_id                  VARCHAR(25)  NOT NULL ,
                     store_id                  VARCHAR(25)  NULL ,
                     order_bundle_number       TIMESTAMP    NOT NULL ,
                     order_date                DATETIME     NOT NULL ,
                     order_quantity            INTEGER      NOT NULL ,
                     order_status              VARCHAR(25)  NOT NULL ,
                     receiver_name             VARCHAR(25)  NOT NULL ,
                     receiver_phone            VARCHAR(25)  NOT NULL ,
                     receiver_address          VARCHAR(25)  NOT NULL ,
                     receiver_request          VARCHAR(25)  NULL ,
                     delivery_tracking_number  INTEGER      NULL ,
                     delivery_company_code     VARCHAR(25)  NULL ,
                     payment_option            VARCHAR(25)  NOT NULL ,
                     total_payment_price       INTEGER      NULL ,
                     reward_point              INTEGER      NULL ,
                     using_point               INTEGER      NULL ,
                     order_claim_request_date  DATE         NULL ,
                     order_claim_response_date DATE         NULL ,
                     order_claim_reason        VARCHAR(100) NULL ,

                     PRIMARY KEY ( order_number ) ,
                     FOREIGN KEY ( product_number ) REFERENCES product ( product_number ) ,
                     FOREIGN KEY ( buyer_id ) REFERENCES user ( id ) ,
                     FOREIGN KEY ( store_id ) REFERENCES user ( id )
                     );


CREATE TABLE `store_application_document` (
                                          `store_application_document_number` INT(5)        NOT NULL AUTO_INCREMENT ,
                                          `id`                                VARCHAR(25)   NOT NULL ,
                                          `examination_status`                VARCHAR(4)    NOT NULL ,
                                          `product_detail`                    VARCHAR(1000) NOT NULL ,
                                          `reg_date`                          DATE          NOT NULL ,
                                          PRIMARY KEY ( `store_application_document_number` ) ,
                                          FOREIGN KEY ( id ) REFERENCES user ( id )
                                          );

CREATE TABLE `review` (
                      `review_number`  INT(5)       NOT NULL AUTO_INCREMENT ,
                      `order_number`   INT(5)       NOT NULL ,
                      `review_content` VARCHAR(200) NOT NULL ,
                      `review_image`   VARCHAR(30)  NULL ,
                      `rating`         INT          NOT NULL ,
                      `reg_date`       DATE         NULL ,
                      `user_id`        VARCHAR(25)  NOT NULL ,
                      PRIMARY KEY ( `review_number` ) ,
                      FOREIGN KEY ( `order_number` ) REFERENCES `order` ( order_number ) ,
                      FOREIGN KEY ( user_id ) REFERENCES user ( id )
                      );

CREATE TABLE `cart` (
                    `cart_number`        INT(5)      NOT NULL AUTO_INCREMENT ,
                    `user_id`            VARCHAR(25) NOT NULL ,
                    `cart_quantity`      INT         NOT NULL ,
                    `product_number`     INT(5)      NOT NULL ,
                    `product_name`       VARCHAR(50) NOT NULL ,
                    `product_main_image` VARCHAR(30) NOT NULL ,
                    `product_price`      INT         NOT NULL ,
                    PRIMARY KEY ( `cart_number` ) ,
                    FOREIGN KEY ( user_id ) REFERENCES user ( id ) ,
                    FOREIGN KEY ( product_number ) REFERENCES product ( product_number )
                    );

CREATE TABLE `image` (
                     `image_number`                INT(5)      NOT NULL AUTO_INCREMENT ,
                     `image_classification_code`   CHAR(1)     NOT NULL ,
                     `image_classification_number` INT(5)      NOT NULL ,
                     `image_name`                  VARCHAR(30) NOT NULL ,
                     PRIMARY KEY ( `image_number` )
                     );

CREATE TABLE `customer` (
                        `customer_board_number`      int           NOT NULL AUTO_INCREMENT ,
                        `customer_board_title`       varchar(30)   NOT NULL ,
                        `customer_board_content`     varchar(1000) NOT NULL ,
                        `reg_date`                   date          NOT NULL ,
                        `update_date`                date DEFAULT NULL ,
                        `customer_FAQ_category_code` 	int    		NOT NULL,      
                        `customer_board_type`        char(1)       NOT NULL ,
                        `customer_id`                varchar(25)   NOT NULL ,
                        PRIMARY KEY ( `customer_board_number` ) ,
                        FOREIGN KEY ( `customer_id` ) REFERENCES `user` ( `id` )
                        );



CREATE TABLE `ban_period` (
                          `report_number`         int         NOT NULL ,
                          `respondent_id`         varchar(25) NOT NULL ,
                          `respondent_nickname`   varchar(20) NOT NULL,
                          `respondent_store_name` varchar(20) NOT NULL,
                          `status_number`         int DEFAULT NULL ,
                          `ban_period_number`     int DEFAULT NULL ,
                          `ban_end_date`          date        NOT NULL ,
                          FOREIGN KEY ( `report_number` ) REFERENCES `customer` ( `customer_board_number` ) ,
                          FOREIGN KEY ( `respondent_id` ) REFERENCES `user` ( `id` )
                          );

CREATE TABLE `inquiry` (
                       `inquiry_number`        INT(5)       NOT NULL AUTO_INCREMENT ,
                       `inquiry_title`         VARCHAR(40)  NOT NULL ,
                       `inquiry_content`       VARCHAR(600) NOT NULL ,
                       `inquiry_date`          DATE         NOT NULL ,
                       `user_id`               VARCHAR(25)  NOT NULL ,
                       `store_id`              VARCHAR(25)  NOT NULL ,
                       `product_number`        INT(5)       NOT NULL ,
                       `inquiry_reply_status`  TINYINT DEFAULT 0 ,
                       `inquiry_reply_content` VARCHAR(600) NULL ,
                       `inquiry_reply_date`    DATE         NULL ,
                       `secret`                TINYINT DEFAULT 0 ,
                       PRIMARY KEY ( `inquiry_number` ) ,
                       FOREIGN KEY ( `user_id` ) REFERENCES user ( id ) ,
                       FOREIGN KEY ( `store_id` ) REFERENCES user ( id ) ,
                       FOREIGN KEY ( `product_number` ) REFERENCES product ( product_number )
                       );

CREATE TABLE `live` (
                    `live_number`     int         NOT NULL AUTO_INCREMENT ,
	        'room_id'	     varchar(40) NOT NULL , 
                    `store_id`        varchar(25) NOT NULL ,
                    `live_title`      varchar(20) NOT NULL ,
                    `live_intro`      varchar(30) DEFAULT NULL ,
                    `live_image`      varchar(30) DEFAULT 'noimage.jpg' ,
                    `live_status`     tinyint     DEFAULT '0' ,
                    `chatting_status` tinyint     DEFAULT '0' ,
                    PRIMARY KEY ( `live_number`, 'room_id' ) ,
                    FOREIGN KEY ( store_id ) REFERENCES user ( id )
                    );

CREATE TABLE `live_user_status` (
                                `live_number`  int         NOT NULL ,
                                `id`           varchar(25) NOT NULL ,
                                `alarm_status` tinyint DEFAULT '0' ,
                                `kick_status`  tinyint DEFAULT '0' ,
                                `dumb_status`  tinyint DEFAULT '0' ,
                                FOREIGN KEY ( live_number ) REFERENCES live ( live_number )
                                );

CREATE TABLE live_reservation (
                              `live_reservation_number` integer(5)  NOT NULL AUTO_INCREMENT ,
                              `store_id`                varchar(25) NULL ,
                              `reservation_date`        varchar(25)        NULL ,

                              PRIMARY KEY ( live_reservation_number ) ,
                              FOREIGN KEY ( store_id ) REFERENCES user ( id )
                              );

CREATE TABLE live_product (
                               `live_product_number`     INTEGER(5)  NOT NULL AUTO_INCREMENT ,
                               `live_number`             INTEGER(5)  NOT NULL ,
                               `live_reservation_number` INTEGER(5)  NOT NULL ,
                               `product_number`          INTEGER(5)  NOT NULL ,
                               `product_main_image`      VARCHAR(30) NOT NULL ,
                               PRIMARY KEY ( live_product_number ) ,
                               FOREIGN KEY ( live_number ) REFERENCES live ( live_number ) ,
                               FOREIGN KEY ( product_number ) REFERENCES product ( product_number )
                               );

CREATE TABLE live_chat (
                       `live_number`      INTEGER     NOT NULL ,
                       `writer`           VARCHAR(20) NOT NULL ,
                       `chatting_message` VARCHAR(30) NOT NULL ,
                       `send_date`        DATE        NOT NULL ,
                       FOREIGN KEY ( live_number ) REFERENCES live ( live_number ) ,
                       FOREIGN KEY ( writer ) REFERENCES user ( id )
                       );

ALTER TABLE user AUTO_INCREMENT = 10000;
ALTER TABLE store_application_document AUTO_INCREMENT = 10000;
ALTER TABLE product AUTO_INCREMENT = 10000;
ALTER TABLE cart AUTO_INCREMENT = 10000;
ALTER TABLE inquiry AUTO_INCREMENT = 10000;
ALTER TABLE review AUTO_INCREMENT = 10000;
ALTER TABLE live AUTO_INCREMENT = 10000;
ALTER TABLE live_reservation AUTO_INCREMENT = 10000;
ALTER TABLE live_product AUTO_INCREMENT = 10000;
ALTER TABLE `order` AUTO_INCREMENT = 10000;

---ALERT customer

ALTER TABLE customer MODIFY COLUMN customer_FAQ_category_code INT NULL;
ALTER TABLE customer MODIFY reg_date TIMESTAMP; 
ALTER TABLE customer MODIFY update_date TIMESTAMP;
ALTER TABLE customer ADD view_cnt INT DEFAULT 0;
ALTER TABLE customer ADD delete_yn ENUM('Y', 'N') NOT NULL DEFAULT 'N' ;

---ALERT ban_period

ALTER TABLE ban_period MODIFY COLUMN respondent_nickname VARCHAR(20) NULL;
ALTER TABLE ban_period MODIFY COLUMN respondent_store_name VARCHAR(20) NULL;

-- insert sql

-- user
INSERT INTO user (id, password, gender, address, postal_code, nickname, phone_number, name, reg_date, join_path, book_number, total_point, store_logo_image, store_introduction, bank_account_copy_image, bank_name,bank_account_number, role, store_name, withdrawal_status)
VALUES ('admin@naver.com','admin',NULL,'어드민',1111,NULL,'01022222223','어드민','2022-05-01','HOME',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin',NULL,0);
INSERT INTO user (id, password, gender, address, postal_code, nickname, phone_number, name, reg_date, join_path, book_number, total_point, store_logo_image, store_introduction, bank_account_copy_image, bank_name, bank_account_number,role, store_name, withdrawal_status)
VALUES  ('store01@naver.com','store01','N','서울특별시 강남구',3024,'','01011212224','이지원','2022-05-30','HOME',NULL,100,'store_logo_image01.png','store_introduction 04','bank_account_copy_image04.png','신한', '23108051211987','store','간지나 스토어1',0);
INSERT INTO user (id, password, gender, address, postal_code, nickname, phone_number, name, reg_date, join_path, book_number, total_point, store_logo_image, store_introduction, bank_account_copy_image, bank_name, bank_account_number, role, store_name, withdrawal_status)
VALUES  ('store02@naver.com','store02',NULL,'서울특별시',12500,NULL,'01012345678','태지원','2022-05-27','HOME',NULL,NULL,'store_logo_image02.png','스토어소개','bank_account_image02.png','농협', '23108051211987','store','반짝반짝스토어2',0);
INSERT INTO user (id, password, gender, address, postal_code, nickname, phone_number, name, reg_date, join_path, book_number, total_point, store_logo_image, store_introduction, bank_account_copy_image, bank_name, bank_account_number, role, store_name, withdrawal_status)
VALUES  ('store03@naver.com','store03',NULL,'서울시강남구',1234,NULL,'01022222222','이순신','2022-05-21','HOME',NULL,NULL,'store_logo_image03.png','수제과제를 파는!! 곳','bank_account_image03.png','신한','2111111111111' , 'storeX','수제과자3',0);
INSERT INTO user (id, password, gender, address, postal_code, nickname, phone_number, name, reg_date, join_path, book_number, total_point, store_logo_image, store_introduction, bank_account_copy_image, bank_name,bank_account_number, role, store_name, withdrawal_status)
VALUES  ('store04@naver.com','store04',NULL,'서울특별시 강남구',3024,NULL,'01011211224','이지원','2022-05-30','HOME',NULL,100,'store_logo_image01.png','store_introduction 04','bank_account_copy_image04.png',NULL,'신한','store','초초코스토어04',0);
INSERT INTO user (id, password, gender, address, postal_code, nickname, phone_number, name, reg_date, join_path, book_number, total_point, store_logo_image, store_introduction, bank_account_copy_image, bank_name, bank_account_number,role, store_name, withdrawal_status)
VALUES  ('store05@naver.com','store05',NULL,'서울특별시',12500,NULL,'01012345278','홍길동','2022-05-27','HOME',NULL,NULL,'store_logo_image05.png','스토어소개','bank_account_image02.png','농협' ,'23108051211987','store','bit05',0);
INSERT INTO user (id, password, gender, address, postal_code, nickname, phone_number, name, reg_date, join_path, book_number, total_point, store_logo_image, store_introduction, bank_account_copy_image, bank_name, bank_account_number,role, store_name, withdrawal_status)
VALUES  ('store06@naver.com','store06',NULL,'서울시강남구',1234,NULL,'01012222222','하지원','2022-05-29','HOME',NULL,NULL,'store_logo_image06.png','수제과제를 파는!! 곳','bank_account_image03.png','신한', '2111111111111','store','bit06',0);
INSERT INTO user (id, password, gender, address, postal_code, nickname, phone_number, name, reg_date, join_path, book_number, total_point, store_logo_image, store_introduction, bank_account_copy_image, bank_name, bank_account_number,role, store_name, withdrawal_status)
VALUES  ('user01@naver.com','user05','N','경기도 고양시 원흥로',125,'nickname01','01011112112','홍길동','2022-05-30','HOME',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'user',NULL,0);
INSERT INTO user (id, password, gender, address, postal_code, nickname, phone_number, name, reg_date, join_path, book_number, total_point, store_logo_image, store_introduction, bank_account_copy_image, bank_name, bank_account_number,role, store_name, withdrawal_status)
VALUES ('user02@naver.com','user02','N','경기도 고양시 삼성로',2014,'nickname02','01011212234','이순신','2022-05-30','KAKAO',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'user',NULL,0);
INSERT INTO user (id, password, gender, address, postal_code, nickname, phone_number, name, reg_date, join_path, book_number, total_point, store_logo_image, store_introduction, bank_account_copy_image, bank_name, bank_account_number,role, store_name, withdrawal_status)
VALUES  ('user03@naver.com','user03','N','경기도 고양시 원흥로3로',30241,'nickname03','01011212356','김지원','2022-05-30','NAVER',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'user',NULL,0);
INSERT INTO user (id, password, gender, address, postal_code, nickname, phone_number, name, reg_date, join_path, book_number, total_point, store_logo_image, store_introduction, bank_account_copy_image, bank_name,bank_account_number, role, store_name, withdrawal_status)
VALUES  ('user04@naver.com','user04','N','경기도 고양시 원흥로',125,'nickname04','01011112278','홍길동','2022-05-30','HOME',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'user',NULL,0);
INSERT INTO user (id, password, gender, address, postal_code, nickname, phone_number, name, reg_date, join_path, book_number, total_point, store_logo_image, store_introduction, bank_account_copy_image, bank_name, bank_account_number,role, store_name, withdrawal_status)
VALUES ('user05@naver.com','user05','N','경기도 고양시 삼성로',2014,'nickname05','01011212291','이순신','2022-05-30','KAKAO',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'user',NULL,0);
INSERT INTO user (id, password, gender, address, postal_code, nickname, phone_number, name, reg_date, join_path, book_number, total_point, store_logo_image, store_introduction, bank_account_copy_image, bank_name, bank_account_number ,role, store_name, withdrawal_status)
VALUES  ('user06@naver.com','user06','N','경기도 고양시 원흥로3로',30241,'nickname06','01011882322','김지원','2022-05-30','NAVER',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'user',NULL,0);

-- store application document
INSERT INTO store_application_document (`store_application_document_number`,`id`,`examination_status`,`product_detail`,`reg_date`) VALUES (10000,'store01@naver.com','R','우리 스토어 오랜동안 베이킹을 배운 장인이 만든 과자를 판매합니다. ','2022-05-02');
INSERT INTO store_application_document (`store_application_document_number`,`id`,`examination_status`,`product_detail`,`reg_date`) VALUES (10001,'store02@naver.com','A','우리스토어','2022-05-30');
INSERT INTO store_application_document (`store_application_document_number`,`id`,`examination_status`,`product_detail`,`reg_date`) VALUES (10002,'store03@naver.com','W','product_detail 3','2022-05-30');
INSERT INTO store_application_document (`store_application_document_number`,`id`,`examination_status`,`product_detail`,`reg_date`) VALUES (10003,'store01@naver.com','A','product_detail 4','2022-05-30');


-- product
INSERT INTO product (product_number, product_name, product_price, store_id, product_main_image, product_detail, product_category_code, product_status, reg_date, delivery_charge, product_quantity)
VALUES (10000, '수제청 딸기청 패션후르츠 결혼식 답례품 생일선물', 5000, 'store03@naver.com', 'product_main_image.jpg', 'product_detail 01', '01', '01', NOW(), 3000, 10);
INSERT INTO product (product_name, product_price, store_id, product_main_image, product_category_code, product_status, reg_date, delivery_charge, product_quantity, product_group_number)
VALUES ('종류: 딸기청best / 용량: 250ml', 6000, 'store03@naver.com', 'product_main_image.jpg', '01', '01', NOW(), 3000, 10, 10000);
INSERT INTO product (product_name, product_price, store_id, product_main_image, product_category_code, product_status, reg_date, delivery_charge, product_quantity, product_group_number)
VALUES ('종류: 자몽청best / 용량: 250ml', 6000, 'store03@naver.com', 'product_main_image.jpg', '01', '01', NOW(), 3000, 10, 10000);
INSERT INTO product (product_name, product_price, store_id, product_main_image, product_category_code, product_status, reg_date, delivery_charge, product_quantity, product_group_number)
VALUES ('종류: 오렌지청best / 용량: 250ml', 6000, 'store03@naver.com', 'product_main_image.jpg', '01', '02', NOW(), 3000, 10, 10000);
INSERT INTO product (product_name, product_price, store_id, product_main_image, product_category_code, product_status, reg_date, delivery_charge, product_quantity, product_group_number)
VALUES ('종류: 포도청best / 용량: 250ml', 6000, 'store03@naver.com', 'product_main_image.jpg', '01', '03', NOW(), 3000, 0, 10000);
INSERT INTO product (product_name, product_price, store_id, product_main_image, product_detail, product_category_code, product_status, reg_date, delivery_charge, product_quantity)
VALUES ('식빵 쿠키커터 스마일식빵 쿠키틀', 7000, 'store02@naver.com', 'product_main_image.jpg', 'product_detail 02', '02', '01', NOW(), 3000, 20);
INSERT INTO product (product_name, product_price, store_id, product_main_image, product_detail, product_category_code, product_status, reg_date, delivery_charge, product_quantity)
VALUES ('진정밤 멀티밤 트러블에 정말 좋아요~', 10000, 'store02@naver.com', 'product_main_image.jpg', 'product_detail 03', '03', '01', NOW(), 3000, 30);
INSERT INTO product (product_name, product_price, store_id, product_main_image, product_category_code, product_status, reg_date, delivery_charge, product_quantity, product_group_number)
VALUES ('성분: 라벤더', 6000, 'store02@naver.com', 'product_main_image.jpg', '03', '01', NOW(), 3000, 10, 10006);
INSERT INTO product (product_name, product_price, store_id, product_main_image, product_category_code, product_status, reg_date, delivery_charge, product_quantity, product_group_number)
VALUES ('성분: 로즈마리', 6000, 'store02@naver.com', 'product_main_image.jpg', '03', '01', NOW(), 3000, 10, 10006);
INSERT INTO product (product_name, product_price, store_id, product_main_image, product_category_code, product_status, reg_date, delivery_charge, product_quantity, product_group_number)
VALUES ('성분: 로즈', 6000, 'store02@naver.com', 'product_main_image.jpg', '03', '02', NOW(), 3000, 10, 10006);
INSERT INTO product (product_name, product_price, store_id, product_main_image, product_category_code, product_status, reg_date, delivery_charge, product_quantity, product_group_number)
VALUES ('성분: 카모마일', 6000, 'store02@naver.com', 'product_main_image.jpg', '03', '03', NOW(), 3000, 0, 10006);
INSERT INTO product (product_name, product_price, store_id, product_main_image, product_detail, product_category_code, product_status, reg_date, delivery_charge, product_quantity)
VALUES ('아디다스 티셔츠 한정판', 12000, 'store01@naver.com', 'product_main_image.jpg', 'product_detail 02', '04', '01', NOW(), 3000, 10);
INSERT INTO product (product_name, product_price, store_id, product_main_image, product_detail, product_category_code, product_status, reg_date, delivery_charge, product_quantity)
VALUES ('감성 무드등', 30000, 'store01@naver.com', 'product_main_image.jpg', 'product_detail 02', '05', '01', NOW(), 3000, 10);
INSERT INTO product (product_name, product_price, store_id, product_main_image, product_detail, product_category_code, product_status, reg_date, delivery_charge, product_quantity)
VALUES ('순면 100% 턱받침', 15300, 'store01@naver.com', 'product_main_image.jpg', 'product_detail 02', '06', '01', NOW(), 3000, 10);
INSERT INTO product (product_name, product_price, store_id, product_main_image, product_detail, product_category_code, product_status, reg_date, delivery_charge, product_quantity)
VALUES ('수제 터키 과자 딜라이트', 10000, 'store03@naver.com', 'product_main_image.jpg', 'product_detail 02', '01', '01', NOW(), 3000, 10);
INSERT INTO product (product_name, product_price, store_id, product_main_image, product_detail, product_category_code, product_status, reg_date, delivery_charge, product_quantity)
VALUES ('프랑스에서 만든 마카롱 10개 세트', 20000, 'store03@naver.com', 'product_main_image.jpg', 'product_detail 02', '01', '01', NOW(), 3000, 10);


-- order
INSERT INTO `order` (product_number, buyer_id, store_id, order_bundle_number, order_date, order_quantity, order_status, receiver_name, receiver_phone, receiver_address, receiver_request, delivery_tracking_number, delivery_company_code, payment_option, total_payment_price, reward_point, using_point, order_claim_request_date, order_claim_response_date, order_claim_reason)
VALUES (10001, 'user01@naver.com', 'store03@naver.com', NOW(), NOW(), 1, '주문접수', '홍길동', '01039372812', '어쩌구 어쩔동 어쩔아파트', '어쩔티비 저쩔티비', NULL, NULL, '카카오페이', 188887, 1, NULL, NOW(), NOW(), NULL);


-- live
INSERT INTO live (store_id, live_title, live_intro, live_image, live_status, chatting_status) VALUES ('admin@naver.com', 'live_title 01', 'live_intro 01', 'live_image 01', 1, 1);
INSERT INTO live (store_id, live_title, live_intro, live_image, live_status, chatting_status) VALUES ('store01@naver.com', 'live_title 02', 'live_intro 02', 'live_image 02', 1, 1);
INSERT INTO live (store_id, live_title, live_intro, live_image, live_status, chatting_status) VALUES ('store02@naver.com', 'live_title 03', 'live_intro 03', 'live_image 03', 1, 1);
INSERT INTO live (store_id, live_title, live_intro, live_image, live_status, chatting_status) VALUES ('store03@naver.com', 'live_title 04', 'live_intro 04', 'live_image 04', 1, 1);

INSERT INTO live_user_status (live_number, id, alarm_status, kick_status, dumb_status) VALUES (10000, 'user01@naver.com', 01, 00, 00);
INSERT INTO live_user_status (live_number, id, alarm_status, kick_status, dumb_status) VALUES (10001, 'user02@naver.com', 00, 02, 01);
INSERT INTO live_user_status (live_number, id, alarm_status, kick_status, dumb_status) VALUES (10002, 'user03@naver.com', 03, 01, 02);

INSERT INTO live_reservation (store_id, reservation_date) VALUES ('admin@naver.com', '2022-05-31 13:00');
INSERT INTO live_reservation (store_id, reservation_date) VALUES ('store01@naver.com', '2022-04-21 09:00');
INSERT INTO live_reservation (store_id, reservation_date) VALUES ('store02@naver.com', '2022-07-15 12:00');
INSERT INTO live_reservation (store_id, reservation_date) VALUES ('store03@naver.com', '2022-06-4 18:00');

INSERT INTO live_product (live_number, live_reservation_number, product_number, product_main_image) VALUES (10001, 10001, 10011, 'product_main_image 01');
INSERT INTO live_product (live_number, live_reservation_number, product_number, product_main_image) VALUES (10001, 10001, 10012, 'product_main_image 02');
INSERT INTO live_product (live_number, live_reservation_number, product_number, product_main_image) VALUES (10001, 10001, 10013, 'product_main_image 03');
INSERT INTO live_product (live_number, live_reservation_number, product_number, product_main_image) VALUES (10002, 10002, 10005, 'product_main_image 04');
INSERT INTO live_product (live_number, live_reservation_number, product_number, product_main_image) VALUES (10002, 10002, 10006, 'product_main_image 05');
INSERT INTO live_product (live_number, live_reservation_number, product_number, product_main_image) VALUES (10002, 10002, 10007, 'product_main_image 06');
INSERT INTO live_product (live_number, live_reservation_number, product_number, product_main_image) VALUES (10003, 10003, 10000, 'product_main_image 07');
INSERT INTO live_product (live_number, live_reservation_number, product_number, product_main_image) VALUES (10003, 10003, 10001, 'product_main_image 08');
INSERT INTO live_product (live_number, live_reservation_number, product_number, product_main_image) VALUES (10003, 10003, 10003, 'product_main_image 09');
INSERT INTO live_product (live_number, live_reservation_number, product_number, product_main_image) VALUES (10003, 10003, 10015, 'product_main_image 10');

INSERT INTO live_chat (live_number, writer, chatting_message, send_date) VALUES (10001, 'user01@naver.com', 'user01의 message 01', NOW());
INSERT INTO live_chat (live_number, writer, chatting_message, send_date) VALUES (10001, 'user01@naver.com', 'user01의 message 02', NOW());
INSERT INTO live_chat (live_number, writer, chatting_message, send_date) VALUES (10002, 'user02@naver.com', 'user02의 message 01', NOW());
INSERT INTO live_chat (live_number, writer, chatting_message, send_date) VALUES (10002, 'user02@naver.com', 'user02의 message 02', NOW());
INSERT INTO live_chat (live_number, writer, chatting_message, send_date) VALUES (10003, 'user03@naver.com', 'user03의 message 01', NOW());
INSERT INTO live_chat (live_number, writer, chatting_message, send_date) VALUES (10003, 'user03@naver.com', 'user03의 message 02', NOW());

--customer
INSERT INTO customer(customer_board_title, customer_board_content, reg_date, update_date, customer_FAQ_category_code, customer_board_type, customer_id)
VALUES('TITLE 1', 'CONTNET 1', now(), NULL, 1, 'N', 'admin@naver.com');
INSERT INTO customer(customer_board_title, customer_board_content, reg_date, update_date, customer_FAQ_category_code, customer_board_type, customer_id)
VALUES('TITLE 2', 'CONTNET 2', now(), NULL, 2, 'N', 'admin@naver.com');
INSERT INTO customer(customer_board_title, customer_board_content, reg_date, update_date, customer_FAQ_category_code, customer_board_type, customer_id)
VALUES('TITLE 3', 'CONTNET 3', now(), NULL, 3, 'F', 'admin@naver.com');
INSERT INTO customer(customer_board_title, customer_board_content, reg_date, update_date, customer_FAQ_category_code, customer_board_type, customer_id)
VALUES('TITLE 4', 'CONTNET 4', now(), NULL, 4, 'R', 'admin@naver.com');
INSERT INTO customer(customer_board_title, customer_board_content, reg_date, update_date, customer_FAQ_category_code, customer_board_type, customer_id)
VALUES('TITLE 5', 'CONTNET 5', now(), now(), 3, 'N', 'admin@naver.com');
INSERT INTO customer(customer_board_title, customer_board_content, reg_date, update_date, customer_FAQ_category_code, customer_board_type, customer_id)
VALUES('TITLE 6', 'CONTNET 6', now(), NULL, 2, 'N', 'admin@naver.com');
INSERT INTO customer(customer_board_title, customer_board_content, reg_date, update_date, customer_FAQ_category_code, customer_board_type, customer_id)
VALUES('TITLE 7', 'CONTNET 7', now(), now(), 1, 'N', 'admin@naver.com');
INSERT INTO customer(customer_board_title, customer_board_content, reg_date, update_date, customer_FAQ_category_code, customer_board_type, customer_id)
VALUES('TITLE 8', 'CONTNET 8', now(), NULL, 4, 'N', 'admin@naver.com');


UPDATE customer SET customer_board_type='N', customer_board_title="TITLE 22", customer_board_title="CONTENT 22", update_date = now(), customer_id = "admin@naver.com" 
WHERE customer_board_number = 10;
UPDATE customer SET customer_board_type='F', customer_board_title="TITLE 33", customer_board_title="CONTENT 33", update_date = now(), customer_id = "admin@naver.com" 
WHERE customer_board_number = 5;
UPDATE customer SET customer_board_type='R', customer_board_title="TITLE 44", customer_board_title="CONTENT 44", update_date = now(), customer_id = "admin@naver.com" 
WHERE customer_board_number = 6;
UPDATE customer SET customer_board_type='L', customer_board_title="TITLE 55", customer_board_title="CONTENT 55", update_date = now(), customer_id = "admin@naver.com" 
WHERE customer_board_number = 7;
