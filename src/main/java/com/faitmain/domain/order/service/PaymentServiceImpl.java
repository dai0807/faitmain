package com.faitmain.domain.order.service;

import com.faitmain.domain.order.domain.Payment;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService{

    @Value( "${imp_key}" )
    private String impKey;

    @Value( "${imp_secret}" )
    private String impSecret;

    @ToString
    @Getter
    private class Response{
        private PaymentInfo response;
    }

    @ToString
    @Getter
    private class PaymentInfo{
        private int amount;
    }

    @Override
    public String getToken() throws IOException{

        HttpsURLConnection conn = null;

        URL url = new URL( "https://api.iamport.kr/users/getToken" );

        conn = ( HttpsURLConnection ) url.openConnection();

        conn.setRequestMethod( "POST" );
        conn.setRequestProperty( "Content-type" , "application/json" );
        conn.setRequestProperty( "Accept" , "application/json" );
        conn.setDoOutput( true );

        JsonObject json = new JsonObject();

        json.addProperty( "imp_key" , impKey );
        json.addProperty( "imp_secret" , impSecret );

        BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( conn.getOutputStream() ) );

        bw.write( json.toString() );
        bw.flush();
        bw.close();

        BufferedReader br = new BufferedReader( new InputStreamReader( conn.getInputStream() , StandardCharsets.UTF_8 ) );

        Gson gson = new Gson();
        String response = gson.fromJson( br.readLine() , Map.class ).get( "response" ).toString();
        System.out.println( response );
        String token = gson.fromJson( response , Map.class ).get( "access_token" ).toString();

        br.close();
        conn.disconnect();

        return token;
    }

    @Override
    public int paymentInfo( String imp_uid , String access_token ) throws IOException{

        HttpsURLConnection conn = null;

        URL url = new URL( "https://api.iamport.kr/payments/" + imp_uid );

        conn = ( HttpsURLConnection ) url.openConnection();

        conn.setRequestMethod( "GET" );
        conn.setRequestProperty( "Authorization" , access_token );
        conn.setDoOutput( true );

        BufferedReader br = new BufferedReader( new InputStreamReader( conn.getInputStream() , StandardCharsets.UTF_8 ) );

        Gson gson = new Gson();

        Response response = gson.fromJson( br.readLine() , Response.class );

        br.close();
        conn.disconnect();

        return response.getResponse().getAmount();
    }

    @Override
    public void paymentCancel( String access_token , String imp_uid , int amount , String reason ) throws IOException{

        log.info( "access_token = {}", access_token );
        log.info( "imp_uid = {}" , imp_uid );

        HttpsURLConnection conn = null;
        URL url = new URL( "https//api.iamport.kr/payments/cancel" );

        conn = ( HttpsURLConnection ) url.openConnection();

        conn.setRequestMethod( "POST" );
        conn.setRequestProperty( "Content-type" , "applicaition/json" );
        conn.setRequestProperty( "Accept" , "application/json" );
        conn.setRequestProperty( "Authorization" , access_token );

        conn.setDoOutput( true );

        JsonObject json = new JsonObject();

        json.addProperty( "reason", reason );
        json.addProperty( "imp_uid", imp_uid );
        json.addProperty( "amount", amount );
        json.addProperty( "checkSum" , amount );

        BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( conn.getOutputStream() ) );

        bw.write( json.toString() );
        bw.flush();
        bw.close();

        BufferedReader br = new BufferedReader( new BufferedReader( new InputStreamReader( conn.getInputStream() , StandardCharsets.UTF_8 ) ) );

        br.close();
        conn.disconnect();
    }


    /* ************************************ */


    @Override
    public void addPaymentInfo( Payment payment ){

    }

    @Override
    public String getAccessToken( Payment payment ){
        String access_Token = "";
        String reqURL = "https://api.iamport.kr/users/getToken";
        String data = "{ \"imp_key\" : \"{REST API 키}\", \"imp_secret\" : \"{REST API secret}\"}";

        try {
            URL url = new URL( reqURL );
            HttpsURLConnection conn = ( HttpsURLConnection ) url.openConnection();

            /* POST */
            conn.setDoOutput( true );
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty( "Content-Type" , "application/json" );

            /* DATA INSERT */
            try {
                OutputStream outputStream = conn.getOutputStream();
                byte[] request_data = data.getBytes( StandardCharsets.UTF_8 );
                outputStream.write( request_data );
                outputStream.close();
            } catch ( Exception e ) {
                e.printStackTrace();
            }

            int responseCode = conn.getResponseCode();
            if ( responseCode == 200 ) {
                /* 요청을 통해 얻은 JSON 타입의 RESPONSE 메세지 읽어오기 */
                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
                String line = "";
                String result = "";

                while ( ( line = bufferedReader.readLine() ) != null ) {
                    result += line;
                }

                /* GSON 라이브러리에 포함된 클래스로 JSON파싱 후 객체 생성 */
                JsonParser jsonParser = new JsonParser();
                JsonElement element = jsonParser.parse( result );

                access_Token = element.getAsJsonObject().get( "response" ).getAsJsonObject().get( "acceess_token" ).getAsString();
                bufferedReader.close();
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return access_Token;
    }

    @Override
    public Payment getPaymentInfo( String accessToken , Payment payment ){
        return null;
    }

    @Override
    public String getAmountToBePaid( Payment payment ){
        return null;
    }

    @Override
    public void updatePaymentInfo( Payment payment ){

    }
}
