package com.mj.service;

import java.util.List;

import com.mj.dto.UsersDto;

public interface TestService {
	//로그인 체크
	int loginSelect(String id, String pw);
	//회원가입
	void createId(String id, String pw, String name, int point);
	//관리자 찾기
	String getId(String id);
	//유저정보
	UsersDto userInfo(String id);
	//유저들정보
	List<UsersDto> management();
	//유저정보수정
	void userUpdate(String pw, String name, int point, String id);
	//유저삭제
	void userDelete(String id);
	//포인트 모두 추가
	void pointUpdate();
	//포인트 추가
	void plusPoint(int plusPoint, String id);
	//포인트 차감
	void minusPoint(int minusPoint, String id);
	void startScheduler() throws Exception; //스케줄러 시작
	void endScheduler() throws Exception; //스케줄러 종료
	//인원수
	int userSelect();
}
