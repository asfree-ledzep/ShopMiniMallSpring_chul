package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.GoodsDTO;
import com.service.GoodsService;

@Controller
public class GoodsController {
	@Autowired
	GoodsService service;
	
	
	@RequestMapping(value="/goodsRetrieve", method= RequestMethod.GET) //goodsRetrive.jsp 
	@ModelAttribute("goodsRetrieve") //model 객체에 gooodsRetrive 로 dto객체를 담음
	public GoodsDTO goodsRetrive(@RequestParam("gCode") String gCode) {//return type 주의
		GoodsDTO dto = service.goodsRetrieve(gCode);		
		return dto; //Model객체에 dto를 삽입 
	}
	@RequestMapping("/goodsList")
	public String gooodsList(@RequestParam(value="gCategory",
	required=false, defaultValue="top") String gCategory, Model m) {
		List<GoodsDTO> list= service.goodsList(gCategory);
		m.addAttribute("goodsList", list);
		return "goodsList";
	}
	
	
}
