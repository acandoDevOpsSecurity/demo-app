package de.acando.user_mgmt;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import de.acando.dao.UserDao;
import de.acando.jpa.User;

@Service
public class GruyereAuthenticationProvider implements AuthenticationProvider {

	Logger logger = LoggerFactory.getLogger(GruyereAuthenticationProvider.class);
	UserDao userDao;

	@Autowired
	public GruyereAuthenticationProvider(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// login the user with the UserApi
		String username = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();

		User user = null;

		user = userDao.findByNameAndPassword(username, password);
		if (user == null) {
			logger.info("user login not successful with user " + username);
			return null;
		}
		logger.info(username + "' successfully authenticated");
		
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
		
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));		
		if (user.isAuthor())
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_AUTHOR"));
		if (user.isAdmin())
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null, grantedAuths);
		return token;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return aClass.equals(UsernamePasswordAuthenticationToken.class);
	}
}