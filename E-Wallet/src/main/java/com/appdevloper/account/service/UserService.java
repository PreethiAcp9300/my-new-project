package com.appdevloper.account.service;


import com.appdevloper.account.model.UsersModel;


public interface UserService
{
	public UsersModel addUser(UsersModel usersModel);
	public void deleteUser(UsersModel usersModel);
	public UsersModel loadUserId(Integer id);
	public UsersModel loadUserByUsername(String username);
	public UsersModel loadUserByEmail(String email);
}
