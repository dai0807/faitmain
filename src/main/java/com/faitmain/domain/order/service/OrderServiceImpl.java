package com.faitmain.domain.order.service;

import com.faitmain.domain.cart.domain.Cart;
import com.faitmain.domain.cart.mapper.CartMapper;
import com.faitmain.domain.order.domain.Order;
import com.faitmain.domain.order.domain.OrderCancel;
import com.faitmain.domain.order.domain.OrderPageProduct;
import com.faitmain.domain.order.domain.OrderProduct;
import com.faitmain.domain.order.mapper.OrderMapper;
import com.faitmain.domain.product.domain.Product;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ProductMapper productMapper;

    @Autowired
    CartMapper cartMapper;


    /* 주문자 주소 정보 */
    @Override
    public User getBuyer( String buyerId ){
        return orderMapper.selectBuyer( buyerId );
    }

    /* 주문정보 */
    @Override
    public List<OrderPageProduct> getOrderPageProductList( List<OrderPageProduct> orderPageProductList ){

        List<OrderPageProduct> oppList = new ArrayList<>();
        for ( OrderPageProduct orderPageProduct : orderPageProductList ) {
            OrderPageProduct opp = orderMapper.selectOrderPageProduct( orderPageProduct.getProductNumber() );
            opp.setProductOrderCount( orderPageProduct.getProductOrderCount() );
            opp.setProductMainImage( orderPageProduct.getProductMainImage() );
            opp.initSaleTotal();
            oppList.add( opp );
        }
        return oppList;
    }

    /* 주문 */
    @Override
    @Transactional
    public void addOrder( Order order ) throws Exception{
        /* 사용할 데이터 가져오기 */

        /* 회원정보 */
        User user = orderMapper.selectBuyer( order.getBuyerId() );

        /* 주문정보 */
        List<OrderProduct> orderProductList = new ArrayList<>();
        for ( OrderProduct orderProduct : order.getOrderProductList() ) {
            OrderProduct op = orderMapper.selectOrderProduct( orderProduct.getProductNumber() );
            /* 수량세팅 */
            op.setProductOrderCount( orderProduct.getProductOrderCount() );
            /* 기본정보 세팅 */
            op.initSaleTotal();
            /* LIST 객체 추가 */
            orderProductList.add( op );
        }

        /* ORDER 세팅 */
        order.setOrderProductList( orderProductList );
        order.getOrderPriceInfo();

        /* DB 주문,주문상품(,배송정보) 넣기*/

        /* ORDERNUMBER 만들기 및 ORDER 객체 저장 */
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat( "_yyyyMMdd" );
        int orderNumber = Integer.parseInt( user.getUserNumber() + format.format( date ) );
        order.setOrderNumber( orderNumber );

        /* DB 넣기 */
        /* ORDER 등록 */
        orderMapper.insertOrder( order );
        /* ORDER PRODUCT 등록 */
        for ( OrderProduct orderProduct : order.getOrderProductList() ) {
            orderProduct.setOrderNumber( orderNumber );
        }

        /* 포인트 변동 적용 */
        int calTotalPoint = user.getTotalPoint();
        calTotalPoint = calTotalPoint - order.getUsingPoint() + order.getOrderRewardPoint();
        user.setTotalPoint( calTotalPoint );

        /* 포인트 DB 적용 */
        orderMapper.updatePoint( user );

        /* 재고 변동 적용 */
        for ( OrderProduct orderProduct : order.getOrderProductList() ) {
            /* 변동 재고 값 구하기 */
            Product product = productMapper.getProduct( orderProduct.getProductNumber() );
            product.setProductQuantity( product.getProductQuantity() - orderProduct.getProductOrderCount() );
            /* 변동 값 DB 적용 */
            orderMapper.updateStock( product );
        }
        /* 장바구니 제거 */
        for ( OrderProduct orderProduct : order.getOrderProductList() ) {
            Cart cart = new Cart();
            cart.setBuyerId( order.getBuyerId() );
            cart.setProductNumber( orderProduct.getProductNumber() );

            cartMapper.deleteCart( cart.getCartNumber() );

        }
    }

    /* 주문 상품 리스트 */
    @Override
    public List<Order> getOrderList( Criterion criterion ){
        return orderMapper.selectOrderList( criterion );
    }

    /* 주문 총 개수 */
    @Override
    public int getOrderTotal( Criterion criterion ){
        return orderMapper.selectOrderTotal( criterion );
    }

    /* 주문 취소 */
    @Override
    @Transactional
    public void cancelOrder( OrderCancel orderCancel ) throws Exception{
        /* 주문 & 주문상품 객체 */

        /* 회원 */
        User user = orderMapper.selectBuyer( orderCancel.getBuyerId() );
        /* 주문상품 */
        List<OrderProduct> orderProductList = orderMapper.selectOrderProductList( orderCancel.getOrderNumber() );
        for ( OrderProduct orderProduct : orderProductList ) {
            orderProduct.initSaleTotal();
        }
        /* 주문 */
        Order order = orderMapper.selectOrder( orderCancel.getOrderNumber() );
        order.setOrderProductList( orderProductList );
        order.getOrderPriceInfo();

        /* 주문상품 취소 DB */
        orderMapper.deleteOrder( order.getOrderNumber() );

        /* 포인트 & 재고 변환 */

        /* 포인트 */
        int calTotalPoint = user.getTotalPoint();
        calTotalPoint = calTotalPoint - order.getUsingPoint() + order.getOrderRewardPoint();
        user.setTotalPoint( calTotalPoint );

        /* DB 적용 */
        orderMapper.updatePoint( user );

        /* 재고 */
        for ( OrderProduct orderProduct : order.getOrderProductList() ) {
            Product product = productMapper.getProduct( orderProduct.getProductNumber() );
            product.setProductQuantity( product.getProductQuantity() + orderProduct.getProductOrderCount() );
            orderMapper.updateStock( product );
        }
    }


}
