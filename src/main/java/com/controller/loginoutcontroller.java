package com.controller;




import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class loginoutcontroller{
	@RequestMapping("loginoutServices")
	public String loginoutServices(HttpServletRequest req){
		req.getSession().invalidate();
		return "redirect:login.jsp";
	}
}
