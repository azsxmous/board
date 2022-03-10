package com.example.jpa.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// ����Ÿ���� �丮������ �ƴ� String ���°� ��
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
	
	// api�� ���鶧�� �ּҿ� /api/�� �߰�(������ �ƴ�)
	@GetMapping("/api/helloworld")
	public String helloRestApi() {
		return "hello rest api";
	}
}
