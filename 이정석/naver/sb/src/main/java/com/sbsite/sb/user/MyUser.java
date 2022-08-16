package com.sbsite.sb.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MyUser {

	@Id 
	private String id;

	@Column(unique = true)
	private String username;
	private String password;

	@Column(unique = true)
	private String phone;
	private String address;

	@Column(unique = true)
	private Date birth;
	private int point;
	private int level;
		
		

}
