package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CartDAO;
import com.dto.CartDTO;

@Service
public class CartService {
		@Autowired
		CartDAO dao;

		public int goodsCart(CartDTO cartdto) {
			// TODO Auto-generated method stub
			return dao.goodsCart(cartdto);
		}

		public List<CartDTO> cartlist(String userid) {
			// TODO Auto-generated method stub
			return dao.cartlist(userid);
		}

		public int cartUpdate(CartDTO dto) {
			// TODO Auto-generated method stub
			return dao.cartUpdate(dto);
		}

		public int cartDelte(int num) {
			// TODO Auto-generated method stub
			return dao.cartDelte(num);
		}
}
