package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.GoodsDTO;
import com.service.GoodsService;

@Controller
public class GoodsController {
	@Autowired
	GoodsService service;
	
	@RequestMapping("/goodsList")
	public String gooodsList(@RequestParam(value="gCategory",
	required=false, defaultValue="top") String gCategory, Model m) {
		List<GoodsDTO> list= service.goodsList(gCategory);
		m.addAttribute("goodsList", list);
		return "goodsList";
	}
	
	
}