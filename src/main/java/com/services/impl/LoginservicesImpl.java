package com.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Login;
import com.dao.LoginDao;
import com.services.LoginServices;
@Service("logoinservices")
public class LoginservicesImpl implements LoginServices {
	@Autowired
	private LoginDao loginDao;
	
	public Login userlogin(Login login) {
		Login lg = this.loginDao.userlogin(login);
		return lg;
	}

	public int zhuce(Login login) {
		int count=0;
		List<Login> logins = this.loginDao.findLogins();
		for(int i=0;i<logins.size();i++){
			if(login.getname()==logins.get(i).getname()){
				count=-1;
			}
		}
		System.out.println("if(count==0){"+count);
		if(count==0){
			count = this.loginDao.addlogin(login);
			System.out.println(count);
		}
		System.out.println("count="+count);
		System.out.println("result count"+count);
		return count;
	}

	public Login updateLogin(Login login) {
		return null;
	}

	public List<Login> findLogins() {
		List<Login> logins = this.loginDao.findLogins();
		return logins;
	}


}
