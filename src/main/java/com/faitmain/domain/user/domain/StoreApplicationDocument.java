package com.faitmain.domain.user.domain;


import java.sql.Date;
import java.util.List;

import lombok.Data;


@Data
public class StoreApplicationDocument {

		int storeApplicationDocumentNumber ;
		String id ;
		String examinationStatus ;
		String productDetial ;
		Date regDate ;
	
		List<Image> StoreApplicationDocumentImage ;
	
	
}
