package com.faitmain.global.util;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.faitmain.domain.customer.constant.Method;

@Controller
public class UiUtils {
//Customer
	public String showMessageWithRedirect(@RequestParam(value = "message", required = false) String message,
											@RequestParam(value = "redirectUri", required = false) String redirectUri,
											@RequestParam(value = "method", required = false) Method method,
											@RequestParam(value = "params", required = false) Map<String, Object> params, Model model) {
		
		model.addAttribute("message", message);
		model.addAttribute("redirectUri", redirectUri);
		model.addAttribute("method", method);
		model.addAttribute("params", params);
		
		System.out.println(message);
		System.out.println(redirectUri);
		System.out.println(method);
		System.out.println(params);
		
		return "/utils/message-redirect";
	}
}
