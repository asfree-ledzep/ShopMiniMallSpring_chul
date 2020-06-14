package com.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MemberDAO;
import com.dto.MemberDTO;

@Service
public class MemberService {
		@Autowired
		MemberDAO dao;
	
		public int idCheck(String userid) {
			// TODO Auto-generated method stub
			return dao.idCheck(userid);
		}
		public MemberDTO login(Map<String, String> map) {
			// TODO Auto-generated method stub
			return dao.login(map);
		}
		public MemberDTO mypage(String userid) {
			// TODO Auto-generated method stub
			return dao.mypage(userid);
		}
		public int memberUpdate(MemberDTO dto) {
			// TODO Auto-generated method stub
			return dao.memberUpdate(dto);
		}
		public int memberAdd(MemberDTO dto) {
			// TODO Auto-generated method stub
			return dao.memberAdd(dto);
		}
	
}
