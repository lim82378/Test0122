package com.mj.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mj.dto.UsersDto;

@Repository
public class TestDaoImpl implements TestDao {
	@Autowired
	SqlSession sqlSession;
	
	//로그인 체크
	@Override
	public int loginSelect(String id, String pw) {
		HashMap<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pw", pw);
		return sqlSession.selectOne("MemberMapper.loginSelect",map);
	}
	//회원가입
	@Override
	public void createId(String id, String pw, String name, int point) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("pw", pw);
		map.put("name", name);
		map.put("point", point);
		sqlSession.insert("MemberMapper.createId",map);
	}
	//관리자 찾기
	@Override
	public String getId(String id) {
		return sqlSession.selectOne("MemberMapper.getId",id);
	}
	//유저정보
	@Override
	public UsersDto userInfo(String id) {
		return sqlSession.selectOne("MemberMapper.userInfo",id);
	}
	//유저들정보
	@Override
	public List<UsersDto> management() {
		return sqlSession.selectList("MemberMapper.management");
	}
	@Override
	public void userUpdate(String pw, String name, int point, String id) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("pw", pw);
		map.put("name", name);
		map.put("point", point);
		map.put("id", id);
		sqlSession.update("MemberMapper.userUpdate",map);
	}
	@Override
	public void userDelete(String id) {
		sqlSession.delete("MemberMapper.userDelete",id);
	}
	@Override
	public void pointUpdate() {
		sqlSession.update("MemberMapper.pointUpdate");
	}
	@Override
	public void plusPoint(int plusPoint, String id) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("plusPoint", plusPoint);
		map.put("id", id);
		sqlSession.update("MemberMapper.plusPoint",map);
	}
	@Override
	public void minusPoint(int minusPoint, String id) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("minusPoint", minusPoint);
		map.put("id", id);
		sqlSession.update("MemberMapper.minusPoint",map);
	}
	@Override
	public int userSelect() {
		return sqlSession.selectOne("MemberMapper.userSelect");
	}

}
