package com.faitmain.domain.order.mapper;

import com.faitmain.domain.order.domain.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith( SpringExtension.class)
@SpringBootTest
class OrderMappperTest{

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void addOrderTest(){

        System.out.println("addOrderTest Start");

        Order order = new Order();

        order.setProductNumber( 10001 );
        order.setBuyerId( "user01@naver.com" );
        order.setStoreId( "stroe03@naver.com" );
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

        int result = orderMapper.addOrder( order );
        System.out.println("result = " + result);

        order = orderMapper.getOrder( order.getOrderNumber() );
        System.out.println(order);

        assertThat( order.getProductNumber() ).isEqualTo( 10001 );
        assertThat( order.getBuyerId() ).isEqualTo( "user01@naver.com" );
        assertThat( order.getStoreId() ).isEqualTo( "stroe03@naver.com" );
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

        System.out.println( order );

    }

}
