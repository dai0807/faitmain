DROP TABLE IF EXISTS ban_period CASCADE;

DROP TABLE IF EXISTS cart CASCADE;

DROP TABLE IF EXISTS customer CASCADE;

DROP TABLE IF EXISTS image CASCADE;

DROP TABLE IF EXISTS inquiry CASCADE;

DROP TABLE IF EXISTS live_chat CASCADE;

DROP TABLE IF EXISTS live_product_list CASCADE;

DROP TABLE IF EXISTS live_reservation CASCADE;

DROP TABLE IF EXISTS live_user_status CASCADE;

DROP TABLE IF EXISTS live CASCADE;

DROP TABLE IF EXISTS review CASCADE;

DROP TABLE IF EXISTS `order` CASCADE;

DROP TABLE IF EXISTS product CASCADE;

DROP TABLE IF EXISTS store_application_document CASCADE;

DROP TABLE IF EXISTS user CASCADE;



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
                       `price`                 INT         NOT NULL ,
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
                     order_number              INTEGER AUTO_INCREMENT ,
                     product_number            INTEGER      NOT NULL ,
                     buyer_id                  VARCHAR(25)  NOT NULL ,
                     store_id                  VARCHAR(25)  NULL ,
                     order_bundle_number       INTEGER      NOT NULL ,
                     order_date                DATE         NOT NULL ,
                     order_quantity            INTEGER      NOT NULL ,
                     order_status              VARCHAR(25)  NOT NULL ,
                     receiver_name             VARCHAR(25)  NOT NULL ,
                     receiver_phone            INTEGER      NOT NULL ,
                     receiver_address          VARCHAR(25)  NOT NULL ,
                     receiver_request          VARCHAR(25)  NULL ,
                     delivery_tracking_number  INTEGER      NULL ,
                     delivery_company_code     VARCHAR(25)  NULL ,
                     payment_option            VARCHAR(25)  NOT NULL ,
                     total_payment_price       INTEGER      NULL ,
                     reward_point              INTEGER      NULL ,
                     using_point               INTEGER      NULL ,
                     order_claim_request_date  DATE         NOT NULL ,
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
                        `customer_FAQ_category_code` int           NOT NULL ,
                        `customer_board_type`        char(1)       NOT NULL ,
                        `customer_id`                varchar(25)   NOT NULL ,
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
                    `store_id`        varchar(25) NOT NULL ,
                    `live_title`      varchar(20) NOT NULL ,
                    `live_intro`      varchar(30) DEFAULT NULL ,
                    `live_image`      varchar(30) DEFAULT 'noimage.jpg' ,
                    `chatting_status` tinyint     DEFAULT '0' ,
                    PRIMARY KEY ( `live_number` ) ,
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
                              `reservation_date`        DATE        NULL ,

                              PRIMARY KEY ( live_reservation_number ) ,
                              FOREIGN KEY ( store_id ) REFERENCES user ( id )
                              );

CREATE TABLE live_product_list (
                               `live_product_number`     INTEGER(5)  NOT NULL AUTO_INCREMENT ,
                               `live_number`             INTEGER(5)  NOT NULL ,
                               `live_reservation_number` INTEGER(5)  NOT NULL ,
                               `product_number`          INTEGER(5)  NOT NULL ,
                               `product_main_image`      VARCHAR(30) NOT NULL ,
                               PRIMARY KEY ( live_product_number ) ,
                               FOREIGN KEY ( live_number ) REFERENCES live ( live_number ) ,
                               FOREIGN KEY ( live_reservation_number ) REFERENCES live_reservation ( live_reservation_number ) ,
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