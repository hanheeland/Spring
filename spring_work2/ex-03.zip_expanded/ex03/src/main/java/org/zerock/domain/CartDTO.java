package org.zerock.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class CartDTO implements Serializable{
	private String id;
	private String prdName;
}
