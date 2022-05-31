package com.faitmain.domain.user.domain;


import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StoreApplicationDocument {

		int storeApplicationDocumentNumber ;
		String id ;
		String examinationStatus ;
		String productDetial ;
		Date regDate ;
	
	
	
	
}
