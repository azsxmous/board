package com.example.jpa.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirstController {
	
	// 주소 매핑을 위한 어노테이션. value="주소", method = HTTP 메소드
	@RequestMapping(value = "/first-url", method = RequestMethod.GET)
	public void first() {
		
	}
	
	// Controller는 기본적으로 뷰페이지를 리턴하는데 ResponseBody를 사용하면 문자열을 리턴할 수 있음
	@ResponseBody
	// RequestMapping 에 대한 http 메소드는 기본적으로 GET
	@RequestMapping("/helloworld")
	public String helloworld() {
		return "hello world";
	}

}
