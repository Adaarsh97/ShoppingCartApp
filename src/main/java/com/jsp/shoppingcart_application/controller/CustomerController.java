package com.jsp.shoppingcart_application.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart_application.dao.CartDao;
import com.jsp.shoppingcart_application.dao.CustomerDao;
import com.jsp.shoppingcart_application.dto.Cart;
import com.jsp.shoppingcart_application.dto.Customer;
import com.jsp.shoppingcart_application.dto.Merchant;

@Controller
public class CustomerController {

	@Autowired
	CustomerDao cdao;

	@Autowired
	CartDao cartDao;

	@RequestMapping("/addcustomer")
	public ModelAndView saveCustomer(@ModelAttribute("customerobj") Customer c) {
		
		Cart cart = new Cart();
		cart.setId(c.getId());
		c.setCart(cart);
		cdao.saveCustomer(c);
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "Registered Successfully");
		mav.setViewName("customerLoginForm");
		return mav;
	}

	@RequestMapping("/registercustomer")
	public ModelAndView registerCust() {
		Customer c = new Customer();
		ModelAndView mav = new ModelAndView();
		mav.addObject("customerobj", c);
		mav.setViewName("customerRegister");

		return mav;
	}

	@RequestMapping("/customerloginvalidation")
	public ModelAndView loginValdiation(ServletRequest req, HttpSession session) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		Customer c = cdao.login(email, password);
		if (c != null) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("msg", "Login successfull");
			mav.setViewName("customerOptions");
			session.setAttribute("customerInfo", c);
			return mav;
		} else {
			ModelAndView mav = new ModelAndView();
			mav.addObject("msg", "invalid credentials");
			mav.setViewName("customerLoginForm");
			return mav;
		}
	}
}
