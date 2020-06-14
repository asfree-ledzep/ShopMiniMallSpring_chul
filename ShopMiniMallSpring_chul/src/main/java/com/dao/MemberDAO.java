package com.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MemberDTO;

@Repository
public class MemberDAO {
	@Autowired
	SqlSessionTemplate session;
	


	public int idCheck(String userid) {
		// TODO Auto-generated method stub
		return session.selectOne("com.MemberMapper.idCheck", userid);
	}

	public MemberDTO login(Map<String, String> map) {
		// TODO Auto-generated method stub
		return session.selectOne("com.MemberMapper.login", map);
	}

	public MemberDTO mypage(String userid) {
		// TODO Auto-generated method stub
		return session.selectOne("com.MemberMapper.mypage",userid);
	}

	public int memberUpdate(MemberDTO dto) {
		// TODO Auto-generated method stub
		return session.update("com.MemberMapper.memberUpdate", dto);
	}

	public int memberAdd(MemberDTO dto) {
		// TODO Auto-generated method stub
		return session.insert("com.MemberMapper.memberAdd",dto);
	}

	

}
