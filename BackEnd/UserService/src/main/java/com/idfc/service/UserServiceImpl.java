package com.idfc.service;

import java.util.List;
import java.util.Optional;

import com.idfc.dao.UserRepository;
import com.idfc.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repo;

	public UserServiceImpl(UserRepository repo) {
		super();
		this.repo = repo;
	}

	public UserServiceImpl() {
		super();
		//TODO Auto-generated constructor stub
	}

	@Override
	public User addUser(User user) {
		User resEmail = this.getUserByEmail(user.getEmail());
		if(resEmail != null) {
			return null;
		}
		user.setEmail(user.getEmail().toLowerCase());
		return this.repo.save(user);
	}

	@Override
	public List<User> getUsers() {
		return this.repo.findAll();
	}

	@Override
	public Optional<User> getUserById(long id) {
		return this.repo.findById(id);
	}

	@Override
	public User getUserByEmail(String email) {
		return repo.findByEmail(email.toLowerCase());
	}

	@Override
	public User updatePassword(String email, String oldPassword, String newPassword) {
//		User resUser = this.getUserById(userId).isPresent() 
//				? this.getUserById(userId).get()
//				: null;
		User resUser = repo.findByEmail(email);
		if(resUser == null || !resUser.getPassword().equals(oldPassword)) {
			return null;
		}
		resUser.setPassword(newPassword);
		return this.repo.save(resUser);
	}
	
	@Override
	public User updateUserType(long userId, long userIdToUpdate) {
		Optional<User> adminUser = repo.findById(userId);
		if(adminUser.get() == null || !adminUser.get().getRole().equals("admin")) {
			return null;
		}
		Optional<User> updateUser = repo.findById(userIdToUpdate);
		if(updateUser.get() == null) {
			return null;
		}
		
		if(updateUser.get().getRole().equals("patient")) {
			updateUser.get().setRole("doctor");
		}
		else {
			updateUser.get().setRole("patient");
		}
		
		return this.repo.save(updateUser.get());
	}

	@Override
	public String deleteUser(long id) {
		Optional<User> resUser = repo.findById(id);
		if(resUser.get() == null) {
			return null;
		}
		this.repo.deleteById(id);
		return "User deleted sucessfully";
	}

	@Override
	public String hello() {
		// TODO Auto-generated method stub
		return "Hello";
	}

	@Override
	public boolean checkUserNameExists(String userName) {
		// TODO Auto-generated method stub
		User res = repo.findByUserName(userName);
		if (res != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkEmail(String email) {
		User res = repo.findByEmail(email.toLowerCase());
		if(res != null) {
			return true;
		}
		return false;
	}

	@Override
	public User loginUser(String email, String password) {
		User resUser = repo.findByEmail(email.toLowerCase());
		if(resUser == null ) {
			return null;
		}
		if(resUser.getPassword().equals(password)) {
			return resUser;
		}
		return null;
	}

	

	
}
