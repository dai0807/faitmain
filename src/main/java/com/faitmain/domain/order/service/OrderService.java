package com.faitmain.domain.order.service;

import com.faitmain.domain.order.domain.Order;
import com.faitmain.domain.order.domain.OrderPageOne;
import com.faitmain.domain.user.domain.User;

import java.util.List;
import java.util.Map;

public interface OrderService{

    /* 주문정보 */
    List<OrderPageOne> getProductInfo( List<OrderPageOne> orderBundle );

    /* 주문자 정보 */
    User getBuyerInfo( int UserNumber );


    /********************************************************/


    //주문추가
    void addOrder( Order order ) throws Exception;

    //주문상태업데이트
    int updateOrder( Order order ) throws Exception;

    //주문확인
    Order getOrder( int OrderNumber ) throws Exception;

    //주문조회
    Map<String, Object> getOrderList() throws Exception;


    //////////// iamport ///////////

    String getToken() throws Exception;

    int paymentInfo( String imp_uid , String access_token );

    public void pamentCancle( String access_token , String imp_uid , String amount , String reason );


}
