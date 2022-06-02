package com.faitmain.domain.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.faitmain.domain.user.domain.StoreApplicationDocument;
import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.user.mapper.UserMapper;
import com.faitmain.global.common.Search;
import org.springframework.transaction.annotation.Transactional;

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
		System.out.println(this.getClass());
	}
	
 	public User getUser(String id) throws Exception {
 		return userMapper.getUser(id);
	}


 	public int addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


 	public int updateUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


 	public int findUser(Map<String, Object> hasMap) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


 	public int getchechDuplication(Map<String, Object> hasMap) throws Exception {
		// TODO Auto-generated method stub
		return 0;
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

}
