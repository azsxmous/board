package com.example.jpa.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// 리턴타입이 뷰리졸버가 아닌 String 형태가 됨
@RestController
public class SecondController {
	
	@RequestMapping(value="/hello-spring", method = RequestMethod.GET)
	public String helloSpring() {
		return "hello spring";
	}
	
	@GetMapping("/hello-rest")
	public String hellorest() {
		return "hello rest";
	}
	
	// api를 만들때는 주소에 /api/를 추가(강제는 아님)
	@GetMapping("/api/helloworld")
	public String helloRestApi() {
		return "hello rest api";
	}
}
