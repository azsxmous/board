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
	
	@Size(max = 20, message="연락처는 20자 이하로 입력해야 합니다.")
	@NotBlank(message="연락처는 필수항목입니다.")
	private String phone;

}
