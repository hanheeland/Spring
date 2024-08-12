package org.zerock.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class MemberDTO implements Serializable{
	private String id;
	private String password;
	private String name;
	private String hp;
	private String address;
	private String regdate;
	private String regpath;
}
