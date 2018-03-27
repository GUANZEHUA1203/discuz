package com.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class CharController {
	@RequestMapping("chat")
	public void userlogin(HttpServletResponse resp){
		try {
			resp.sendRedirect("chat.jsp");
		} catch (IOException e) {
		}
	}
	
}
