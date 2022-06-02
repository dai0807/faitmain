package com.faitmain.domain.live.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.faitmain.domain.live.domain.Live;
import com.faitmain.domain.live.domain.LiveChat;
import com.faitmain.domain.live.domain.LiveProduct;
import com.faitmain.domain.live.domain.LiveReservation;
import com.faitmain.domain.live.domain.LiveUserStatus;
import com.faitmain.domain.product.domain.Product;
import com.faitmain.domain.product.mapper.ProductMapper;

@AutoConfigureTestDatabase(replace=Replace.NONE)
@MybatisTest
public class LiveMapperTest {

	@Autowired
	private LiveMapper liveMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	
	/*================================= Live ===================================== */
	@Test
	public void addLiveTest() throws Exception {
		System.out.println("addLiveTest start");
		
		Live live = new Live();
		
		live.setStoreId("store03@naver.com");
		live.setLiveTitle("liveTitleTest");
		live.setLiveIntro("liveIntroTest");
		live.setLiveImage("liveImageTest");
		live.setLiveStatus(true);
		live.setChattingStatus(true);
		
		Assertions.assertEquals(1, liveMapper.addLive(live));
		
		System.out.println("addLiveTest end");
	}
	
	//@Test
	public void updateLiveTest() throws Exception {
		System.out.println("updateLiveTest start");
		
		Live live = new Live();
		
		live.setLiveNumber(10003);
		live.setStoreId("store03@naver.com");
		live.setLiveTitle("updateliveTitleTest");
		live.setLiveIntro("updateliveIntroTest");
		live.setLiveImage("updateliveImageTest");
		live.setLiveStatus(true);
		live.setChattingStatus(true);
		
		int result = liveMapper.updateLive(live);
		System.out.println("result = " + result);
		
		Live updateLive = liveMapper.getLive(10003);
		
		Assertions.assertEquals("store03@naver.com", updateLive.getStoreId());
		Assertions.assertEquals("updateliveTitleTest", updateLive.getLiveTitle());
		Assertions.assertEquals("updateliveIntroTest", updateLive.getLiveIntro());
		Assertions.assertEquals("updateliveImageTest", updateLive.getLiveImage());
		
		System.out.println("updateLiveTest end");
	}
	
	
	//@Test
	public void getLiveTest() throws Exception {
		System.out.println("getLiveTest start");
			
		Live getLive = liveMapper.getLive(10001);
			
		Assertions.assertEquals("store01@naver.com", getLive.getStoreId());
		Assertions.assertEquals("live_title 02", getLive.getLiveTitle());
		Assertions.assertEquals("live_intro 02", getLive.getLiveIntro());
		Assertions.assertEquals("live_image 02", getLive.getLiveImage());
			
		System.out.println("getLiveTest end");
	}
	
	//@Test
	public void getLiveListTest() throws Exception {
		System.out.println("getLiveListTest start");
				
		
		
		System.out.println("getLiveListTest end");
	}
		
	/*================================= Live Chat ====================================== */
	
	
	//@Test
	public void addLiveChatTest() throws Exception {
		System.out.println("addLiveChatTest start");
		
		LiveChat liveChat = new LiveChat();
		
		liveChat.setWriter("user02@naver.com");
		liveChat.setChattingMessage("addLiveChatTest");
		
		Assertions.assertEquals(1, liveMapper.addLiveChat(liveChat));
		
		System.out.println("addLiveChatTest end");
	}
	
	//@Test
	public void getLiveChatListTest() throws Exception {
		System.out.println("getLiveChatListTest start");
		
		
		
		System.out.println("getLiveChatListTest end");
	}
	
	/*================================= Live Product ====================================== */
	
	//@Test
	public void addLiveProductTest() throws Exception{
		System.out.println("addLiveProductTest start");
		
		LiveProduct liveProduct = new LiveProduct();
		Product product = new Product();
		
		product = productMapper.getProduct(10011);
		
		liveProduct.setLiveNumber(10001);
		liveProduct.setLiveReservationNumber(0);
		liveProduct.setLiveProduct(product);
		
		Assertions.assertEquals(1, liveMapper.addLiveProduct(liveProduct));
		
		System.out.println("addLiveProductTest end");
	}
	
	//@Test
	public void getLiveProductTest() throws Exception{
		System.out.println("getLiveProductTest start");
			
		LiveProduct liveProduct = new LiveProduct();
		
		liveProduct = liveMapper.getLiveProduct(10001);
		
		Assertions.assertEquals(10001, liveProduct.getLiveProductNumber());
		Assertions.assertEquals(10001, liveProduct.getLiveNumber());
		Assertions.assertEquals(10001, liveProduct.getLiveReservationNumber());
		Assertions.assertEquals(10011, liveProduct.getLiveProduct().getProductNumber());
		Assertions.assertEquals("product_main_image.jpg", liveProduct.getLiveProduct().getProductMainImage());
			
		System.out.println("getLiveProductTest end");
	}
	
	//@Test
	public void getLiveProductListTest() throws Exception{
		System.out.println("getLiveProductListTest start");
				
				
		System.out.println("getLiveProductListTest end");
	}
	
	//@Test
	public void deleteLiveProductTest() throws Exception{
		System.out.println("deleteLiveProductTest start");
		
		Assertions.assertEquals(1, liveMapper.deleteLiveProduct(10001));
		
		System.out.println("deleteLiveProductTest end");
	}
	
	/*================================= Live Reservation ====================================== */
	
	//@Test
	public void addLiveReservationTest() throws Exception{
		System.out.println("addLiveReservationTest start");
		
		LiveReservation liveReservation = new LiveReservation();
		
		liveReservation.setStoreId("store03@naver.com");
		liveReservation.setReservationDate("2022-07-14 09:00");
		
		Assertions.assertEquals(1, liveMapper.addLiveReservation(liveReservation));
		
		System.out.println("addLiveReservationTest end");
	}
	
	/*================================= Live User Status ====================================== */
	
	//@Test
	public void addLiveUserStatusTest() throws Exception{
		System.out.println("addLiveUserStatusTest start");
			
		LiveUserStatus liveUserStatus = new LiveUserStatus();
		
		liveUserStatus.setLiveNumer(10001);
		liveUserStatus.setId("user02@naver.com");
			
		Assertions.assertEquals(1, liveMapper.addLiveUserStatus(liveUserStatus));
			
		System.out.println("addLiveUserStatusTest end");
	}
	
	//@Test
	public void updateLiveUserStatusTest() throws Exception{
		System.out.println("updateLiveUserStatusTest start");
				
		LiveUserStatus liveUserStatus = new LiveUserStatus();
			
		liveUserStatus.setLiveNumer(10001);
		liveUserStatus.setId("user02@naver.com");
		liveUserStatus.setAlarmStatus(false);
		liveUserStatus.setKickStatus(false);
		liveUserStatus.setDumbStatus(false);
				
		int result = liveMapper.updateLiveUserStatus(liveUserStatus);
		System.out.println("result = " + result);
		
		LiveUserStatus updateLiveUserStatus = liveMapper.getLiveUserStatus(liveUserStatus);
		
		Assertions.assertEquals(10001, updateLiveUserStatus.getLiveNumer());
		Assertions.assertEquals("user02@naver.com", updateLiveUserStatus.getId());
		Assertions.assertEquals(false, updateLiveUserStatus.isAlarmStatus());
		Assertions.assertEquals(false, updateLiveUserStatus.isKickStatus());
		Assertions.assertEquals(false, updateLiveUserStatus.isDumbStatus());
				
		System.out.println("updateLiveUserStatusTest end");
	}
	
	//@Test
	public void getLiveUserStatusListTest() throws Exception{
		System.out.println("getLiveUserStatusListTest start");
		
		List<LiveUserStatus> list = liveMapper.getLiveUserStatusList(10001);
		
		System.out.println("getLiveUserStatusListTest end");
	}
}
