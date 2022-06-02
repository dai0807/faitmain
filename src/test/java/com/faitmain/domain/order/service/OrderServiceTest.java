package com.faitmain.domain.order.service;

import java.sql.Date;
import java.sql.Timestamp;


import com.faitmain.domain.order.domain.Order;
import com.faitmain.domain.order.mapper.OrderMapper;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@AutoConfigureMockMvc
public class OrderServiceTest{

    //    1
    private final OrderMapper orderMapper = Mockito.mock( OrderMapper.class );

    @BeforeEach
    public void setUp(){


    }

//    @Test
//    @DisplayName( "전체 조회" )
//    @Transactional
//    public void getOrderListTest() throws Exception{
//        List< Order > orderList = orderService.getOrderList();
//        for ( Order order : orderList ) {
//            System.out.println( "order = " + order.toString() );
//        }
//    }

    @Test
    public void addOrderTest(){

//        log.info( "addOrderTest" );

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
        order.setDeliveryTrackingNumber( 0 );
        order.setDeliveryCompanyCode( null );
        order.setPaymentOption( "카카오페이" );
        order.setTotalPaymentPrice( 12345 );
        order.setRewardPoint( 1 );
        order.setUsingPoint( 0 );
        order.setOrderClaimRequestDate( new Date( new java.util.Date().getTime() ) );
        order.setOrderClaimResponseDate( new Date( new java.util.Date().getTime() ) );
        order.setOrderClaimReason( 0 );

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
        assertThat( order.getDeliveryTrackingNumber() ).isEqualTo( 0 );
        assertThat( order.getDeliveryCompanyCode() ).isEqualTo( null );
        assertThat( order.getPaymentOption() ).isEqualTo( "카카오페이" );
        assertThat( order.getTotalPaymentPrice() ).isEqualTo( 12345 );
        assertThat( order.getRewardPoint() ).isEqualTo( 1 );
        assertThat( order.getUsingPoint() ).isEqualTo( 0 );
//        assertThat( order.getOrderClaimRequestDate() ).isEqualTo( "2022-06-02" );
//        assertThat( order.getOrderClaimResponseDate() ).isEqualTo( "2022-06-02" );
        assertThat( order.getOrderClaimReason() ).isEqualTo( 0 );

    }

//    @Test
//    public void update(){
//
//        Order order = new Order();
//
//        order.setOrderNumber(0);
//        order.setProductNumber(0);
//        order.setBuyerId("");
//        order.setStoreId("");
//        order.setOrderBundleNumber(new Timestamp(new java.util.Date().getTime()));
//        order.setOrderDate(new Timestamp(new java.util.Date().getTime()));
//        order.setOrderQuantity(0);
//        order.setOrderStatus("");
//        order.setReceiverName("");
//        order.setReceiverPhone("");
//        order.setReceiverAddress("");
//        order.setReceiverRequest("");
//        order.setDeliveryTrackingNumber(0);
//        order.setDeliveryCompanyCode("");
//        order.setPaymentOption("");
//        order.setTotalPaymentPrice(0);
//        order.setRewardPoint(0);
//        order.setUsingPoint(0);
//        order.setOrderClaimRequestDate(new Date(new java.util.Date().getTime()));
//        order.setOrderClaimResponseDate(new Date(new java.util.Date().getTime()));
//        order.setOrderClaimReason(0);
//
//        assertThat(order.getOrderNumber()).isEqualTo();
//        assertThat(order.getProductNumber()).isEqualTo();
//        assertThat(order.getBuyerId()).isEqualTo();
//        assertThat(order.getStoreId()).isEqualTo();
//        assertThat(order.getOrderBundleNumber()).isEqualTo();
//        assertThat(order.getOrderDate()).isEqualTo();
//        assertThat(order.getOrderQuantity()).isEqualTo();
//        assertThat(order.getOrderStatus()).isEqualTo();
//        assertThat(order.getReceiverName()).isEqualTo();
//        assertThat(order.getReceiverPhone()).isEqualTo();
//        assertThat(order.getReceiverAddress()).isEqualTo();
//        assertThat(order.getReceiverRequest()).isEqualTo();
//        assertThat(order.getDeliveryTrackingNumber()).isEqualTo();
//        assertThat(order.getDeliveryCompanyCode()).isEqualTo();
//        assertThat(order.getPaymentOption()).isEqualTo();
//        assertThat(order.getTotalPaymentPrice()).isEqualTo();
//        assertThat(order.getRewardPoint()).isEqualTo();
//        assertThat(order.getUsingPoint()).isEqualTo();
//        assertThat(order.getOrderClaimRequestDate()).isEqualTo();
//        assertThat(order.getOrderClaimResponseDate()).isEqualTo();
//        assertThat(order.getOrderClaimReason()).isEqualTo();
//
//    }

}
