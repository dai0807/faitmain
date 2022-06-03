package com.faitmain.domain.user.domain;


import java.sql.Date;
import java.util.List;

import com.faitmain.global.common.Image;

import lombok.Data;


@Data
public class StoreApplicationDocument {


		int storeApplicationDocumentNumber ;
		String id ;
		String storeName ;
		String storeIntroduction ;
		String examinationStatus ; // R, A ,W 
		String productDetial ;
		List<Image> productmanufacturingImage ;
		Date regDate ;
	
		
	
	
}
