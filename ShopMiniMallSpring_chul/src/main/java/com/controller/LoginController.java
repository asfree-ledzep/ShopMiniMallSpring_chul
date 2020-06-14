package com.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class LoginController {
		@Autowired
		MemberService service;
		
		@RequestMapping("/logout")
		public String logout(HttpSession session) {
			session.invalidate();
			//return "redirect:main"; //main.jsp메인화면으로  이동
			return "redirect:main";
		}
		@RequestMapping("/loginUI")
		public String loginUI() {
			return "loginForm";
		}
		
		@RequestMapping(value="/login", method=RequestMethod.GET)
		public String login(@RequestParam Map<String,String> map, Model m, HttpSession session) {
			System.out.println(map);
			MemberDTO dto= service.login(map);
			System.out.println(dto);
			String mesg= null;
			String nextPage;
			if(dto== null) {
				mesg="회원가입이 필요합니다. ";
				m.addAttribute("mesg", mesg);
				nextPage="loginUI";
			}else {
				session.setAttribute("login", dto);
				nextPage="redirect:main"; //주의할 것 
			}
				
			return nextPage;
		}
}
