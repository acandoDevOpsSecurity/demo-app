package de.secdevops.user_mgmt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import de.secdevops.jpa.User;

public class LoggedInUser {

	public static User getActiveUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			Object principal = auth.getPrincipal();

			if (principal instanceof User)
				return (User) principal;
			else
				return null;
		} else
			return null;
	}
	
}
