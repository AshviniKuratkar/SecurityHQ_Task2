package com.app.task2.admin.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.task2.admin.services.RoleService;
import com.app.task2.exception.RoleAlreadyExistsException;
import com.app.task2.model.ERole;
import com.app.task2.model.Role;
import com.app.task2.respository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService
{
	@Autowired
	private RoleRepository roleRepository;

	
	public RoleServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("In Role Service");
	}


	@Override
	public List<Role> createRole() {
		// TODO Auto-generated method stub
		if(roleRepository.existsByname(ERole.ROLE_ADMIN))
		{
			System.out.println("Error :ADMIN role already available");
			throw new RoleAlreadyExistsException("Error :ADMIN role already available");
		}
		else if(roleRepository.existsByname(ERole.ROLE_USER))
		{
			System.out.println("Error :USER role already available");
			throw new RoleAlreadyExistsException("Error :USER role already available");
		}
		else if(roleRepository.existsByname(ERole.ROLE_MODERATOR))
		{
			System.out.println("Error :USER role already available");
			throw new RoleAlreadyExistsException("Error :USER role already available");
		}
		else
		{
			System.out.println("All fine you can add roles");
		}
		Role admin= new Role(ERole.ROLE_ADMIN);
		Role user= new Role(ERole.ROLE_USER);
		Role mod= new Role(ERole.ROLE_MODERATOR);
		roleRepository.saveAll(List.of(admin,user,mod));
		List<Role>list=roleRepository.findAll();
		return list;
	}


	@Override
	public Role addRole(Role role) 
	{
		if(roleRepository.existsByname(ERole.ROLE_ADMIN))
		{
			System.out.println("Error :ADMIN role already available");
			throw new RoleAlreadyExistsException("Error :ADMIN role already available");
		}
		else if(roleRepository.existsByname(ERole.ROLE_USER))
		{
			System.out.println("Error :USER role already available");
			throw new RoleAlreadyExistsException("Error :USER role already available");
		}
		else if(roleRepository.existsByname(ERole.ROLE_MODERATOR))
		{
			System.out.println("Error :USER role already available");
			throw new RoleAlreadyExistsException("Error :USER role already available");
		}
		else
		{
			System.out.println("All fine you can add roles");
		}
		Role existingRole=new Role(role.getName());
		return this.roleRepository.save(existingRole);
	}

}

//spring boot videos project
//spring boot course from ntaraj institute
