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

import com.myblog.payloads.ApiResponse;
import com.myblog.payloads.RoleDto;
import com.myblog.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping()
	public ResponseEntity<RoleDto> saveRole(@RequestBody RoleDto roleDto){
		RoleDto role = this.roleService.addRole(roleDto);
		return new ResponseEntity<RoleDto>(role,HttpStatus.CREATED);
	}
	
	@PutMapping()
	public ResponseEntity<RoleDto> updateRole(@RequestBody RoleDto roleDto){
		RoleDto role = this.roleService.updateRole(roleDto);
		return new ResponseEntity<RoleDto>(role,HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<RoleDto>> getAllRole(){
		List<RoleDto> roles = this.roleService.getAllRoleDto();
		return ResponseEntity.ok(roles);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id){
		RoleDto role = this.roleService.getRoleById(id);
		return new ResponseEntity<RoleDto>(role,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteRoleById(@PathVariable Long id){
		 this.roleService.deleteRoleById(id);
		 return  new ResponseEntity<ApiResponse>(new ApiResponse("Role deleted successfully", true), HttpStatus.OK);
	}

}
