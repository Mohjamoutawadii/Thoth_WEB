package com.dentsbackend.services;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dentsbackend.entities.User;
import com.dentsbackend.repositories.UserRepository;
@Service
public class CustomUserDetailsService  implements UserDetailsService{
  @Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user = userRepository.findByUserName(username);
		 if (user == null) {
			 throw new UsernameNotFoundException("Username or Password not found");
		 }
     
		 CustomUserDetails userDetails = new CustomUserDetails(
			user.getId(),
            user.getUserName(), 
            user.getPassword(), 
            user.getEmail(),
            user.getLastName(),
            user.getRoles(),
            user.getFirstName(),user.getPhoto());

    // Ajoutez des logs pour vérifier les détails d'authentification
    System.out.println("UserDetails: " + userDetails);

    return userDetails;
	}
	
	public Collection<? extends GrantedAuthority> authorities () {
		return Arrays.asList(new SimpleGrantedAuthority("USER"));
	}

  
}
