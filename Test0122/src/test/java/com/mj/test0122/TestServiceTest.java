package com.mj.test0122;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mj.dto.UsersDto;
import com.mj.service.TestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestServiceTest {
	@Autowired
	TestService tSvc;
	//1. 준비(Given)
	//2. 실행(When)
	//3. 검증(Then)
	
	@Test
	public void testLoginSelect() throws Exception {
		String id = "user1";
		String pw = "1";
		int count = tSvc.loginSelect(id, pw);
		System.out.println("로그인 여부(1이면 로그인됨) : "+count);
	}
	//원래는 트렌젝션으로 롤백해야됨
	@Test
	public void testCreateId() throws Exception {
		String id = "user3";
		String pw = "3";
		String name = "유저3";
		int point = 1000;
		tSvc.createId(id, pw, name, point);
		System.out.println("회원 가입 성공");
	}
	@Test
	public void testGetId() throws Exception {
		String id = "user1";
		String idCheck = tSvc.getId(id);
		System.out.println("id : "+idCheck);
	}
	@Test
	public void testUserInfo() throws Exception {
		String id = "user1";
		UsersDto dto = tSvc.userInfo(id);
		System.out.println(dto.getId()+dto.getName()+dto.getPw()+dto.getPoint());
	}
	@Test
	public void testManagement() throws Exception {
		List<UsersDto> dto = tSvc.management();
		for(UsersDto list : dto) {
			System.out.println(list.getId()+list.getName()+list.getPw()+list.getPoint());
		}
	}
	@Test
	@Transactional
	public void testUserUpdate() throws Exception {
		String id = "user1";
		String pw = "01";
		int point = 10000;
		String name = "유저01";
		tSvc.userUpdate(pw, name, point, id);
		System.out.println("업데이트 되었다가 롤백");
	}
	@Test
	@Transactional
	public void testUserDelete() throws Exception {
		String id = "user1";
		tSvc.userDelete(id);
		System.out.println("삭제되었다가 롤백");
	}
	@Test
	@Transactional
	public void testPointUpdate() throws Exception {
		tSvc.pointUpdate();
		System.out.println("추가되었다가 롤백");
	}
	@Test
	@Transactional
	public void testPlusPoint() throws Exception {
		String id = "user1";
		int plusPoint = 100;
		tSvc.plusPoint(plusPoint, id);
		System.out.println("추가되었다가 롤백");
	}
	@Test
	@Transactional
	public void testMinusPoint() throws Exception {
		String id = "user1";
		int minusPoint = 100;
		tSvc.plusPoint(minusPoint, id);
		System.out.println("추가되었다가 롤백");
	}
	@Test
	public void userSelect() throws Exception {
		int count = tSvc.userSelect();
		System.out.println(count);
	}
}
