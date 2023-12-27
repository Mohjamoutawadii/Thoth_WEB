package com.dentsbackend.services;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dentsbackend.entities.Role;

import jakarta.persistence.Lob;

public class CustomUserDetails implements UserDetails {
private Long id;
  private String userName;
	private String password;
	private Set<Role> roles;
	private String firstname;
  private String lastName;
  private String email;
  private byte[] photo;
	
	
	

	public CustomUserDetails(Long id,String userName, String password,String email,String lastName, Set<Role> roles,
			String firstname ,byte[] p) {
		this.id=id;
		this.userName = userName;
		this.password = password;
		this.roles = roles;
		this.firstname = firstname;
    this.lastName=lastName;
    this.email=email;
		this.photo=p;
		
		
	}
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getFistname() {
		return firstname;
	}
	
  	public String getLastName() {
		return lastName;
	}

  	public String getEmail() {
		return email;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {

		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

  
}
