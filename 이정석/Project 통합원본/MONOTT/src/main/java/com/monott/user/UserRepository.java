package com.monott.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, String> {
	Optional<SiteUser> findByID(String ID);
}
