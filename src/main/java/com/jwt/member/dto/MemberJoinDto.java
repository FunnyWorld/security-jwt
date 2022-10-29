package com.jwt.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class MemberJoinDto {

	private String username;
	private String password;
	private String email;
	private String role;
}
