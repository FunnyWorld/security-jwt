package com.jwt.member.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jwt.member.dto.LoginDto;

@Controller
public class MemberController {

	@GetMapping("/login")
	public String loginForm() {
		return "member/loginForm";
	}

	@PostMapping("/login")
	public String login(Model model, LoginDto loginDto) {
		return "redirect:/main";
	}
}
