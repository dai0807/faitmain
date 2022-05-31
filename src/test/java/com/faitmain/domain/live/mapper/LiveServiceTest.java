package com.faitmain.domain.live.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.faitmain.domain.live.domain.Live;
import com.faitmain.domain.live.service.LiveService;
import com.faitmain.domain.product.mapper.ProductMapperTest;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class LiveServiceTest {

	Logger logger = LoggerFactory.getLogger(ProductMapperTest.class);
	
	@Autowired()
	@Qualifier("liveServiceImpl")
	private LiveService liveService;
	
	@Test
	public void testAddLive() throws Exception{
		
		Live live = new Live();
		
		live.setLiveNumber(10001);
		live.setStoreId("store01@naver.com");
		live.setLiveTitle("testLiveTitle");
		live.setLiveIntro("testLiveIntro");
		live.setLiveImage("testLiveImage");
		live.setChattingStatus(true);
		live.setLiveStatus(true);
		
		
	}
}
