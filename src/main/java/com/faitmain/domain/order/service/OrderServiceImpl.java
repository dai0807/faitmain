package com.faitmain.domain.order.service;

import com.faitmain.domain.order.domain.Order;
import com.faitmain.domain.order.domain.OrderPageOne;
import com.faitmain.domain.order.mapper.OrderMapper;
import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.user.mapper.UserMapper;
import com.faitmain.domain.web.domain.AttachImage;
import com.faitmain.domain.web.mapper.AttachMapper;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    AttachMapper attachMapper;




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

    @Override
    public User getBuyerInfo( int userNumber ){
        return orderMapper.getBuyerInfo( userNumber );
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
    public Map< String, Object > getOrderList(){
        List< Order > orderList = orderMapper.getOrderList();
        Map< String, Object > map = new HashMap<>();
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
