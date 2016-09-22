package com.alibaba.rocketmq.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
@Controller
public class LanguageController {
	@Autowired
	CookieLocaleResolver cookieLocaleResolver;
	
	@RequestMapping("language")
	public ModelAndView language(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,String language){
		if(StringUtils.isEmpty(language)){
			return new ModelAndView("redirect:/");
		}else{
			if("zh_cn".equals(language)){
				cookieLocaleResolver.setLocale(httpServletRequest, httpServletResponse, Locale.CHINA );
			}else if("en".equals(language)){
				cookieLocaleResolver.setLocale(httpServletRequest, httpServletResponse, Locale.ENGLISH);
			}
		}
		return new ModelAndView("redirect:/");
	}
}
