package com.faitmain.domain.order.service;

import java.sql.Date;
import java.sql.Timestamp;


import com.faitmain.domain.order.domain.Order;
import com.faitmain.domain.order.mapper.OrderMapper;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@AutoConfigureWebMvc
public class OrderServiceTest{

    @Autowired
    OrderMapper orderMapper;

    @BeforeEach
    public void init(){


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
