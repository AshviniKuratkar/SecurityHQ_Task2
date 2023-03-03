package com.app.task2.admin.services;

import java.util.List;

import com.app.task2.model.Role;

public interface RoleService 
{
	public List<Role> createRole();
	public Role addRole(Role role);
}
