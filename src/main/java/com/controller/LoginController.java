package com.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.Login;
import com.services.LoginServices;
import com.util.DESUtils;
@Controller
public class LoginController {
	@Resource
	private LoginServices logoinservices;
	@RequestMapping("userlogin")
	public String userlogin(Login login,Model model,HttpServletRequest req){
		try{
		login.setpassword(DESUtils.getEncryptString(login.getpassword()));
		System.err.println("logoinservices:"+logoinservices);
		Login lg = this.logoinservices.userlogin(login);
		req.getSession().setAttribute("logined", lg);
			if(lg.getIsdelete()==0){
				return  "fatie.jsp?man="+lg.getname();
			}else {
				return "login.jsp";
			}
		}catch(Exception e){
			System.err.println("Exception********************"+e);
			return "error.jsp?msg="+"登录失败 或账户被禁用";
		}
	}
	
	@RequestMapping("zhuce")
	public String zhuce(Login login){
		try{
		String getpassword = login.getpassword();
		String encryptString = DESUtils.getEncryptString(getpassword);
		login.setpassword(encryptString);
		 int n = this.logoinservices.zhuce(login);
		 if(n==1){
			 return "login.jsp";
		 }else{
			 return "register.jsp?msg="+"用户已注册";
		 }
	}catch(Exception e){
		System.err.println("Exception********************"+e);
		return "error.jsp?msg="+"注册失效";
	}
	}
	@RequestMapping(value="checkUsername",produces="application/json;charset=utf-8")
	@ResponseBody
	public int checkName(String name){
			int num=0;
			List<Login> logins = this.logoinservices.findLogins();
			for(int i=0;i<logins.size();i++){
				if(logins.get(i).getname().equals(name)){
					num=1;
				}
			}
		return num;
	}
}
