package com.myblog.payloads;

import lombok.Data;

@Data
public class UserDto {

	private Long id ;
	private String name;
	private String email;
	private String password;
	private String about;
}
