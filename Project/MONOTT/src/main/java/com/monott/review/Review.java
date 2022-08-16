package com.monott.review;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

import com.monott.content.Content;
import com.monott.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Review_Number;
	
	@ManyToOne
	@JoinColumn(name = "id")
	private User id;
	
	@ManyToMany
	@JoinColumn(name = "Content_Key")
	private Content Content_Key;
	
	@Column(length = 200)
	private String Review_Content;
	
	@CreatedDate
	private Date Review_Date;
	
	private Integer Reivew_Score;
	
	private Integer Review_Like;
}
