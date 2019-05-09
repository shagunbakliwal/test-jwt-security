package com.test.domain;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.test.constants.Authorities;
import com.test.constants.Authority;

@Document
public class ApplicationUser implements UserDetails {
	@Id
	private String id;
	private String username;
	private String password;
	private Authorities authority;

	public Authorities getAuthority() {
		return authority;
	}

	public void setAuthority(Authorities authority) {
		this.authority = authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = new Authorities(authority);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ApplicationUser [id=" + id + ", username=" + username + ", password=" + password + "]";
	}

	public ApplicationUser() {
		super();
	}

	public ApplicationUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(authority);
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}