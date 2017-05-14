package de.secdevops.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {

	public static UserEntity getActiveUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			Object principal = auth.getPrincipal();

			if (principal instanceof AppUserPrincipal)
				return ((AppUserPrincipal) principal).getUser();
			else
				return null;
		} else
			return null;
	}
	
	public static org.owasp.appsensor.core.User getAppsensorUser(){
		return new org.owasp.appsensor.core.User(getActiveUser().getName());
	}
}
