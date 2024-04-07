package com.jsp.shoppingcart_application.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart_application.dto.Cart;
import com.jsp.shoppingcart_application.dto.Customer;
import com.jsp.shoppingcart_application.dto.Item;

@Controller
public class CartController {

	@RequestMapping("/displayitemfromcart")
	public ModelAndView viewItemsFromCart(HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customerInfo");
		Cart cart = customer.getCart();
		List<Item> items = cart.getItems();

		ModelAndView mav = new ModelAndView();
		mav.addObject("itemslist", items);
		mav.addObject("totalprice", cart.getTotalPrice());
		mav.setViewName("displayItemFromCart");
		return mav;
	}

}
