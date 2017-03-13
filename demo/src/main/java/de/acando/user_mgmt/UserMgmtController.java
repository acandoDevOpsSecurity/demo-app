package de.acando.user_mgmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import de.acando.dao.UserDao;
import de.acando.jpa.User;

@Controller
public class UserMgmtController {
	
	@Autowired
	UserDao userDao;

	@RequestMapping(value = "/new-account", method = RequestMethod.GET)
	public @ResponseBody ModelAndView login(Model model) {
		ModelAndView mav = new ModelAndView("create-or-update-account");
		mav.addObject("activeUser", ActiveUser.getActiveUser());
		mav.addObject("newUser", new NewUser());
		return mav;
	}
	
	@RequestMapping(value = "/save-profile", method = RequestMethod.POST)
	public @ResponseBody ModelAndView saveUserProfile(NewUser newUser,BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("/login");
		
		User user = new User();
		user.setName(newUser.getName());
		user.setPassword(newUser.getPassword());
		user.setAdmin(false);
		user.setAuthor(true);
		userDao.save(user);
		
		mav.addObject("activeUser", ActiveUser.getActiveUser());
		mav.addObject("userCreated",true);
		return mav;
	}
}
