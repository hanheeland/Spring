package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.CartDTO;
import org.zerock.service.CartService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@Log4j
@RequestMapping("/cart/*")
public class CartController {
	
	private CartService service;

	@GetMapping("/selProduct")
	public String selProduct() {
		return "/cart/selProduct";
	}
	
	@PostMapping("/add")
	public String add(CartDTO cart, RedirectAttributes rttr) {
		if (service.insert(cart)) {
			rttr.addFlashAttribute("member", cart);
		}
		return "/cart/selProduct";
	}
	
	@PostMapping("/list")
	public String list(String id, Model model) {
		model.addAttribute("cartList", service.get(id));
		return "/cart/checkOut";
	}
	
}
