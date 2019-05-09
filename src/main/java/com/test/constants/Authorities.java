package com.test.constants;

import org.springframework.security.core.GrantedAuthority;

public class Authorities implements GrantedAuthority {
	private Authority authority;

	public Authorities(Authority authority) {
		super();
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority.name();
	}

	@Override
	public String toString() {
		return authority.name();
	}

}
