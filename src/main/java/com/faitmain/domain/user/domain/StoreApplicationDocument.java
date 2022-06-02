package com.faitmain.domain.user.domain;


import java.sql.Date;
import java.util.List;

import com.faitmain.global.common.Image;

import lombok.Data;


@Data
public class StoreApplicationDocument {

		private int storeApplicationDocumentNumber ;
		private String id ;
		private String examinationStatus ;
		private String productDetial ;
		private Date regDate ;
	
		private List<Image> StoreApplicationDocumentImage ;
	
	
}
