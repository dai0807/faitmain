package com.faitmain.domain.order.mapper;

import com.faitmain.domain.order.domain.Order;
import com.faitmain.domain.order.domain.OrderOne;
import com.faitmain.domain.order.domain.OrderPageOne;
import com.faitmain.domain.product.domain.Product;
import com.faitmain.domain.user.domain.User;
import com.faitmain.global.common.Search;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderMapper{


    /* 주문자 주소 정보 */
    User getBuyerInfo( int userNumber );

    /* 주문 상품 정보 (주문 페이지) */
    OrderPageOne getProductInfo( int productNumber );

    /* 주문 상품 정보 (주문 처리) */
    OrderOne getOrderInfo( int productNumber );

    /* 주문 테이블 등록 */
    int enrollOrder( Order order );

    /* 주문 상품 테이블 등록 */
    int enrollOrderOne( OrderOne orderOne );

    /* 주문 금액 차감 */
    int deductMoney( User user );

    /* 주문 재고 차감 */
    int deductStock( Product product );


    /**************************************************************************/


    //insert
    int addOrder( Order order );

    //update
    int updateOrder( Order order );

    //selectOne
    Order getOrder( int orderNumber );

    //selectList
    List<Order> getOrderList();

    //paging
    int getTotalCount( Search search );

    List<Order> findAllByBuyer( User buyerId );

    Optional<Order> findByOrderNumberAndBuyer( int OrderNumber , User buyerId );

}
