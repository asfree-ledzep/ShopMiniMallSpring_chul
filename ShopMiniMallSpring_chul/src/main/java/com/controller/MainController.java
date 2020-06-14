//ShopMiniMall의 서블릿버전의 MainServlet 역할

package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.GoodsDTO;
import com.service.GoodsService;


@Controller
public class MainController {
	
	@Autowired
	GoodsService goodsService; 
	
	@RequestMapping("/main")
	public String main(Model m) { //추가
		List<GoodsDTO> list= goodsService.goodsList("outer");
		m.addAttribute("goodsList", list);
		return "main"; //main.jsp
	}
}
