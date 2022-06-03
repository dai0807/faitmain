package com.faitmain.domain.user.service;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.faitmain.domain.user.domain.StoreApplicationDocument;
import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.user.mapper.UserMapper;
import com.faitmain.global.common.Search;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserSerivce {

	
    @Autowired
	@Qualifier("userMapper")
 	private UserMapper userMapper ;

    public void setUserMapper ( UserMapper userMapper ){
        this.userMapper = userMapper;
    }
	
	public UserServiceImpl() {
		log.info( "Service = {} ", this.getClass() );
	}
	
 	public User getUser(User user) throws Exception {
 		return userMapper.getUser(user);
	}


 	public int addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.addUser(user);
	}


 	public int updateUser(User user) throws Exception {
 		return userMapper.updateUser(user);
	}


 	public int findUser(Map<String, Object> hashMap) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.findUser(hashMap);
	}


 	public int getchechDuplication(HashMap<String, Object> hashMap) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getchechDuplicationCount(hashMap);
	}


 	
 	
 	public void certifiedPhoneNumber(String userPhoneNumber, int randomNumber) throws Exception {
		// TODO Auto-generated method stub
		
	}


 	public String getAccessToken(String authorize_code) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


 	public HashMap<String, Object> getUserInfo(String access_Token) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


 	public int updateStore(User user) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateUserPassword(User user) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updatUserStore(User user) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public Map<String, Object> getUserList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int AddStoreApplicationDocument(StoreApplicationDocument storeApplicationDocument) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateStoreApplicationDocument(StoreApplicationDocument storeApplicationDocument) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public StoreApplicationDocument getStoreApplicationDocument(StoreApplicationDocument storeApplicationDocument)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, Object> getStoreApplicationDocumentList(StoreApplicationDocument storeApplicationDocument)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void sendCertificationSms(String userPhoneNumber, int randomNumber) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void certifiedPhoneNumber(String userPhoneNumber, String smsCertification) throws Exception {
		// TODO Auto-generated method stub
		
	}

	 
	public int getchechDuplication(Map<String, Object> hashMap) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


	

}
