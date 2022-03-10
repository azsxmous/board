package com.example.jpa.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirstController {
	
	// �ּ� ������ ���� ������̼�. value="�ּ�", method = HTTP �޼ҵ�
	@RequestMapping(value = "/first-url", method = RequestMethod.GET)
	public void first() {
		
	}
	
	// Controller�� �⺻������ ���������� �����ϴµ� ResponseBody�� ����ϸ� ���ڿ��� ������ �� ����
	@ResponseBody
	// RequestMapping �� ���� http �޼ҵ�� �⺻������ GET
	@RequestMapping("/helloworld")
	public String helloworld() {
		return "hello world";
	}

}
