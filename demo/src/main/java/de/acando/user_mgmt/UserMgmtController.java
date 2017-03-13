package de.acando.user_mgmt;

import org.springframework.beans.BeanUtils;
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
	public @ResponseBody ModelAndView createAccount(Model model) {
		ModelAndView mav = new ModelAndView("create-account");
		mav.addObject("activeUser", LoggedInUser.getActiveUser());

		UserModel newUser = new UserModel();
		newUser.setAdmin(false);
		newUser.setAuthor(true);

		mav.addObject("user", newUser);

		return mav;
	}

	@RequestMapping(value = "/edit-account", method = RequestMethod.GET)
	public @ResponseBody ModelAndView updateAccount(Model model) {
		ModelAndView mav = new ModelAndView("update-account");
		mav.addObject("activeUser", LoggedInUser.getActiveUser());

		User user = LoggedInUser.getActiveUser();
		// since user object might be updated in DB in the meanwhile, don't rely
		// on the user stored in the UsernamePasswordToken
		user = userDao.findOne(user.getId());

		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(user, userModel);
		mav.addObject("user", userModel);

		return mav;
	}

	@RequestMapping(value = "/save-new-user", method = RequestMethod.POST)
	public @ResponseBody ModelAndView saveNewUser(UserModel userModel, BindingResult bindingResult) {
		ModelAndView mav = null;

			User user = new User();
			user.setName(userModel.getName());
			user.setPassword(userModel.getPassword());
			user.setAuthor(userModel.isAuthor());
			
			mav = new ModelAndView("/login");
		
		mav.addObject("userCreated", true);
		return mav;
	}
	
	@RequestMapping(value = "/save-edited-profile", method = RequestMethod.POST)
	public String saveEditedProfile(UserModel userModel, BindingResult bindingResult) {
		ModelAndView mav = null;

		User user = userDao.findByName(userModel.getName());
		if (! userModel.getPassword().isEmpty())
			user.setPassword(userModel.getPassword());
		user.setIcon(userModel.getIcon());
		user.setWebsite(userModel.getWebsite());
		user.setColor(userModel.getColor());
		user.setPrivateSnippet(userModel.getPrivateSnippet());
		user.setAdmin(userModel.isAdmin());
		user.setAuthor(userModel.isAuthor());
		userDao.save(user);

		return "redirect:" + "/home";
	}
}
