
/* USER */
INSERT INTO user (id, password, gender, user_address1, user_address2, user_address3, nickname, phone_number, name, reg_date, join_path, book_number, total_point, store_logo_image, store_introduction, role, store_name, withdrawal_status)
     VALUES ('id 1', 'password 1', 'N', 'userAddress1 1', 'userAddress2 1', 'userAddress3 1', 'nickname 1', 'phone_numbe1', 'name 1', NOW(), 'N', 1, 1, 'store_logo_image 1', 'store_introduction 1', 'role 1', 'store_name 1', 1);
INSERT INTO user (id, password, gender, user_address1, user_address2, user_address3, nickname, phone_number, name, reg_date, join_path, book_number, total_point, store_logo_image, store_introduction, role, store_name, withdrawal_status)
     VALUES ('id 2', 'password 2', 'N', 'userAddress1 2', 'userAddress2 2', 'userAddress3 2', 'nickname 2', 'phone_numbe2', 'name 2', NOW(), 'N', 2, 2, 'store_logo_image 2', 'store_introduction 2', 'role 2', 'store_name 2', 2);
INSERT INTO user (id, password, gender, user_address1, user_address2, user_address3, nickname, phone_number, name, reg_date, join_path, book_number, total_point, store_logo_image, store_introduction, role, store_name, withdrawal_status)
     VALUES ('id 3', 'password 3', 'N', 'userAddress1 3', 'userAddress2 3', 'userAddress3 3', 'nickname 3', 'phone_numbe3', 'name 3', NOW(), 'N', 3, 3, 'store_logo_image 3', 'store_introduction 3', 'role 3', 'store_name 3', 3);
INSERT INTO user (id, password, gender, user_address1, user_address2, user_address3, nickname, phone_number, name, reg_date, join_path, book_number, total_point, store_logo_image, store_introduction, role, store_name, withdrawal_status)
     VALUES ('id 4', 'password 4', 'N', 'userAddress1 4', 'userAddress2 4', 'userAddress3 4', 'nickname 4', 'phone_numbe4', 'name 4', NOW(), 'N', 4, 4, 'store_logo_image 4', 'store_introduction 4', 'role 4', 'store_name 4', 4);
INSERT INTO user (id, password, gender, user_address1, user_address2, user_address3, nickname, phone_number, name, reg_date, join_path, book_number, total_point, store_logo_image, store_introduction, role, store_name, withdrawal_status)
     VALUES ('id 5', 'password 5', 'N', 'userAddress1 5', 'userAddress2 5', 'userAddress3 5', 'nickname 5', 'phone_numbe5', 'name 5', NOW(), 'N', 5, 5, 'store_logo_image 5', 'store_introduction 5', 'role 5', 'store_name 5', 5);


/* PRODUCT */
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



/* ORDER */

INSERT INTO `order` (receiver_name, buyer_id, receiver_address1, receiver_address2, receiver_address3, order_status, delivery_charge, using_point, order_date)
     VALUES ('receiver_name 1', 'id 1', 'receiver_address1 1', 'receiver_address2 1', 'receiver_address3 1', 'order_state 1', 1, 1, NOW());
INSERT INTO `order` (receiver_name, buyer_id, receiver_address1, receiver_address2, receiver_address3, order_status, delivery_charge, using_point, order_date)
     VALUES ('receiver_name 2', 'id 2', 'receiver_address1 2', 'receiver_address2 2', 'receiver_address3 2', 'order_state 2', 2, 2, NOW());
INSERT INTO `order` (receiver_name, buyer_id, receiver_address1, receiver_address2, receiver_address3, order_status, delivery_charge, using_point, order_date)
     VALUES ('receiver_name 3', 'id 3', 'receiver_address1 3', 'receiver_address2 3', 'receiver_address3 3', 'order_state 3', 3, 3, NOW());
INSERT INTO `order` (receiver_name, buyer_id, receiver_address1, receiver_address2, receiver_address3, order_status, delivery_charge, using_point, order_date)
     VALUES ('receiver_name 4', 'id 4', 'receiver_address1 4', 'receiver_address2 4', 'receiver_address3 4', 'order_state 4', 4, 4, NOW());
INSERT INTO `order` (receiver_name, buyer_id, receiver_address1, receiver_address2, receiver_address3, order_status, delivery_charge, using_point, order_date)
     VALUES ('receiver_name 5', 'id 5', 'receiver_address1 5', 'receiver_address2 5', 'receiver_address3 5', 'order_state 5', 5, 5, NOW());


/* ORDER PRODUCT */

INSERT INTO order_product (order_number, product_number, product_quantity, product_price, reward_point)
     VALUES ('order_number 1', 1, 1, 1, 1);
INSERT INTO order_product (order_number, product_number, product_quantity, product_price, reward_point)
     VALUES ('order_number 2', 2, 2, 2, 2);
INSERT INTO order_product (order_number, product_number, product_quantity, product_price, reward_point)
     VALUES ('order_number 3', 3, 3, 3, 3);
INSERT INTO order_product (order_number, product_number, product_quantity, product_price, reward_point)
     VALUES ('order_number 4', 4, 4, 4, 4);
INSERT INTO order_product (order_number, product_number, product_quantity, product_price, reward_point)
     VALUES ('order_number 5', 5, 5, 5, 5);