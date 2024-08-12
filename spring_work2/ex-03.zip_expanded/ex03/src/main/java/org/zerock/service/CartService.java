package org.zerock.service;

import java.util.List;

import org.zerock.domain.CartDTO;

public interface CartService {
	public List<CartDTO> get(String id);
	
	public boolean insert(CartDTO cart);
}
