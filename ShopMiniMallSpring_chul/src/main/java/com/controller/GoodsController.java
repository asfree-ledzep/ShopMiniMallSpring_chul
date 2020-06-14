package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.MemberDTO;
import com.service.CartService;
import com.service.GoodsService;

@Controller
public class GoodsController {
	@Autowired
	GoodsService goodsService;
	@Autowired
	CartService cartService;
	

	@RequestMapping("/goodsCart")
	public String goodsCart(CartDTO cartdto, HttpSession session, Model m) {
		MemberDTO mdto= (MemberDTO)session.getAttribute("login");
		System.out.println("goodsCart 파싱 cartdto========="+ cartdto);
		String nextPage="";
		if(mdto !=null ) {
			String userid= mdto.getUserid();
			cartdto.setUserid(userid);
			int result= cartService.goodsCart(cartdto);
			System.out.println("result===== " + result);
			session.setAttribute("mesg", "주문이 완료되었습니다.");
			nextPage="redircet:/goodsRetrieve?gCode="+ cartdto.getgCode();
		}else {
			m.addAttribute("mesg","로그인이 필요합니다.");
			nextPage="loginForm";
			
		}
		
		return nextPage;
	}

	
	
	@RequestMapping(value="/goodsRetrieve", method= RequestMethod.GET) //goodsRetrive.jsp 
	@ModelAttribute("goodsRetrieve") //model 객체에 gooodsRetrive 로 dto객체를 담음
	public GoodsDTO goodsRetrive(@RequestParam("gCode") String gCode) {//return type 주의
		GoodsDTO dto = goodsService.goodsRetrieve(gCode);		
		return dto; //Model객체에 dto를 삽입 
	}
	
	
	@RequestMapping("/goodsList")
	public String gooodsList(@RequestParam(value="gCategory",
	required=false, defaultValue="top") String gCategory, Model m) {
		List<GoodsDTO> list= goodsService.goodsList(gCategory);
		m.addAttribute("goodsList", list);
		return "goodsList";
	}
	
	
}
