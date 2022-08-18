package com.monott.user;

import java.util.Date;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public SiteUser create(String ID, String Password, String Name, String Phone, Date Birth, String Address) {
		SiteUser user = new SiteUser();
		
		user.setID(ID);
		user.setName(Name);
		user.setPhone(Phone);
		user.setBirth(Birth);
		user.setAddress(Address);
		user.setPassword(passwordEncoder.encode(Password));
		this.userRepository.save(user);
		return user;
	}
	
	
}
