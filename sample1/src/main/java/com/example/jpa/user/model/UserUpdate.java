package com.example.jpa.user.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserUpdate {
	
	@Size(max = 20, message="����ó�� 20�� ���Ϸ� �Է��ؾ� �մϴ�.")
	@NotBlank(message="����ó�� �ʼ��׸��Դϴ�.")
	private String phone;

}
