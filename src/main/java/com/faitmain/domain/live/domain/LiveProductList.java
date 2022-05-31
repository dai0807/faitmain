package com.faitmain.domain.live.domain;

import com.faitmain.domain.product.domain.Product;

import lombok.Data;

@Data
public class LiveProductList {
	private int liveProductNumber;
	private int liveNumber;
	private int liveReservationNumber;
	private Product liveProduct;
}
