package de.acando.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import de.acando.jpa.User;

public class ActiveUser {

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
