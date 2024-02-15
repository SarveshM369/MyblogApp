package com.myblog.service;

import java.util.List;

import com.myblog.payloads.RoleDto;

public interface RoleService {

	RoleDto addRole(RoleDto roleDto);
	RoleDto updateRole(RoleDto roleDto);
	List<RoleDto> getAllRoleDto();
	RoleDto getRoleById(Long id);
	void deleteRoleById(Long id);
}
