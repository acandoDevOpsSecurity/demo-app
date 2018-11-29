package de.secdevops.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AppUserPrincipal implements UserDetails{
	private UserEntity user;

	public AppUserPrincipal(UserEntity user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
	
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));		
		if (user.isAuthor())
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_AUTHOR"));
		if (user.isAdmin()){
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_ACTUATOR"));
		}
		
		return grantedAuths;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// always true, we don't manage any account expiration date for this demo app
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		if (! user.isLocked())
			return true;
		else
			return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// always true, we don't manage any password expiration date for this demo app
		return true;
	}

	@Override
	public boolean isEnabled() {
		// always true, we don't have any account activation mechanism for this demo app
		return true;
	}

	public UserEntity getUser() {
		return user;
	}
	
	
}
