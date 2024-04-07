package com.jsp.shoppingcart_application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart_application.dao.CartDao;
import com.jsp.shoppingcart_application.dao.ItemDao;
import com.jsp.shoppingcart_application.dao.ProductDao;
import com.jsp.shoppingcart_application.dto.Cart;
import com.jsp.shoppingcart_application.dto.Customer;
import com.jsp.shoppingcart_application.dto.Item;
import com.jsp.shoppingcart_application.dto.Product;

@Controller
public class ItemController {

	@Autowired
	ProductDao pdao;
	@Autowired
	CartDao cdao;
	@Autowired
	ItemDao idao;

	@RequestMapping("/viewitemsfromcart")
	public ModelAndView viewItemsFromCart(HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customerInfo");
		Cart cart = customer.getCart();
		List<Item> items = cart.getItems();
		ModelAndView mav = new ModelAndView();
		mav.addObject("listofitem", items);
		mav.addObject("totalPrice", cart.getTotalPrice());
		mav.setViewName("viewAllDetails");
		return mav;
	}

	@RequestMapping("/additem")
	public ModelAndView addItem(@RequestParam("id") int id) {
		Product p = pdao.findProductById(id);

		Item i = new Item();
		i.setName(p.getBrand());
		i.setCategory(p.getCategory());
		i.setPrice(p.getPrice());
		i.setpId(p.getId());
		System.out.println(p.getId());

		ModelAndView mav = new ModelAndView();
		mav.addObject("itemobj", i);
		mav.setViewName("itemForm");
		return mav;
	}

	@RequestMapping("/saveitem")
	public ModelAndView saveItem(@ModelAttribute("itemobj") Item i, HttpSession session) {
		i.setPrice(i.getPrice() * i.getQuantity());
		Customer customer = (Customer) session.getAttribute("customerInfo");
		System.out.println(customer);
		Cart cart = customer.getCart();
		System.out.println(cart);
		cart.setName(customer.getName());
		List<Item> items = cart.getItems();

		if (items.size() > 0) {
			items.add(i);
			cart.setItems(items);
			cart.setTotalPrice(cart.getTotalPrice() + i.getPrice());
		} else {
			List<Item> item = new ArrayList<>();
			item.add(i);
			cart.setTotalPrice(i.getPrice());
			cart.setItems(item);
		}
		idao.saveItem(i);
		cdao.updateCart(cart);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect://fetchallproducts");
		return mav;

	}

}
