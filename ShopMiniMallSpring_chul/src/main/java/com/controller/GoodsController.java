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
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping("/cartDelte")
	@ResponseBody
	public void cartDelte(@RequestParam("num") String num) {
		System.out.println("cartDelte request num= "+ num);
		int delnum= Integer.parseInt(num);
		int result= cartService.cartDelte(delnum);
		System.out.println("cartDelte result= "+ result);
	}
	
	@RequestMapping(value="/cartUpdate", method=RequestMethod.GET)
	@ResponseBody
	public void cartUpdate(@RequestParam("num") String num, @RequestParam("gAmount") String gAmount) {
			CartDTO dto= new CartDTO();
			dto.setNum(Integer.parseInt(num));
			dto.setgAmount(Integer.parseInt(gAmount));
			System.out.println("cartUpdate dto===="+ dto);
			 int result= cartService.cartUpdate(dto);
		
	}
	@RequestMapping("/cartList")
	public String carlist(HttpSession session, Model m) {
			MemberDTO memdto= (MemberDTO)session.getAttribute("login");
			String nextPage="";
			if(memdto != null) {
			List<CartDTO> list=cartService.cartlist(memdto.getUserid());
			session.setAttribute("cartList", list);
			
			nextPage="cartList";
		}else {
			m.addAttribute("mesg","로그인이 필요합니다.");
			nextPage="loginForm";			
		}
		return nextPage;
	}
	
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
