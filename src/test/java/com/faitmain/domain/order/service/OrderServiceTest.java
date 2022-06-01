package com.faitmain.domain.order.service;

import com.faitmain.domain.order.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class OrderServiceTest{

//    1
//    private final OrderMapper orderMapper = Mockito.mock( OrderMapper.class );
    @Autowired
    private OrderService orderService;

    @BeforeEach
    public void setUp(){
    }

    @Test
    @DisplayName( "전체 조회" )
    @Transactional
    public void getOrderListTest() throws Exception{
        List< Order > orderList = orderService.getOrderList();
        for ( Order order : orderList ) {
            System.out.println( "order = " + order.toString() );
        }
    }

//    @Test
//    public void addOrder(){
//        Order order = new Order( "10001"
//                , "user01@naver.com"
//                , "store03@naver.com"
//                , NULL
//                , NOW()
//                , "1"
//                , "주문접수"
//                , "홍길동"
//                , "01039372812"
//                , "어쩌구 어쩔동 어쩔아파트"
//                , "어쩔티비 저쩔티비"
//                , "NULL"
//                , "NULL"
//                , "카카오페이"
//                , "188887"
//                , "1"
//                , "NULL"
//                , "NOW()"
//                , "NOW()"
//                , "NULL" );
//    }

}
