package com.faitmain.domain.order.domain;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

//@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class Order {

    private int orderNumber;

    private int productNumber;

    private String buyerId;

    private String storeId;

    private Timestamp orderBundleNumber;

    private Timestamp orderDate;

    private int orderQuantity;

    private String orderStatus;

    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;

    private String receiverRequest;

    private int deliveryTrackingNumber;

    private String deliveryCompanyCode;

    private String paymentOption;

    private int totalPaymentPrice;

    private int rewardPoint;

    private int usingPoint;

    private Date orderClaimRequestDate;

    private Date orderClaimResponseDate;

    private int orderClaimReason;


}