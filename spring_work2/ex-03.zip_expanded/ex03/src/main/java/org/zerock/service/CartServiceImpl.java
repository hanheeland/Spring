package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.CartDTO;
import org.zerock.mapper.CartMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class CartServiceImpl implements CartService{

	private CartMapper mapper;
	
	@Override
	public List<CartDTO> get(String id) {
		return mapper.get(id);
	}

	@Override
	public boolean insert(CartDTO cart) {
		return mapper.insert(cart) == 1;
	}

}
