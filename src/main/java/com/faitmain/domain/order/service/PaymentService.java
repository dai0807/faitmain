package com.faitmain.domain.order.service;

import com.faitmain.domain.order.domain.Payment;

import java.io.IOException;

public interface PaymentService{

    String getToken() throws IOException;

    int paymentInfo( String imp_uid , String access_token ) throws IOException;

    void paymentCancel( String access_token , String imp_uid , String amount , String reason );



    /* ********************************* */

    void addPaymentInfo( Payment payment );

    String getAccessToken( Payment payment );

    Payment getPaymentInfo( String accessToken , Payment payment );

    String getAmountToBePaid( Payment payment );

    void updatePaymentInfo( Payment payment );


}
