package com.service;

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
}
