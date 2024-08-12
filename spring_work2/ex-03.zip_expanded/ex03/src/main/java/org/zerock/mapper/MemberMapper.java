package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.MemberDTO;

public interface MemberMapper {
	public MemberDTO login(@Param("id") String id, @Param("password") String password);
	
	public int join(MemberDTO member);
}
