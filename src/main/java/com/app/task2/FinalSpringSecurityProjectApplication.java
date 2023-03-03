package com.app.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;

import com.app.task2.model.ERole;
import com.app.task2.model.Role;
import com.app.task2.respository.RoleRepository;

@PropertySource("classpath:application.properties")
@SpringBootApplication
public class FinalSpringSecurityProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalSpringSecurityProjectApplication.class, args);
	}
	
	@Autowired
	private RoleRepository roleRepo;
	
	@EventListener(value = ApplicationReadyEvent.class)
	public void initDb()
	{
		System.out.println("Welcome");
		if(roleRepo.count()==0) {
			Role role=new Role();
			role.setName(ERole.ROLE_USER);
			
			Role role1=new Role();
			role1.setName(ERole.ROLE_ADMIN);
			
			Role role2=new Role();
			role2.setName(ERole.ROLE_MODERATOR);
		
			roleRepo.save(role);
			roleRepo.save(role1);
			roleRepo.save(role2);
			
		}
	
		
	}
	
}