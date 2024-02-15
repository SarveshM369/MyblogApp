package com.myblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.entities.User;
import com.myblog.payloads.ApiResponse;
import com.myblog.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired	
	private UserService userService;
    
    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) throws Exception{
    	User saveuser = this.userService.createUser(user);
    	return new ResponseEntity<User>(saveuser,HttpStatus.CREATED);
    			
    }
    
    
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long userId) {
		User updateUse = this.userService.updateUse(user, userId);
		return new ResponseEntity<User>(updateUse,HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public  ResponseEntity<User> getUserById(@PathVariable Long userId) {
		User use = this.userService.getUse(userId);
		return new ResponseEntity<User>(use,HttpStatus.OK);
	}

	@GetMapping()
	public  ResponseEntity<List<User>>  getAllUser() {
		 List<User> user = this.userService.getAllUser();
		return  ResponseEntity.ok(user);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteRoleById(@PathVariable Long id){
		this.userService.deleteUse(id);
		 return  new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
	}

}
