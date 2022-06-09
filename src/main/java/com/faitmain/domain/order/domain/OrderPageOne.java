package com.faitmain.domain.order.domain;

import lombok.Data;

@Data
public class OrderPageOne{

    /* 뷰로부터 전달받을 값 */
    private int productNumber;

    private int productQuantity;

    /* DB로부터 꺼내올 값 */
    private String productName;

    private int productPrice;

    private double productDisocunt;

    /* 만들어 낼 값 */
    private int salePrice;

    private int totalPrice;

    private int point;

    private int totalPoint;


    public void initSaleTotal() {
        this.salePrice = (int) (this.productPrice * (1-this.productDisocunt));
        this.totalPrice = this.salePrice*this.productQuantity;
        this.point = (int)(Math.floor(this.salePrice*0.05));
        this.totalPoint =this.point * this.productQuantity;
    }


}
