package com.faitmain.domain.user.service;

 import java.util.HashMap;
import java.util.Map;

import com.faitmain.domain.user.domain.StoreApplicationDocument;
import com.faitmain.domain.user.domain.User;
 import com.faitmain.domain.user.mapper.UserMapper;
import  com.faitmain.global.common.Search;

public interface UserSerivce {

	
	// User
	
	public int addUser (User user) throws Exception;
	
	public int updateUser (User user) throws Exception ;
	
	public User getUser(User user)  throws Exception;
	
	//id&pw 찾기 find 시 사용 
	public int findUser (Map<String, Object> hasMap) throws Exception;
	
	//유효성 검증
	public int getchechDuplication (Map<String, Object> hasMap) throws Exception;

	public int updateUserPassword(User user) throws Exception ;
	
	
	//문자 보내기
	public void sendCertificationSms (String userPhoneNumber, int randomNumber) throws Exception ;
		
	
	//문자 인증
	public void certifiedPhoneNumber (String userPhoneNumber, String smsCertification ) throws Exception ;
	
	// 카카오 토큰 받기
	public String getAccessToken(String authorize_code) throws Exception ;
	//카카오 정보 받아오기
	public HashMap<String, Object> getUserInfo(String access_Token) throws Exception ; 
 	// 
	public int updatUserStore (User user) throws Exception ;
	
	public Map<String,Object> getUserList (Search search) throws Exception ;

	
	
	
	
	// Store Application
	public int AddStoreApplicationDocument (StoreApplicationDocument storeApplicationDocument) throws Exception ; 
	public int updateStoreApplicationDocument (StoreApplicationDocument storeApplicationDocument) throws Exception ; 
	public StoreApplicationDocument getStoreApplicationDocument (StoreApplicationDocument storeApplicationDocument) throws Exception ; 
	public HashMap<String, Object> getStoreApplicationDocumentList (StoreApplicationDocument storeApplicationDocument) throws Exception ; 

	
	
}
