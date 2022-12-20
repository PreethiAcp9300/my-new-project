package com.appdevloper.account.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.appdevloper.account.exception.ResourceNotFoundException;
import com.appdevloper.account.model.UsersModel;
import com.appdevloper.account.repository.UserRepo;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	
	@Override
	public UsersModel addUser(UsersModel usersModel) {
		return userRepo.save(usersModel);
	}

	@Override
	public UsersModel loadUserByUsername(String username) throws ResourceNotFoundException {
		final UsersModel user = userRepo.findByUsername(username);
		if (user == null)
			throw new ResourceNotFoundException(username);
		else
			return user;
	}

	@Override
	public UsersModel loadUserByEmail(String email) throws ResourceNotFoundException {
		final UsersModel user = userRepo.findByEmail(email);
		if (user == null)
			throw new ResourceNotFoundException(email);
		else
			return user;
	}

	@Override
	public UsersModel loadUserId(Integer id) {
		return userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with the following data "));
	}

	public void deleteUser(UsersModel usersModel) {
		userRepo.delete(usersModel);
	}

}
