package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.CartDTO;

@Repository
public class CartDAO {
	
	@Autowired
	SqlSessionTemplate session;

	public int goodsCart(CartDTO cartdto) {
		// TODO Auto-generated method stub
		return session.insert("com.CartMapper.goodsCart", cartdto);
	}

	public List<CartDTO> cartlist(String userid) {
		// TODO Auto-generated method stub
		return session.selectList("com.CartMapper.carlist", userid);
	}

	
	public int cartUpdate(CartDTO dto) {
		// TODO Auto-generated method stub
		return session.update("com.CartMapper.cartUpdate", dto);
	}
	
}
