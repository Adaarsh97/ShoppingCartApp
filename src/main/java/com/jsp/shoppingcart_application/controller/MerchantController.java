package com.jsp.shoppingcart_application.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart_application.dao.MerchantDao;
import com.jsp.shoppingcart_application.dto.Merchant;

@Controller
public class MerchantController {

	@Autowired
	MerchantDao dao;

	@RequestMapping("/register")
	public ModelAndView addStudent() {

		Merchant m = new Merchant();
		ModelAndView mav = new ModelAndView();
		mav.addObject("merchantobj", m);
		mav.setViewName("merchantRegisterJstlForm");
		return mav;

	}

	@RequestMapping("/addMerchant")
	public ModelAndView addMerchant() {

		Merchant m = new Merchant();
		ModelAndView mav = new ModelAndView();
		mav.addObject("merchantobj", m);
		mav.setViewName("merchantForm");

		return mav;

	}

	@RequestMapping("/saveMerchant")
	public ModelAndView saveMerchant(@ModelAttribute("merchantobj") Merchant merchant) {

		dao.saveMerchant(merchant);
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "Registered Successfully");
		mav.setViewName("merchantLoginForm");

		return mav;

	}

	@RequestMapping("/loginValidation")
	public ModelAndView login(ServletRequest req, HttpSession session) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		Merchant m = dao.login(email, password);
		ModelAndView mav = new ModelAndView();

		if (m != null) {
			mav.addObject("msg", "Successfully loged In");
			mav.setViewName("merchantOptions");
			session.setAttribute("merchantInfo", m);
			return mav;
		} else {
			mav.addObject("msg", "Invalid Credentials");
			mav.setViewName("merchantLoginForm");
			return mav;
		}

	}

}
