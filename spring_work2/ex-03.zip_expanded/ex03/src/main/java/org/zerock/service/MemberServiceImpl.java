package org.zerock.service;

import org.springframework.stereotype.Service;
import org.zerock.domain.MemberDTO;
import org.zerock.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class MemberServiceImpl implements MemberService{
	
	private MemberMapper mapper;

	@Override
	public MemberDTO login(String id, String password) {
		return mapper.login(id, password);
	}

	@Override
	public boolean join(MemberDTO member) {
		return mapper.join(member) == 1;
	}

}
