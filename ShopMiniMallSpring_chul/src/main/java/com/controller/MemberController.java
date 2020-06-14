package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService service;
	
	@RequestMapping("/MemberUpdateServlet")
	public String memberUpdate(MemberDTO dto, Model m, HttpSession session) {
		//web.xml 한글관련 코드 삽입 참고
		 
		System.out.println("memberUpdaet dto===="+ dto);
		int result=service.memberUpdate(dto);
		String nextPage= null;
		MemberDTO dto2= service.mypage(dto.getUserid());
		if(result == 1) {
			session.setAttribute("login", dto2);
			m.addAttribute("mesg", "정보가 수정되었습니다.");
			nextPage="mypage";
		}else {
			m.addAttribute("mesg", "로그인이 필요합니다.");
			nextPage="loginUI";
		}
		return nextPage;
	}
	@RequestMapping("/mypage")
	public String mypage(HttpSession session){
		MemberDTO dto=(MemberDTO) session.getAttribute("login");
		String userid= dto.getUserid();
		MemberDTO dto2= service.mypage(userid);
		System.out.println(dto2);
		session.setAttribute("login", dto2);
		return "mypage";
	}
	@RequestMapping("/memberUI")
	public String memberUI() {
		
		return "memberForm";
	}
	@RequestMapping("/memberForm")
	public String memberForm() {
		return "memberForm";
	}
	
	
	//String 을 뷰가 아닌 데이터(문자열, ArrayList, DTO등)로 처리하라는 의미.
	/*
	 * 
	<mvc:annotation-driven />
	<mvc:default-servlet-handler/>
 <!-- JSON/Ajax start -->
	<dependency>
	    <groupId>com.fasterxml.jackson.core</groupId>
	    <artifactId>jackson-databind</artifactId>
	    <version>2.8.8</version>
	</dependency>
		<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
	<dependency>
	    <groupId>com.fasterxml.jackson.core</groupId>
	    <artifactId>jackson-core</artifactId>
	    <version>2.8.8</version>
	</dependency>
	 */
	@RequestMapping(value="/idCheck", produces="text/plain;charset=UTF-8")
	@ResponseBody //pom.xml 에 디펜던시 등록하여 ajax사용함
	public String idCheck(@RequestParam("userid") String userid, @RequestParam("passwd") String passwd) {
		String mesg= "아이디사용가능";
		int count= service.idCheck(userid);
		if(count==1) {
			mesg="아이디중복";
		}
		return mesg;
	}
	
	@RequestMapping(value="/memberAdd", method= RequestMethod.GET)
	public String memberAdd(MemberDTO dto, Model m) {
		
		System.out.println("controller memberAdd=="+dto);
		int n= service.memberAdd(dto);
		if(n ==1 ) {
			m.addAttribute("mesg", "회원가입성공");
		}else {
			m.addAttribute("mesg", "회원가입실퍠");
		}
		return "redirect:main"; //maincontroller로 이동
	}

}
