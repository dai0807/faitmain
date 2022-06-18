package com.faitmain.domain.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.faitmain.domain.live.domain.Live;
import com.faitmain.domain.live.domain.LiveReservation;
import com.faitmain.domain.live.service.LiveService;
import com.faitmain.domain.product.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
public class WebController {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	@Autowired
	@Qualifier("liveServiceImpl")
	private LiveService liveService;

	@GetMapping("/")
	public String main(Model model) throws Exception {
		log.info("log = {} ", this.getClass().getName());

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("orderName", "product_name DESC");
		map.put("startRowNum", 1);
		map.put("endRowNum", 5);

		map = productService.getProductList(map);

		map.put("liveList", liveService.getLiveList().get("liveList"));

		log.info("after getLiveList");

		LiveReservation liveReservation = liveService.getCurrentLiveReservation();
		log.info("liveReservation : {}", liveReservation);

		Live live = null;
		if (liveReservation != null) {
			live = liveService.getLiveByStoreId(liveReservation.getStore().getId());
		}
		log.info("live : {}", live);

		model.addAttribute("map", map);
		model.addAttribute("liveReservation", liveReservation);

		model.addAttribute("live", live);

		System.out.println(model);
		return "index";
	}

	@GetMapping("/myPage")
	public String getMyPage() {
		log.info("getMyPage : GET start...");

		log.info("getMyPage : GET end...");
		return "myPage";
	}

}
