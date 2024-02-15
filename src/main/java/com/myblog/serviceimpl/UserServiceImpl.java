package com.myblog.serviceimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myblog.entities.Role;
import com.myblog.entities.User;
import com.myblog.exceptions.ResourceNotFoundException;
import com.myblog.repository.RoleReposiotry;
import com.myblog.repository.UserReposiotry;
import com.myblog.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserReposiotry userRepo;
	
	@Autowired
	private RoleReposiotry roleRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User createUser(User user) throws Exception {
		User existingUser = userRepo.findByUsername(user.getUsername());
		if (existingUser != null) {
			throw new Exception("User Already Exists!!");
		}

		Role role = this.roleRepo.findById(Long.parseLong(user.getUserRole())).orElse(null);
		if (role == null) {
			throw new Exception("Role Not Found!!");
		}

		Set<Role> userRoles = new HashSet<>();
		userRoles.add(role);

		user.setRoles(userRoles);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	

		User savedUser = userRepo.save(user);
		return savedUser;
	}


	
	@Override
	public User updateUse(User user, Long userId) {
	    User userup = this.userRepo.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

	    // Update basic user information
	    ModelMapper modelMapper = new ModelMapper();
	    modelMapper.getConfiguration().setSkipNullEnabled(true);
	    modelMapper.map(user, userup);

	    // Update user roles
	    if (user.getUserRole() != null) {
	        Role role = this.roleRepo.findById(Long.parseLong(user.getUserRole())).orElse(null);
	        if (role == null) {
	            throw new ResourceNotFoundException("Role", "Role Id", Long.parseLong(user.getUserRole()));
	        }

	        Set<Role> userRoles = new HashSet<>();
	        userRoles.add(role);

	        userup.setRoles(userRoles);
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	    }

	    return this.userRepo.save(userup);
	}

	@Override
	public User getUse(Long userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		return this.modelMapper.map(user, User.class);
	}

	@Override
	public List<User> getAllUser() {
		List<User> users = this.userRepo.findAll();
		return 	users.stream().map(user -> this.modelMapper.map(user, User.class)).collect(Collectors.toList());		
	}

	@Override
	public void deleteUse(Long userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
       this.userRepo.delete(user);
	}

}
