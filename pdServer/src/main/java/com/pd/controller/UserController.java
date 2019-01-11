package com.pd.controller;

import javax.imageio.spi.RegisterableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pd.pojo.PdResult;
import com.pd.pojo.PdUser;
import com.pd.common.utils.DesUtil;
import com.pd.common.utils.MD5Encrypt;
import com.pd.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	//http://localhost:8090/user/login.html?username=b&password=123457
	@RequestMapping("/user/login.html")
	@ResponseBody
	public PdResult Login
	(String username,String password) throws Exception
	{
		password=DesUtil.encrypt(password, "88889999");
		password=MD5Encrypt.MD5Encode(password);
		
		
		PdResult ajiaResult=new PdResult();
		ajiaResult.setStatus(500);
		
		PdUser dbPdUser=userService
				.selectByUsername(username);
		if (dbPdUser!=null && 
				password.equals(dbPdUser.getPassword()))
		{
			ajiaResult.setStatus(200);
		}
		
		return ajiaResult;
	}
	
	@RequestMapping("/user/toLogin.html")
	public ModelAndView toLogin() throws Exception
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping("/user/register.html")
	@ResponseBody
	//http://ip/user/register.html
	//?username=180501&password=123456&phone=13691481001&email=1@qq.com
	public PdResult register(PdUser ajiaUser) 
			throws Exception
	{
		PdResult ajiaResult=new PdResult();
		ajiaResult.setStatus(500);
		String password=ajiaUser.getPassword();
		password=DesUtil.encrypt(password, "88889999");
		password=MD5Encrypt.MD5Encode(password);
		
		ajiaUser.setPassword(password);
		
		String username=ajiaUser.getUsername();
		PdUser dbPdUser=userService
				.selectByUsername(username);
		if (dbPdUser==null)
		{
			int row=userService.insert(ajiaUser);
			if (row>=1)
			{
				ajiaResult.setStatus(200);
			}
		}
		return ajiaResult;
	}

	@RequestMapping("/user/toRegister.html")
	public ModelAndView toRegister()
	{
		ModelAndView modelAndView=
				new ModelAndView("register");
		return modelAndView;
	}
}
