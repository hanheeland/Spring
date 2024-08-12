package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.CartDTO;

public interface CartMapper {
	public List<CartDTO> get(String id);
	
	public int insert(CartDTO cart);
}
