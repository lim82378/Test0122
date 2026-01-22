package com.mj.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mj.dto.UsersDto;
import com.mj.service.TestService;

@Controller
public class HomeController {
	@Autowired
	TestService tSvc;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@RequestMapping("/")
	public String home() {
		return "login";
	}
	@RequestMapping("/login_check")
	public String logincheck(@RequestParam("id") String id, 
            @RequestParam("pw") String pw, 
            Model model, HttpSession session) {
		//관리자 아이디
		String adminName = "admin";
		//로그인 체크
		int count = tSvc.loginSelect(id, pw);
		//로그인 아이디 확인
		String admin = tSvc.getId(id);
		if (count == 1) {
			session.setAttribute("userId", admin);
	        
	        if (admin.equals(adminName)) {
	            // 관리자 로그인
	            return "redirect:/adminpage";
	        }
	        //일반유저 로그인
	        return "redirect:/main"; 
	    } else {
	    	model.addAttribute("msg","아이디/비밀번호를 다시 확인하세요!");
	    	return "login";
	    }
	}
	@RequestMapping("/main")
	public String main(Model model,HttpSession session) {
		String loginId = (String) session.getAttribute("userId");
	    
	    if (loginId == null) {
	        return "redirect:/";
	    }
	    
	    UsersDto listuser = tSvc.userInfo(loginId);
	    model.addAttribute("userInfo", listuser);
	    
		return "main";
	}
	@RequestMapping("/adminpage")
	public String adminpage(Model model,HttpSession session,RedirectAttributes rttr) {
		String loginId = (String) session.getAttribute("userId");
	    String admin = "admin";
	    if (loginId == null || !loginId.equals(admin)) {
	        return "redirect:/main";
	    }
	    
	    List<UsersDto> listuser = tSvc.management();
	    model.addAttribute("management", listuser);
	    
		return "adminpage";
	}
	
	@RequestMapping("/cearted_id")
	public String ceartedId() {
		return "ceartedId";
	}
	@RequestMapping("/insertId")
	public String insertId(@RequestParam("id") String id, 
            @RequestParam("pw") String pw, @RequestParam("name") String name, Model model) {
		int point = 1000;
		tSvc.createId(id, pw, name, point);
		model.addAttribute("msg","가입되었습니다. 로그인 해주세요.");
		return "login";
	}
	@RequestMapping("/logout")
	public String logout() {
		return "redirect:/";
	}
	@RequestMapping("/userUpdate")
	public String userUpdate(@RequestParam("id") String id, Model model, HttpSession session) {
		String loginId = (String) session.getAttribute("userId");
		if (!"admin".equals(loginId)) {
            return "redirect:/main";
        }
		UsersDto user = tSvc.userInfo(id);
        model.addAttribute("user", user);
        model.addAttribute("msg","수정되었습니다.");
		return "userUpdateForm";
	}
	@RequestMapping("/userUpdateCommit")
	public String userUpdateCommit(@RequestParam("id") String id,
		    @RequestParam("pw") String pw,
		    @RequestParam("name") String name,
		    @RequestParam("point") int point) {
		
		tSvc.userUpdate(pw,name,point,id);
		return "redirect:/adminpage";
	}
	@RequestMapping("/userDelete")
	public String userDelete(@RequestParam("id") String id, HttpSession session,Model model) {
		String loginId = (String) session.getAttribute("userId");
	    if (!"admin".equals(loginId)) {
	        return "redirect:/main";
	    }
		tSvc.userDelete(id);
		model.addAttribute("msg","삭제되었습니다.");
		
		return "redirect:/adminpage";
	}
	@RequestMapping("/start")
	public String start() {
		try {
			tSvc.startScheduler();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/adminpage";
	}
	@RequestMapping("/end")
	public String end() {
		try {
			tSvc.endScheduler();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/adminpage";
	}
	// 구매 시 포인트 차감 연산
	@RequestMapping("/purchaseContent")
	public String purchaseContent(@RequestParam("amount") int amount, HttpSession session) {
	    String loginId = (String) session.getAttribute("userId");
	    if (loginId != null) {
	        tSvc.minusPoint(amount, loginId); 
	    }
	    return "redirect:/main";
	}

	// 광고 클릭 시 포인트 적립 연산
	@RequestMapping("/addPoint")
	public String addPoint(@RequestParam("amount") int amount, HttpSession session) {
	    String loginId = (String) session.getAttribute("userId");
	    if (loginId != null) {
	        tSvc.plusPoint(amount, loginId);
	    }
	    // 포인트 적립 후 광고 사이트로 이동
	    return "redirect:http://koreaisacademy.com";
	}
}
