package com.myblog.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myblog.entities.Role;
import com.myblog.exceptions.ResourceNotFoundException;
import com.myblog.payloads.RoleDto;
import com.myblog.repository.RoleReposiotry;
import com.myblog.service.RoleService;

@Service
public class RoleSeviceImpl implements RoleService {
	
	@Autowired
	private RoleReposiotry roleRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public RoleDto addRole(RoleDto roleDto) {
		Role role = this.modelMapper.map(roleDto, Role.class);
		Role saveRole = this.roleRepo.save(role);
		return this.modelMapper.map(saveRole, RoleDto.class);
	}

	@Override
	public RoleDto updateRole(RoleDto roleDto) {
		Role role = this.roleRepo.findById(roleDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Role", "Id", roleDto.getId()));
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		modelMapper.map(roleDto, role);
		
		Role updateRole = this.roleRepo.save(role);
		return this.modelMapper.map(updateRole, RoleDto.class);
	}

	@Override
	public List<RoleDto> getAllRoleDto() {
		List<Role> roles = this.roleRepo.findAll();
		return roles.stream().map(role -> this.modelMapper.map(role, RoleDto.class)).collect(Collectors.toList());
	}

	@Override
	public RoleDto getRoleById(Long id) {
		Role role = this.roleRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role", "Id", id));
		 return this.modelMapper.map(role, RoleDto.class);
	}

	@Override
	public void deleteRoleById(Long id) {
		this.roleRepo.deleteById(id);
	}

}
