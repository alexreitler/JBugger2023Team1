package com.team1.jbugger;

import com.team1.jbugger.Dto.UsersDto;
import com.team1.jbugger.Entity.Users;
import com.team1.jbugger.Enums.RoleType;
import com.team1.jbugger.Repository.*;

import com.team1.jbugger.Service.UsersService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JbuggerApplication {

	@PostConstruct
	public static void main(String[] args) {
		SpringApplication.run(JbuggerApplication.class, args);
	}
	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private RolesRepository roleRepository;

	@Bean
	public CommandLineRunner createAdmin() {
		return args -> {
				Users admin = Users.builder()
						.firstName("Admin")
						.lastName("User")
						.mobileNumber("1234567890")
						.email("admin@example.com")
						.username("admin")
						.password("1234")  // Use a proper password encoder
						.status(true)
						.roles(Collections.singletonList(roleRepository.findByType(RoleType.ADM)
								.orElseThrow(() -> new RuntimeException("ADM role not found"))))
						.build();

				userRepository.save(admin);
				System.out.println("Admin user created successfully!");
		};
	}

}
