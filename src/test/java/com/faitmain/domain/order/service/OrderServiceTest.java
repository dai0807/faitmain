package com.faitmain.domain.order.service;

import com.faitmain.domain.order.domain.Order;
import com.faitmain.domain.order.mapper.OrderMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.transaction.annotation.Transactional;

import static java.sql.JDBCType.NULL;

@Transactional
public class OrderServiceTest{

    //1.
    private OrderMapper orderMapper = Mockito.mock( OrderMapper.class );
    private OrderService orderService;

    @BeforeEach
    public void setUp(){
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
