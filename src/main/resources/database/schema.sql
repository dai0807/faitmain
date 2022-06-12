-- init sql
DROP TABLE IF EXISTS inquiry CASCADE;
DROP TABLE IF EXISTS cart CASCADE;
DROP TABLE IF EXISTS review CASCADE;
DROP TABLE IF EXISTS `order_product` CASCADE;
DROP TABLE IF EXISTS `order` CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS store_application_document CASCADE;
DROP TABLE IF EXISTS ban_period CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS user CASCADE;
DROP TABLE IF EXISTS image CASCADE;
DROP TABLE IF EXISTS live_chat CASCADE;
DROP TABLE IF EXISTS live_product CASCADE;
DROP TABLE IF EXISTS live_reservation CASCADE;
DROP TABLE IF EXISTS live_user_status CASCADE;
DROP TABLE IF EXISTS live CASCADE;


-- create sql

CREATE TABLE `user` (
                    `user_number`        INTEGER      NOT NULL AUTO_INCREMENT ,
                    `id`                 VARCHAR(25)  NOT NULL ,
                    `password`           VARCHAR(15)  NOT NULL ,
                    `gender`             CHAR(4)      NULL ,
                    `user_address1`      VARCHAR(100) NOT NULL ,
                    `user_address2`      VARCHAR(100) NOT NULL ,
                    `user_address3`      VARCHAR(100) NOT NULL ,
                    `nickname`           VARCHAR(20)  NULL ,
                    `phone_number`       VARCHAR(12)  NULL ,
                    `name`               VARCHAR(10)  NOT NULL ,
                    `reg_date`           DATE         NULL ,
                    `join_path`          VARCHAR(5)   NOT NULL DEFAULT 'HOME' ,
                    `book_number`        INT(5)       NULL ,
                    `total_point`        INT          NULL ,
                    `store_logo_image`   VARCHAR(100) NULL ,
                    `store_introduction` VARCHAR(200) NULL ,
                    `role`               VARCHAR(6)   NOT NULL DEFAULT 'user' ,
                    `store_name`         VARCHAR(20)  NULL ,
                    `withdrawal_status`  TINYINT               DEFAULT 0 ,
                    PRIMARY KEY ( `user_number` ) ,
                    UNIQUE INDEX `id_UNIQUE` ( `id` ASC ) VISIBLE ,
                    UNIQUE INDEX `phone_number_UNIQUE` ( `phone_number` ASC ) VISIBLE ,
                    UNIQUE INDEX `nickname_UNIQUE` ( `nickname` ASC ) VISIBLE ,
                    UNIQUE INDEX `store_name_UNIQUE` ( `store_name` ASC ) VISIBLE
                    );

CREATE TABLE `product` (
                       `product_number`        INT(5)       NOT NULL AUTO_INCREMENT ,
                       `product_name`          VARCHAR(50)  NOT NULL ,
                       `product_price`         INT          NOT NULL ,
                       `store_id`              VARCHAR(25)  NOT NULL ,
                       `product_main_image`    VARCHAR(100) NOT NULL ,
                       `product_detail`        LONGTEXT     NULL ,
                       `product_category_code` CHAR(2)      NOT NULL ,
                       `product_status`        CHAR(2) DEFAULT '01' ,
                       `reg_date`              DATETIME     NOT NULL ,
                       `delivery_charge`       INT          NOT NULL ,
                       `product_quantity`      INT          NOT NULL ,
                       `product_group_number`  INT(5)       NULL ,
                       PRIMARY KEY ( `product_number` ) ,
                       FOREIGN KEY ( `store_id` ) REFERENCES user ( id )
                       );


/* ******************************************************************************** */


CREATE TABLE `order` (
                     `order_number`      int          NOT NULL AUTO_INCREMENT ,
                     `buyer_id`          varchar(50)  NOT NULL ,
                     `receiver_name`     varchar(50)  NOT NULL ,
                     `receiver_address1` varchar(100) NOT NULL ,
                     `receiver_address2` varchar(100) NOT NULL ,
                     `receiver_address3` varchar(100) NOT NULL ,
                     `order_status`      varchar(30)  NOT NULL ,
                     `delivery_charge`   int          NOT NULL ,
                     `using_point`       int          NOT NULL ,
                     `order_date`        timestamp    NULL DEFAULT NOW( ) ,
                     PRIMARY KEY ( order_number ) ,
                     FOREIGN KEY ( buyer_id ) REFERENCES user ( id )
                     );


CREATE TABLE order_product (
                           `order_product_number` int NOT NULL AUTO_INCREMENT ,
                           `order_number`         int NULL ,
                           `product_number`       int NULL ,
                           `product_order_count`     int NOT NULL ,
                           `product_price`        int NOT NULL ,
                           `reward_point`         int NOT NULL ,
                           PRIMARY KEY ( order_product_number ) ,
                           FOREIGN KEY ( order_number ) REFERENCES `order` ( order_number ) ,
                           FOREIGN KEY ( product_number ) REFERENCES product ( product_number )
                           );

/* ******************************************************************************** */


CREATE TABLE `store_application_document` (
                                          `store_application_document_number` INT(5)      NOT NULL AUTO_INCREMENT ,
                                          `id`                                VARCHAR(25) NOT NULL ,
                                          `examination_status`                VARCHAR(4)  NOT NULL ,
                                          `product_detail`                    LONGTEXT    NOT NULL ,
                                          `reg_date`                          DATE        NOT NULL ,
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
                    `cart_number`         INT         NOT NULL AUTO_INCREMENT ,
                    `user_id`             VARCHAR(25) NOT NULL ,
                    `product_order_count` INT         NOT NULL ,
                    `product_number`      INT(5)      NOT NULL ,
                    `product_name`        VARCHAR(50) NOT NULL ,
                    `product_main_image`  VARCHAR(30) NOT NULL ,
                    `product_price`       INT         NOT NULL ,
                    PRIMARY KEY ( `cart_number` ) ,
                    FOREIGN KEY ( user_id ) REFERENCES user ( id ) ,
                    FOREIGN KEY ( product_number ) REFERENCES product ( product_number )
                    );

CREATE TABLE `image` (
                     `image_number`                INT(5)       NOT NULL AUTO_INCREMENT ,
                     `image_classification_code`   CHAR(1)      NOT NULL ,
                     `image_classification_number` INT(5)       NOT NULL ,
                     `image_name`                  VARCHAR(100) NOT NULL ,
                     PRIMARY KEY ( `image_number` )
                     );

CREATE TABLE `customer` (
                        `customer_board_number`      int           NOT NULL AUTO_INCREMENT ,
                        `customer_board_title`       varchar(30)   NOT NULL ,
                        `customer_board_content`     varchar(1000) NOT NULL ,
                        `reg_date`                   TIMESTAMP     NOT NULL ,
                        `update_date`                TIMESTAMP		DEFAULT NULL ,
                        `customer_FAQ_category_code` 	int    		DEFAULT NULL,=======
                        `reg_date`                   date          NOT NULL ,
                        `update_date`                date 		   DEFAULT NULL ,
                        `customer_FAQ_category_code` int           NOT NULL ,
                        `customer_board_type`        char(1)       NOT NULL ,
                        `customer_id`                varchar(25)   NOT NULL ,
                        'delete_yn'					 ENUM('Y','N') DEFAULT 'N',
                        'view_cnt'					 int			DEFAULT NULL, 
                        PRIMARY KEY ( `customer_board_number` ) ,
                        FOREIGN KEY ( `customer_id` ) REFERENCES `user` ( `id` )
                        );



CREATE TABLE `ban_period` (
                          `report_number`         int         NOT NULL ,
                          `respondent_id`         varchar(25) NOT NULL ,
                          `respondent_nickname`   varchar(20) NOT NULL ,
                          `respondent_store_name` varchar(20) NOT NULL ,
                          `status_number`         int DEFAULT NULL ,
                          `ban_period_number`     int DEFAULT NULL ,
                          `ban_end_date`          date        NOT NULL ,
                          FOREIGN KEY ( `report_number` ) REFERENCES `customer` ( `customer_board_number` ) ,
                          FOREIGN KEY ( `respondent_id` ) REFERENCES `user` ( `id` ),
                          FOREIGN KEY ('respondent_nickname') REFERENCES user (nickname),
                          FOREIGN KEY ('respondent_store_name') REFERENCES user (store_name)
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
                    `room_id`         varchar(40) NULL ,
                    `store_id`        varchar(25) NOT NULL ,
                    `live_title`      varchar(20) NOT NULL ,
                    `live_intro`      varchar(30) DEFAULT NULL ,
                    `live_image`      varchar(30) DEFAULT 'noimage.jpg' ,
                    `live_status`     tinyint     DEFAULT '0' ,
                    `chatting_status` tinyint     DEFAULT '0' ,
                    PRIMARY KEY ( `live_number` )
                    );

CREATE TABLE `live_user_status` (
                                `live_number`  int         NOT NULL ,
                                `id`           varchar(25) NOT NULL ,
                                `alarm_status` tinyint DEFAULT '0' ,
                                `kick_status`  tinyint DEFAULT '0' ,
                                `dumb_status`  tinyint DEFAULT '0'
                                );

CREATE TABLE live_reservation (
                              `live_reservation_number` integer(5)  NOT NULL AUTO_INCREMENT ,
                              `store_id`                varchar(25) NULL ,
                              `reservation_date`        varchar(25) NULL ,
                              `title`                   varchar(25) NULL ,
                              `reservation_time`        varchar(25) NULL ,

                              PRIMARY KEY ( live_reservation_number )
                              );

CREATE TABLE live_product (
                               `live_product_number`     INTEGER(5)  NOT NULL AUTO_INCREMENT ,
                               `live_number`             INTEGER(5)  NOT NULL ,
                               `live_reservation_number` INTEGER(5)  NOT NULL ,
                               `product_number`          INTEGER(5)  NOT NULL ,
                               `product_main_image`      VARCHAR(30) NOT NULL ,
                               `product_name`			 VARCHAR(45) NOT NULL ,
                               `product_detail`			 VARCHAR(30) NULL,
                               PRIMARY KEY ( live_product_number )
                               );


CREATE TABLE live_chat (
                       `live_number`      INTEGER     NOT NULL ,
                       `writer`           VARCHAR(20) NOT NULL ,
                       `chatting_message` VARCHAR(30) NOT NULL ,
                       `send_date`        DATE        NOT NULL 
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





