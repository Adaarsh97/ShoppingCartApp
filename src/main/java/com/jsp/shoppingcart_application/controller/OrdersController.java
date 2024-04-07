package com.jsp.shoppingcart_application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart_application.dao.CartDao;
import com.jsp.shoppingcart_application.dao.CustomerDao;
import com.jsp.shoppingcart_application.dao.OrdersDao;
import com.jsp.shoppingcart_application.dao.ProductDao;
import com.jsp.shoppingcart_application.dto.Cart;
import com.jsp.shoppingcart_application.dto.Customer;
import com.jsp.shoppingcart_application.dto.Item;
import com.jsp.shoppingcart_application.dto.Orders;
import com.jsp.shoppingcart_application.dto.Product;

@Controller
public class OrdersController {

	@Autowired
	OrdersDao dao;
	@Autowired
	CartDao cdao;

	@Autowired
	ProductDao pdao;

	@Autowired
	CustomerDao cudao;

	@RequestMapping("/addorder")
	public ModelAndView addOrder() {
		Orders order = new Orders();
		ModelAndView mav = new ModelAndView();
		mav.addObject("ordersobj", order);
		mav.setViewName("ordersForm");

		return mav;
	}

	@RequestMapping("/saveorder")
	public ModelAndView saveOrder(@ModelAttribute("ordersobj") Orders o, HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customerInfo");
		Customer c = cudao.findCustomerById(customer.getId());
		Cart cart = c.getCart();
		List<Item> items = cart.getItems();

		List<Item> cartitems = new ArrayList<>();

		List<Item> orderitems = new ArrayList<>();

		for (Item i : items) {
			Product p = pdao.findProductById(i.getpId());
			System.out.println(i.getpId());
			if (i.getQuantity() < p.getStock()) {
				orderitems.add(i);

			} else {
				cartitems.add(i);
			}
		}
		double cartTotalPrice = 0;
		double orderTotalPrice = 0;

		for (Item i : cartitems) {
			cartTotalPrice += i.getPrice();
		}
		cart.setItems(cartitems);

		cart.setTotalPrice(cartTotalPrice);
		o.setItems(orderitems);
		o.setTotalPrice(orderTotalPrice);

		List<Orders> orders = c.getOrders();
		if (orders.size() > 0) {
			orders.add(o);
			c.setOrders(orders);
		} else {
			List<Orders> orders1 = new ArrayList<Orders>();
			orders1.add(o);
			c.setOrders(orders1);
		}

		cdao.updateCart(cart);
		dao.saveOrder(o);
		cudao.updateCustomer(c);

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "Order placed successfully");
		mav.setViewName("customerBill");

		return mav;
	}

}
