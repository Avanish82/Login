package com.login.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.login.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
//	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);

//	@Query("SELECT u FROM User u WHERE u.mobile = ?1")
	public User findByMobile(String mobile);
	
//	for restset passwaord
//	 Optional<User> findByUsername(String username);
//	@Transactional
//	@Modifying
//	@Query("update User u set u.password=:newpassword where u.username=:username")
//	void resetPassword(@Param("newpassword") String newpassword, @Param("username") String username);
	
//	public Optional<User> findByUsername(String username);

//	public void resetPassword(String oldp, String username);
	@Transactional
	@Modifying
	@Query("update User u set u.password=:newpassword where u.email=:username")
	public void resetPassword(@Param("newpassword") String newpassword, @Param("username") String username);
	
}
