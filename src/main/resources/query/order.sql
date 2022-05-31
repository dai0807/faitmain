-- 테이블 복사/백업
CREATE TABLE order_20220530_162529
SELECT *
  FROM order;

-- 백업 테이블에서 원본 테이블로 데이터 복사(Auto Increment 컬럼 제외)
INSERT INTO order ( product_number
                  , buyer_id
                  , store_id
                  , order_bundle_number
                  , order_date
                  , order_quantity
                  , order_status
                  , receiver_name
                  , receiver_phone
                  , receiver_address
                  , receiver_request
                  , delivery_tracking_number
                  , delivery_company_code
                  , payment_option
                  , total_payment_price
                  , reward_point
                  , using_point
                  , order_claim_request_date
                  , order_claim_response_date
                  , order_claim_reason
                  )
SELECT product_number
     , buyer_id
     , store_id
     , order_bundle_number
     , order_date
     , order_quantity
     , order_status
     , receiver_name
     , receiver_phone
     , receiver_address
     , receiver_request
     , delivery_tracking_number
     , delivery_company_code
     , payment_option
     , total_payment_price
     , reward_point
     , using_point
     , order_claim_request_date
     , order_claim_response_date
     , order_claim_reason
  FROM order_20220530_162529;


-- Select - 기본적인 Select

-- 테이블 ROW 갯수 확인
SELECT COUNT( * )
  FROM order;

-- 조건에 맞는 ROW 조회
SELECT order_number
     , product_number
     , buyer_id
     , store_id
     , order_bundle_number
     , order_date
     , order_quantity
     , order_status
     , receiver_name
     , receiver_phone
     , receiver_address
     , receiver_request
     , delivery_tracking_number
     , delivery_company_code
     , payment_option
     , total_payment_price
     , reward_point
     , using_point
     , order_claim_request_date
     , order_claim_response_date
     , order_claim_reason
  FROM order
 WHERE order_number = 1;

-- 모든 컬럼 PK기준 역순으로 조회
SELECT order_number
     , product_number
     , buyer_id
     , store_id
     , order_bundle_number
     , order_date
     , order_quantity
     , order_status
     , receiver_name
     , receiver_phone
     , receiver_address
     , receiver_request
     , delivery_tracking_number
     , delivery_company_code
     , payment_option
     , total_payment_price
     , reward_point
     , using_point
     , order_claim_request_date
     , order_claim_response_date
     , order_claim_reason
  FROM order
 ORDER BY order_number DESC;

-- 모든 컬럼 100건 조회
SELECT order_number
     , product_number
     , buyer_id
     , store_id
     , order_bundle_number
     , order_date
     , order_quantity
     , order_status
     , receiver_name
     , receiver_phone
     , receiver_address
     , receiver_request
     , delivery_tracking_number
     , delivery_company_code
     , payment_option
     , total_payment_price
     , reward_point
     , using_point
     , order_claim_request_date
     , order_claim_response_date
     , order_claim_reason
  FROM order
 LIMIT 100;

-- SELECT 값에 따라 수정된 다른 값을 출력
SELECT order_number
     , CASE
           WHEN product_number = 1 THEN product_number + 10
           WHEN product_number = 2 THEN product_number + 20
           WHEN product_number = 3 THEN product_number + 30
           ELSE product_number + 40 END AS product_numbertype
     , buyer_id
     , store_id
     , order_bundle_number
     , order_date
     , order_quantity
     , order_status
     , receiver_name
     , receiver_phone
     , receiver_address
     , receiver_request
     , delivery_tracking_number
     , delivery_company_code
     , payment_option
     , total_payment_price
     , reward_point
     , using_point
     , order_claim_request_date
     , order_claim_response_date
     , order_claim_reason
  FROM order;

-- 특정 컬럼 중복 제거
SELECT DISTINCT order_status
  FROM order;

-- Group By 문과 Having문을 이용하여 Select
SELECT order_status, AVG( product_number )
  FROM order
 GROUP BY order_status
HAVING AVG( product_number ) > 50;

-- 날짜 범위에 해당하는 ROW를 조회
SELECT order_number
     , product_number
     , buyer_id
     , store_id
     , order_bundle_number
     , order_date
     , order_quantity
     , order_status
     , receiver_name
     , receiver_phone
     , receiver_address
     , receiver_request
     , delivery_tracking_number
     , delivery_company_code
     , payment_option
     , total_payment_price
     , reward_point
     , using_point
     , order_claim_request_date
     , order_claim_response_date
     , order_claim_reason
  FROM order
 WHERE order_date BETWEEN '2015-10-01 23:59:59' AND '2015-11-30 23:59:59';

-- 날짜별 통계
SELECT DATE_FORMAT( order_date , '%Y-%m-%d' ) AS order_date, COUNT( * ) AS count
  FROM order
 GROUP BY DATE_FORMAT( order_date , '%Y-%m-%d' )
 ORDER BY DATE_FORMAT( order_date , '%Y-%m-%d' ) DESC;


-- Paging Select

-- Paging을 이용한 Select - 역순정렬
SELECT order_number
     , product_number
     , buyer_id
     , store_id
     , order_bundle_number
     , order_date
     , order_quantity
     , order_status
     , receiver_name
     , receiver_phone
     , receiver_address
     , receiver_request
     , delivery_tracking_number
     , delivery_company_code
     , payment_option
     , total_payment_price
     , reward_point
     , using_point
     , order_claim_request_date
     , order_claim_response_date
     , order_claim_reason
  FROM order
 ORDER BY order_number DESC
 LIMIT 0, 10;
-- LIMIT [(PageNumber - 1) * RowsPerPage], [RowsPerPage]
-- RowsPerPage	: 한 페이지에 가져올 Row 수
-- PageNumber	: 가져올 페이지 번호


-- Inner Join을 이용한 조회
SELECT o.order_number
     , o.product_number
     , o.buyer_id
     , o.store_id
     , o.order_bundle_number
     , o.order_date
     , o.order_quantity
     , o.order_status
     , o.receiver_name
     , o.receiver_phone
     , o.receiver_address
     , o.receiver_request
     , o.delivery_tracking_number
     , o.delivery_company_code
     , o.payment_option
     , o.total_payment_price
     , o.reward_point
     , o.using_point
     , o.order_claim_request_date
     , o.order_claim_response_date
     , o.order_claim_reason
     , u.id
     , u.password
     , u.gender
     , u.address
     , u.postal_code
     , u.nickname
     , u.phone_number
     , u.name
     , u.reg_date
     , u.join_path
     , u.book_number
     , u.total_point
     , u.store_logo_image
     , u.store_introduction
     , u.bank_account_copy_image
     , u.bank_name
     , u.role
     , u.store_name
     , u.withdrawal_status
  FROM order           o
       INNER JOIN user u ON o.buyer_id = u.id AND o.store_id = u.id
 WHERE o.order_number = 1
 LIMIT 100;

-- Outer Join을 이용한 조회
SELECT o.order_number
     , o.product_number
     , o.buyer_id
     , o.store_id
     , o.order_bundle_number
     , o.order_date
     , o.order_quantity
     , o.order_status
     , o.receiver_name
     , o.receiver_phone
     , o.receiver_address
     , o.receiver_request
     , o.delivery_tracking_number
     , o.delivery_company_code
     , o.payment_option
     , o.total_payment_price
     , o.reward_point
     , o.using_point
     , o.order_claim_request_date
     , o.order_claim_response_date
     , o.order_claim_reason
     , u.id
     , u.password
     , u.gender
     , u.address
     , u.postal_code
     , u.nickname
     , u.phone_number
     , u.name
     , u.reg_date
     , u.join_path
     , u.book_number
     , u.total_point
     , u.store_logo_image
     , u.store_introduction
     , u.bank_account_copy_image
     , u.bank_name
     , u.role
     , u.store_name
     , u.withdrawal_status
  FROM order                o
       LEFT OUTER JOIN user u ON o.buyer_id = u.id AND o.store_id = u.id
 WHERE o.order_number = 1
 LIMIT 100;

-- 해당 테이블을 참조하는 테이블의 최신 Row만 조회
SELECT o.order_number
     , o.product_number
     , o.buyer_id
     , o.store_id
     , o.order_bundle_number
     , o.order_date
     , o.order_quantity
     , o.order_status
     , o.receiver_name
     , o.receiver_phone
     , o.receiver_address
     , o.receiver_request
     , o.delivery_tracking_number
     , o.delivery_company_code
     , o.payment_option
     , o.total_payment_price
     , o.reward_point
     , o.using_point
     , o.order_claim_request_date
     , o.order_claim_response_date
     , o.order_claim_reason
     , u.id
     , u.password
     , u.gender
     , u.address
     , u.postal_code
     , u.new_tablecol
     , u.nickname
     , u.phone_number
     , u.name
     , u.regdate
     , u.join_path
     , u.book_number
     , u.total_point
     , u.store_logo_image
     , u.store_introduction
     , u.bank_account_copy_image
     , u.bank_name
     , u.role
     , u.store_name
     , u.withdrawal_status
  FROM order o

 LIMIT 100;

-- order 테이블을 참조하는 테이블의 Row 수 구하기
SELECT o.order_number
     , o.product_number
     , o.buyer_id
     , o.store_id
     , o.order_bundle_number
     , o.order_date
     , o.order_quantity
     , o.order_status
     , o.receiver_name
     , o.receiver_phone
     , o.receiver_address
     , o.receiver_request
     , o.delivery_tracking_number
     , o.delivery_company_code
     , o.payment_option
     , o.total_payment_price
     , o.reward_point
     , o.using_point
     , o.order_claim_request_date
     , o.order_claim_response_date
     , o.order_claim_reason
     , u.id
     , u.password
     , u.gender
     , u.address
     , u.postal_code
     , u.new_tablecol
     , u.nickname
     , u.phone_number
     , u.name
     , u.regdate
     , u.join_path
     , u.book_number
     , u.total_point
     , u.store_logo_image
     , u.store_introduction
     , u.bank_account_copy_image
     , u.bank_name
     , u.role
     , u.store_name
     , u.withdrawal_status
     , _count.row_count
  FROM order o

 ORDER BY o.order_number DESC;

-- Insert
INSERT INTO order ( product_number
                  , buyer_id
                  , store_id
                  , order_bundle_number
                  , order_date
                  , order_quantity
                  , order_status
                  , receiver_name
                  , receiver_phone
                  , receiver_address
                  , receiver_request
                  , delivery_tracking_number
                  , delivery_company_code
                  , payment_option
                  , total_payment_price
                  , reward_point
                  , using_point
                  , order_claim_request_date
                  , order_claim_response_date
                  , order_claim_reason
                  )
VALUES ( 1
       , 'buyer_id 1'
       , 'store_id 1'
       , 1
       , NOW( )
       , 1
       , 'order_status 1'
       , 'receiver_name 1'
       , 1
       , 'receiver_address 1'
       , 'receiver_request 1'
       , 1
       , 'delivery_company_code 1'
       , 'payment_option 1'
       , 1
       , 1
       , 1
       , NOW( )
       , NOW( )
       , 1
       );

-- Insert된 Row의 Auto Increment값 조회
SELECT LAST_INSERT_ID( );

-- Update
UPDATE order
   SET product_number            = 1
     , buyer_id                  = 'buyer_id 1'
     , store_id                  = 'store_id 1'
     , order_bundle_number       = 1
     , order_date                = NOW( )
     , order_quantity            = 1
     , order_status              = 'order_status 1'
     , receiver_name             = 'receiver_name 1'
     , receiver_phone            = 1
     , receiver_address          = 'receiver_address 1'
     , receiver_request          = 'receiver_request 1'
     , delivery_tracking_number  = 1
     , delivery_company_code     = 'delivery_company_code 1'
     , payment_option            = 'payment_option 1'
     , total_payment_price       = 1
     , reward_point              = 1
     , using_point               = 1
     , order_claim_request_date  = NOW( )
     , order_claim_response_date = NOW( )
     , order_claim_reason        = 1
 WHERE order_number = 1;

-- Delete
DELETE
  FROM order
 WHERE order_number = 1;

-- 1일이 지난 데이터 삭제
DELETE
  FROM order
 WHERE order_date < DATE_SUB( NOW( ) , INTERVAL 1 DAY );

-- 해당 PK가 없으면 Insert 있으면 Update
INSERT INTO order( order_number
                 , product_number
                 , buyer_id
                 , store_id
                 , order_bundle_number
                 , order_date
                 , order_quantity
                 , order_status
                 , receiver_name
                 , receiver_phone
                 , receiver_address
                 , receiver_request
                 , delivery_tracking_number
                 , delivery_company_code
                 , payment_option
                 , total_payment_price
                 , reward_point
                 , using_point
                 , order_claim_request_date
                 , order_claim_response_date
                 , order_claim_reason
                 )
VALUES ( 1
       , 1
       , 'buyer_id 1'
       , 'store_id 1'
       , 1
       , NOW( )
       , 1
       , 'order_status 1'
       , 'receiver_name 1'
       , 1
       , 'receiver_address 1'
       , 'receiver_request 1'
       , 1
       , 'delivery_company_code 1'
       , 'payment_option 1'
       , 1
       , 1
       , 1
       , NOW( )
       , NOW( )
       , 1
       )
    ON DUPLICATE KEY UPDATE product_number = 1
     , buyer_id                            = 'buyer_id 1'
     , store_id                            = 'store_id 1'
     , order_bundle_number                 = 1
     , order_date                          = NOW( )
     , order_quantity                      = 1
     , order_status                        = 'order_status 1'
     , receiver_name                       = 'receiver_name 1'
     , receiver_phone                      = 1
     , receiver_address                    = 'receiver_address 1'
     , receiver_request                    = 'receiver_request 1'
     , delivery_tracking_number            = 1
     , delivery_company_code               = 'delivery_company_code 1'
     , payment_option                      = 'payment_option 1'
     , total_payment_price                 = 1
     , reward_point                        = 1
     , using_point                         = 1
     , order_claim_request_date            = NOW( )
     , order_claim_response_date           = NOW( )
     , order_claim_reason                  = 1;


--
