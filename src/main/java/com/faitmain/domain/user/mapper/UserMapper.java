package com.faitmain.domain.user.mapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.faitmain.domain.user.domain.StoreApplicationDocument;
import com.faitmain.domain.user.domain.User;
import com.faitmain.global.common.Image;
import com.faitmain.global.common.Search;

 
@Mapper
@Repository
public interface UserMapper {

	//Select
	 User getUser(User User) ;
	
	//유저 insert 
	 int addUser(User user) ;
		//유저 insert 
 	 
	 
	 //유저 update
	 int updateUser(User user);
	 
	 // role update
	 int updatUserStore(User user);
	
	 //아이디/PW 찾기 할때 사용하는 findUser
	 int findUser(Map<String, Object> hashMap);
	 
	 //충복체크
	 int getchechDuplicationCount(  Map<String, Object> map) ;
	 
	 
	 int addStoreApplicationDocument(StoreApplicationDocument storeApplicationDocument) ; 
	 
	 int updateStoreApplicationDocument(StoreApplicationDocument storeApplicationDocument);

	 //스토어 문서 가져오기 
	 StoreApplicationDocument getStoreApplicationDocument(String store_application_document_number ) ;
	 
	 
	 //이미지 추가 
	 int addImage(Image image) ;
	 
	 //토탈카운트
	 int getUserTotalCount(Search search) ;
	
}
