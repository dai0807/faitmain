package com.faitmain.domain.order.service;

import com.faitmain.domain.order.domain.Order;
import com.faitmain.domain.order.domain.OrderCancle;
import com.faitmain.domain.order.domain.OrderProduct;
import com.faitmain.domain.order.domain.OrderPageProduct;
import com.faitmain.domain.order.mapper.OrderMapper;
import com.faitmain.domain.product.domain.Cart;
import com.faitmain.domain.product.domain.Product;
import com.faitmain.domain.product.mapper.CartMapper;
import com.faitmain.domain.product.mapper.ProductMapper;
import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.web.domain.AttachImage;
import com.faitmain.domain.web.mapper.AttachMapper;
import com.faitmain.global.common.Criterion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    AttachMapper attachMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CartMapper cartMapper;


    /* 주문자 주소 정보 */
    @Override
    public User getBuyerInfo( String id ){
        return orderMapper.getBuyerInfo( id );
    }

    /* 주문정보 */
    @Override
    public List<OrderPageProduct> getProductInfo( List<OrderPageProduct> orderPageBundle ){

        List<OrderPageProduct> result = new ArrayList<>();
        for ( OrderPageProduct orderPageProduct : orderPageBundle ) {

            OrderPageProduct opp = orderMapper.getProductInfo( orderPageProduct.getProductNumber() );
            opp.setProducOrderCount( orderPageProduct.getProducOrderCount() );
            opp.initSaleTotal();

            List<AttachImage> imageList = attachMapper.getAttachList( opp.getProductNumber() );
            opp.setImageList( imageList );

            result.add( opp );
        }

        return result;
    }

    /* 주문 */
    @Override
    @Transactional
    public void order( Order order ) throws Exception{

        /* 사용할 데이터 가져오기 */
        /* 회원정보 */
        User user = orderMapper.getBuyerInfo( order.getBuyerId() );
        /* 주문정보 */
        List<OrderProduct> orderBundle = new ArrayList<>();
        for ( OrderProduct op : order.getOrderBundle() ) {
            OrderProduct orderProduct = orderMapper.getOrderInfo( op.getProductNumber() );
            /* 수량세팅 */
            orderProduct.setProductOrderCount( op.getProductOrderCount() );
            /* 기본정보 세팅 */
            orderProduct.initSaleTotal();
            /* LIST 객체 추가 */
            orderBundle.add( orderProduct );
        }

        /* ORDER 세팅 */
        order.setOrderBundle( orderBundle );
        order.getOrderPriceInfo();

        /* DB 주문,주문상품(,배송정보) 넣기*/

        /* ORDERNUMBER 만들기 및 ORDER 객체 저장 */
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat( "_yyyyMMdd" );
        int orderNumber = Integer.parseInt( user.getUserNumber() + format.format( date ) );
        order.setOrderNumber( orderNumber );

        /* DB 넣기 */
        /* ORDER 등록 */
        orderMapper.enrollOrder( order );
        /* ORDER PRODUCT 등록 */
        for ( OrderProduct orderProduct : order.getOrderBundle() ) {
            orderProduct.setOrderNumber( orderNumber );
        }

        /* 포인트 변동 적용 */
        int calTotalPoint = user.getTotalPoint();
        calTotalPoint = calTotalPoint - order.getUsingPoint() + order.getOrderRewardPoint();
        user.setTotalPoint( calTotalPoint );

        /* 포인트 DB 적용 */
        orderMapper.deductPoint( user );

        /* 재고 변동 적용 */
        for ( OrderProduct orderProduct : order.getOrderBundle() ) {
            /* 변동 재고 값 구하기 */
            Product product = productMapper.getProduct( orderProduct.getProductNumber() );
            product.setProductQuantity( product.getProductQuantity() - orderProduct.getProductOrderCount() );
            /* 변동 값 DB 적용 */
            orderMapper.deductStock( product );
        }
        /* 장바구니 제거 */
        for ( OrderProduct orderProduct : order.getOrderBundle() ) {
            Cart cart = new Cart();
            cart.setUserId( order.getBuyerId() );
            cart.setProductNumber( orderProduct.getProductNumber() );

            cartMapper.deleteOrderCart( cart );
        }
    }

    /* 주문 상품 리스트 */
    @Override
    public List<Order> getOrderList( Criterion criterion ){
        return orderMapper.getOrderList( criterion );
    }

    /* 주문 총 개수 */
    @Override
    public int getOrderTotal( Criterion criterion ){
        return orderMapper.getOrderTotal( criterion );
    }

    /* 주문 취소 */
    @Override
    @Transactional
    public void orderCancle( OrderCancle orderCancle ) throws Exception{
        /* 주문 & 주문상품 객체 */
        /* 회원 */
        User user = orderMapper.getBuyerInfo( orderCancle.getBuyerId() );
        /* 주문상품 */
        List<OrderProduct> orderBundle = orderMapper.getOrderProductInfo( orderCancle.getOrderNumber() );
        for ( OrderProduct orderProduct : orderBundle ) {
            orderProduct.initSaleTotal();
        }
        /* 주문 */
        Order order = orderMapper.getOrder( orderCancle.getOrderNumber() );
        order.setOrderBundle( orderBundle );
        order.getOrderPriceInfo();

        /* 주문상품 취소 DB */
        orderMapper.orderCancle( order.getOrderNumber() );

        /* 포인트 & 재고 변환 */
        /* 포인트 */
        int calTotalPoint = user.getTotalPoint();
        calTotalPoint = calTotalPoint - order.getUsingPoint() + order.getOrderRewardPoint();
        user.setTotalPoint( calTotalPoint );

        /* DB 적용 */
        orderMapper.deductPoint( user );

        /* 재고 */
        for ( OrderProduct orderProduct : order.getOrderBundle() ) {
            Product product = productMapper.getProduct( orderProduct.getProductNumber() );
            product.setProductQuantity( product.getProductQuantity() + orderProduct.getProductOrderCount() );
            orderMapper.deductStock( product );
        }
    }




}
