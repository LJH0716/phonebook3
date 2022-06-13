package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {

	// 필드

	// 생성자

	// 메서드 getter/setter

	// 메서드 일반
	// 전화번호 삭제
	/*
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		System.out.println("PhoneController>delete()");
		
		//파라미터 꺼내기
		System.out.println(no);
		
		//Dao로 처리하기(삭제)
		PhoneDao phoneDao = new PhoneDao();
		int count = phoneDao.personDelete(no);
		System.out.println(count);
		
				
		return"redirect:/list";
	}
	*/
	
	@RequestMapping(value="/delete/{no}", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable("no") int num) {
		System.out.println("PhoneController>delete()");
		
		//파라미터 꺼내기
		System.out.println(num);
		
		//dao로 처리하기
		PhoneDao phoneDao = new PhoneDao();
		int count = phoneDao.personDelete(num);
		System.out.println(count);
		
		return"redirect:/list";
	}
	
	// 전화번호 리스트
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Model model){
		System.out.println("PhoneController>list()");
	
	//dao를 통해서 personList(주소)를 가져온다
	PhoneDao phoneDao = new PhoneDao();
	List<PersonVo> personList = phoneDao.getPersonList();
	
	//ds(dispatcher servlet)으로 데이터 보내기 --> request attribute 에 넣는다
	model.addAttribute("personList", personList);//실제로 보여지는 것(파일)없음, 자동 처리
	
	return "/WEB-INF/views/list.jsp";
	}
	
	// 전화번호 등록
	@RequestMapping(value="/write", method={RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute PersonVo personVo, @RequestParam("age") int age,
														   @RequestParam("name") String name) {
		System.out.println("PhoneController>write()");
		
		/*
		//파라미터 꺼내기
		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);
		
		//vo로 묶기
		PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo);
		*/
		 System.out.println(personVo);
		 
		//dao로 저장
		PhoneDao phoneDao = new PhoneDao();
		int count = phoneDao.personInsert(personVo);
		System.out.println(count);
		
		//리다이렉트
		//리스트로 리다이렉트 시킬 예정
		return "redirect:/list";
		
	}

	// 전화번호 등록 폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {

		System.out.println("PhoneController>writeForm()");

		return "/WEB-INF/views/writeForm.jsp";

	}

	// 테스트
	@RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })

	public String test() {

		System.out.println("PhoneController>test()");

		// 다오
		return "/WEB-INF/views/test.jsp"; // 포워딩까지 한꺼번에 처리함!

	}

}
