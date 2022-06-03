package com.faitmain.domain.order.mapper;

import com.faitmain.domain.order.domain.Order;
import groovy.util.logging.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith( SpringExtension.class )
@SpringBootTest
@Log4j2
class OrderMappperTest{

    @Autowired
    private OrderMapper orderMapper;

    @Test
    @DisplayName( "주문등록 테스트" )
    public void addOrderTest(){

        System.out.println( "addOrderTest Start" );

        Order order = new Order();

        order.setProductNumber( 10001 );
        order.setBuyerId( "user01@naver.com" );
        order.setStoreId( "store03@naver.com" );
        order.setOrderBundleNumber( new Timestamp( new java.util.Date().getTime() ) );
        order.setOrderDate( new Timestamp( new java.util.Date().getTime() ) );
        order.setOrderQuantity( 1 );
        order.setOrderStatus( "주문접수" );
        order.setReceiverName( "홍길동" );
        order.setReceiverPhone( "01039372812" );
        order.setReceiverAddress( "어쩌구 어쩔동 어쩔아파트" );
        order.setReceiverRequest( "어쩔티비 저쩔티비" );
        order.setDeliveryTrackingNumber( 1 );
        order.setDeliveryCompanyCode( "ABCDE" );
        order.setPaymentOption( "카카오페이" );
        order.setTotalPaymentPrice( 12345 );
        order.setRewardPoint( 1 );
        order.setUsingPoint( 0 );
        order.setOrderClaimRequestDate( new Date( new java.util.Date().getTime() ) );
        order.setOrderClaimResponseDate( new Date( new java.util.Date().getTime() ) );
        order.setOrderClaimReason( 0 );


        System.out.println( "result = " + orderMapper.addOrder( order ) );
        System.out.println( "orderNumber = " + order.getOrderNumber() );

        order = orderMapper.getOrder( order.getOrderNumber() );
        System.out.println( "order = " + order );

        assertThat( order.getProductNumber() ).isEqualTo( 10001 );
        assertThat( order.getBuyerId() ).isEqualTo( "user01@naver.com" );
        assertThat( order.getStoreId() ).isEqualTo( "store03@naver.com" );
//        assertThat( order.getOrderBundleNumber() ).isEqualTo( new Timestamp( new java.util.Date().getTime() ) );
//        assertThat( order.getOrderDate() ).isEqualTo( new Timestamp( new java.util.Date().getTime() ) );
        assertThat( order.getOrderQuantity() ).isEqualTo( 1 );
        assertThat( order.getOrderStatus() ).isEqualTo( "주문접수" );
        assertThat( order.getReceiverName() ).isEqualTo( "홍길동" );
        assertThat( order.getReceiverPhone() ).isEqualTo( "01039372812" );
        assertThat( order.getReceiverAddress() ).isEqualTo( "어쩌구 어쩔동 어쩔아파트" );
        assertThat( order.getReceiverRequest() ).isEqualTo( "어쩔티비 저쩔티비" );
        assertThat( order.getDeliveryTrackingNumber() ).isEqualTo( 1 );
        assertThat( order.getDeliveryCompanyCode() ).isEqualTo( "ABCDE" );
        assertThat( order.getPaymentOption() ).isEqualTo( "카카오페이" );
        assertThat( order.getTotalPaymentPrice() ).isEqualTo( 12345 );
        assertThat( order.getRewardPoint() ).isEqualTo( 1 );
        assertThat( order.getUsingPoint() ).isEqualTo( 0 );
//        assertThat( order.getOrderClaimRequestDate() ).isEqualTo( "2022-06-02" );
//        assertThat( order.getOrderClaimResponseDate() ).isEqualTo( "2022-06-02" );
        assertThat( order.getOrderClaimReason() ).isEqualTo( 0 );
    }

    @Test
    @DisplayName( "주문상태 업데이트 테스트" )
    public void updateOrderTest(){

        System.out.println( "updateOrderTest Start" );

        //given
        Order order = new Order();

        order.setOrderNumber( 10001 );
        order.setOrderStatus( "주문접수" );
        order.setReceiverName( "홍길동" );
        order.setReceiverPhone( "01039372812" );
        order.setReceiverAddress( "어쩌구 어쩔동 어쩔아파트" );
        order.setReceiverRequest( "어쩔티비 저쩔티비" );
        order.setDeliveryTrackingNumber( 1 );
        order.setDeliveryCompanyCode( "ABCDE" );
        order.setOrderClaimReason( 0 );

        //when
        System.out.println( "result = " + orderMapper.updateOrder( order ) );
        System.out.println( "orderNumber = " + order.getOrderNumber() );

        order = orderMapper.getOrder( order.getOrderNumber() );
        System.out.println( "order = " + order );

        //then
        assertThat( order.getOrderStatus() ).isEqualTo( "주문접수" );
        assertThat( order.getReceiverName() ).isEqualTo( "홍길동" );
        assertThat( order.getReceiverPhone() ).isEqualTo( "01039372812" );
        assertThat( order.getReceiverAddress() ).isEqualTo( "어쩌구 어쩔동 어쩔아파트" );
        assertThat( order.getReceiverRequest() ).isEqualTo( "어쩔티비 저쩔티비" );
        assertThat( order.getDeliveryTrackingNumber() ).isEqualTo( 1 );
        assertThat( order.getDeliveryCompanyCode() ).isEqualTo( "ABCDE" );
        assertThat( order.getOrderClaimReason() ).isEqualTo( 0 );
    }

    @Test
    @DisplayName( " 주문조회 테스트" )
    public void getOrderTest(){

        //given
        int orderNumber = 10001;

        //when
        Order order = orderMapper.getOrder( 10001 );
        System.out.println( "order = " + order );

        //then
        assertThat( order.getOrderNumber() ).isEqualTo( 10001 );
    }

    @Test
    @DisplayName( "전체 주문조회 테스트" )
    public void getOrderListTest(){

        List< Order > orderList = orderMapper.getOrderList();
        for ( Order order : orderList ) {
            System.out.println( "order = " + order );
        }

    }



}
