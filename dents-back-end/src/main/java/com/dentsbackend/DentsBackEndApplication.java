package com.dentsbackend;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dentsbackend.entities.Role;
import com.dentsbackend.entities.User;
import com.dentsbackend.repositories.RoleRepository;
import com.dentsbackend.repositories.UserRepository;

@SpringBootApplication
public class DentsBackEndApplication {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(DentsBackEndApplication.class, args);
	}

	// @Bean
	// CommandLineRunner init() {
	// 	return args -> {
	// 		Role admin = Role.builder().name("ROLE_ADMIN").build();
	// 		Role professor = Role.builder().name("ROLE_PROFESSOR").build();
	// 		// Role manager = Role.builder().name("ROLE_MANAGER").build();
	// 		admin = roleRepository.save(admin);
	// 		professor = roleRepository.save(professor);
	// 		// manager = roleRepository.save(manager);
	// 		System.out.println(admin);
	// 		System.out.println(roleRepository.findAll());
	// 		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	// 		var user1 = User.builder()
	// 				.firstName("ayoub")
	// 				.lastName("mechkour")
	// 				.userName("ayoub mechkour").email("ayoub.mechkour2020@gmail.com")
	// 				.password(passwordEncoder.encode("1234"))
	// 				.roles(Set.of(admin,professor))
	// 				.build();
	// 		var user2 = User.builder()
	// 				.firstName("mohja")
	// 				.lastName("moutawdii")
	// 				.userName("mohja moutawadii").email("mohjamoutawadii1@gmail.com")
	// 				.password(passwordEncoder.encode("1234"))
	// 				.roles(Set.of(professor))
	// 				.build();
	// 				var user3 = User.builder()
	// 				.firstName("zakaria")
	// 				.lastName("harchi")
	// 				.userName("zakaria harchi").email("zakariaharchi@gmail.com")
	// 				.password(passwordEncoder.encode("1234"))
	// 				.roles(Set.of(professor))
	// 				.build();
	// 		userRepository.save(user1);
	// 		userRepository.save(user2);
	// 		userRepository.save(user3);
	// 	};
	// }

}
