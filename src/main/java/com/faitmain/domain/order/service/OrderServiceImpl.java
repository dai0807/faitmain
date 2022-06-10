package com.faitmain.domain.order.service;

import com.faitmain.domain.order.domain.Order;
import com.faitmain.domain.order.domain.OrderCancle;
import com.faitmain.domain.order.domain.OrderOne;
import com.faitmain.domain.order.domain.OrderPageOne;
import com.faitmain.domain.order.mapper.OrderMapper;
import com.faitmain.domain.product.domain.Cart;
import com.faitmain.domain.product.domain.Product;
import com.faitmain.domain.product.mapper.CartMapper;
import com.faitmain.domain.product.mapper.ProductMapper;
import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.web.domain.AttachImage;
import com.faitmain.domain.web.mapper.AttachMapper;
import com.faitmain.global.common.Criterion;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
    public List<OrderPageOne> getProductInfo( List<OrderPageOne> orderBundle ){

        ArrayList<OrderPageOne> result = new ArrayList<>();
        for ( OrderPageOne orderPageOne : orderBundle ) {
            OrderPageOne productInfo = orderMapper.getProductInfo( orderPageOne.getProductNumber() );

            productInfo.setProductQuantity( orderPageOne.getProductQuantity() );
            productInfo.initSaleTotal();

            List<AttachImage> imageList = attachMapper.getAttachList( productInfo.getProductNumber() );
            productInfo.setImageList( imageList );

            result.add( productInfo );
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
        ArrayList<OrderOne> orderBundle = new ArrayList<>();
        for ( OrderOne orderOne : order.getOrderBundle() ) {
            OrderOne ordOne = orderMapper.getOrderInfo( orderOne.getProductNumber() );
            /* 수량세팅 */
            ordOne.setProductQuantity( orderOne.getProductQuantity() );
            /* 기본정보 세팅 */
            ordOne.initSaleTotal();
            /* LIST 객체 추가 */
            orderBundle.add( ordOne );
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
        orderMapper.enrollOrder( order );
        for ( OrderOne orderOne : order.getOrderBundle() ) {
            orderOne.setOrderNumber( String.valueOf( orderNumber ) );
        }

        /* 포인트 변동 적용 */
        int calTotalPoint = user.getTotalPoint();
        calTotalPoint = calTotalPoint - order.getUsingPoint() + order.getOrderRewardPoint();
        user.setTotalPoint( calTotalPoint );

        /* 포인트 DB 적용 */
        orderMapper.deductMoney( user );

        /* 재고 변동 적용 */
        for ( OrderOne orderOne : order.getOrderBundle() ) {
            /* 변동 재고 값 구하기 */
            Product product = productMapper.getProduct( order.getProductNumber() );
            product.setProductQuantity( product.getProductQuantity() - orderOne.getProductQuantity() );
            /* 변동 값 DB 적용 */
            orderMapper.deductStock( product );
        }

        /* 장바구니 제거 */
        for ( OrderOne orderOne : order.getOrderBundle() ) {
            Cart cart = new Cart();
            cart.setUserId( order.getBuyerId() );
            cart.setProductNumber( order.getProductNumber() );
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
        List<OrderOne> orderBundle = orderMapper.getOrderOneInfo( orderCancle.getOrderNumber() );
        for ( OrderOne orderOne : orderBundle ) {
            orderOne.initSaleTotal();
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
        orderMapper.deductMoney( user );

        /* 재고 */
        for ( OrderOne orderOne : order.getOrderBundle() ) {
            Product product = productMapper.getProduct( order.getProductNumber() );
            product.setProductQuantity( product.getProductQuantity() + orderOne.getProductQuantity() );
            orderMapper.deductStock( product );
        }
    }



    /*****************************************************************************************************************/


    @Override
    public void addOrder( Order order ) throws Exception{

    }

    @Override
    public int updateOrder( Order order ){
        return orderMapper.updateOrder( order );
    }

    @Override
    public Order getOrder( int OrderNumber ){
        return orderMapper.getOrder( OrderNumber );
    }

    @Override
    public Map<String, Object> getOrderList(){
        List<Order> orderList = orderMapper.getOrderList();
        Map<String, Object> map = new HashMap<>();
        map.put( "list" , orderList );
        return map;
    }

    ////////////////////////////////iamport//////////////////////////////////////////////

    @Override
    public String getToken() throws Exception{

        HttpsURLConnection conn = null;

        URL url = new URL( "https://api.iamport.kr/users/getToken" );

        conn = ( HttpsURLConnection ) url.openConnection();

        conn.setRequestMethod( "POST" );
        conn.setRequestProperty( "Content-type" , "application/json" );
        conn.setRequestProperty( "Accept" , "application/json" );
        conn.setDoOutput( true );

        JsonObject json = new JsonObject();

//        json.addProperty( "apiKey" , apiKey );
//        json.addProperty( "apiScret" , apiSecret );

        BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( conn.getOutputStream() ) );

        bw.write( json.getAsShort() );
        bw.flush();
        bw.close();

        BufferedReader br = new BufferedReader( new InputStreamReader( conn.getInputStream() , StandardCharsets.UTF_8 ) );

        Gson gson = new Gson();

        String response = gson.fromJson( br.readLine() , Map.class ).get( "response" ).toString();
        System.out.println( "response = " + response );

        String token = gson.fromJson( response , Map.class ).get( "access.token" ).toString();
        System.out.println( "token = " + token );

        br.close();
        conn.disconnect();

        return token;
    }

    @Override
    public int paymentInfo( String imp_uid , String access_token ){
        return 0;
    }

    @Override
    public void pamentCancle( String access_token , String imp_uid , String amount , String reason ){

    }


}
