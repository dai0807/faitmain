package com.faitmain.domain.order.domain;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class Order {

    @NotNull
    private int orderNumber;

    @NotNull
    private int productNumber;

    @NotNull
    private String buyerId;

    @NotNull
    private String storeId;

    @NotNull
    private Timestamp orderBundleNumber;

    @NotNull
    private Timestamp orderDate;

    @NotNull
    private int orderQuantity;

    @NotNull
    private String orderStatus;

    @NotNull
    private String receiverName;

    @NotNull
    private String receiverPhone;

    @NotNull
    private String receiverAddress;

    private String receiverRequest;

    private int deliveryTrackingNumber;

    private String deliveryCompanyCode;

    @NotNull
    private String paymentOption;

    @NotNull
    private int totalPaymentPrice;

    @NotNull
    private int rewardPoint;

    private int usingPoint;

    private Date orderClaimRequestDate;

    private Date orderClaimResponseDate;

    private int orderClaimReason;


}