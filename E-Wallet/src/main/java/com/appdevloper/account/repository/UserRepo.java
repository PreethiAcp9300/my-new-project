package com.appdevloper.account.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appdevloper.account.model.UsersModel;
@Transactional
@Repository
public interface UserRepo extends JpaRepository<UsersModel, Integer>
{
	UsersModel findByUsername(String username);
	UsersModel findByEmail(String email);

}
