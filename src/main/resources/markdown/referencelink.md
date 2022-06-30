```
   


```

[https://blog.naver.com/hkhaoo/222649333474](https://blog.naver.com/hkhaoo/222649333474)

[https://blog.naver.com/312movie/222690708344](https://blog.naver.com/312movie/222690708344)

![img_1.png](img_1.png)

![img.png](img.png)

커밋확인용

https://bootpay.gitbook.io/docs/server/link

# (order module)

https://kimvampa.tistory.com/category/%EC%8A%A4%ED%94%84%EB%A7%81%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8?page=8

# (bootstrap grid)

http://shoelace.io/

# (iamport console)

https://admin.iamport.kr/users/login

# (iamport manual)

https://docs.iamport.kr/implementation/payment

# (iamport parameter)

https://docs.iamport.kr/sdk/javascript-sdk?lang=ko#request_pay

# (iamport reference blog)

https://l4279625.tistory.com/27

# (iamport reference source code)

![](../../../../../../../../../var/folders/_6/6m3nbswd29g8q2jtq1_jvpj00000gn/T/TemporaryItems/NSIRD_screencaptureui_b07Gu1/스크린샷 2022-06-13 오전 12.20.57.png)

https://github.com/dudwk814/petppy



``` @Controller
public class UiUtils {
//Customer
	public String showMessageWithRedirect(@RequestParam(value = "message", required = false) String message,
											@RequestParam(value = "redirectUri", required = false) String redirectUri,
											@RequestParam(value = "method", required = false) Method method,
											@RequestParam(value = "map", required = false) Map<String, Object> params, Model model) {
		
		 System.out.println("=======showMessageWithRedirect===시작 ==");

		 System.out.println("여기에 왔니");

		
		model.addAttribute("message", message);
		model.addAttribute("redirectUri", redirectUri);
		model.addAttribute("method", method);
		model.addAttribute("params", params);
		
		System.out.println("message"+message);
		System.out.println("redirectUri"+redirectUri);
		System.out.println("method"+method);
		System.out.println("params"+params);
		 System.out.println("=======showMessageWithRedirect===끝 ==");
		
		return "utils/message-redirect";
	}
}
```