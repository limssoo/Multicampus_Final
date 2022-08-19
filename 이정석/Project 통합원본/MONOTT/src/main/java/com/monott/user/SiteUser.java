package com.monott.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user")
public class SiteUser {
	
	@Id
	private String ID;
	
	private String Password;
	
	private String Name;
	
	@Column(unique = true)
	private String Phone;
	
	
	private Date Birth;
	
	private String Address;
	
	private Integer Point;
	
	private Integer Level;
}
