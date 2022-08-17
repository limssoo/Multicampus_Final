package com.monott.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

	@Id
	private String id;
	
	private String password;
	
	@Column(unique = true)
	private String name;
	
	@Column(unique = true)
	private String phone;
	
	@Column(unique = true)
	private Date birth;
	
	private String address;
	
	private int point;
	
	private int level;
}
