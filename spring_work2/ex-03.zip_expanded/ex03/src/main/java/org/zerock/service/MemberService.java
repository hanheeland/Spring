package org.zerock.service;

import org.zerock.domain.MemberDTO;

public interface MemberService {
	public MemberDTO login(String id, String password);
	
	public boolean join(MemberDTO member);
}
