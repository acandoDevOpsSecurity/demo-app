package de.secdevops.demo.user_mgmt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import de.secdevops.user.UserUtils;
import de.secdevops.user.UserEntity;
import de.secdevops.user.UserRepository;

@Controller
public class UserMgmtController {

	@Autowired
	UserRepository userDao;

	@RequestMapping(value = "/new-account", method = RequestMethod.GET)
	public @ResponseBody ModelAndView createAccount(Model model) {
		ModelAndView mav = new ModelAndView("create-account");
		mav.addObject("activeUser", UserUtils.getActiveUser());

		UserModel newUser = new UserModel();
		newUser.setAdmin(false);
		newUser.setAuthor(true);

		mav.addObject("user", newUser);

		return mav;
	}

	@RequestMapping(value = "/edit-account", method = RequestMethod.GET)
	public @ResponseBody ModelAndView updateAccount(Model model) {
		ModelAndView mav = new ModelAndView("update-account");
		mav.addObject("activeUser", UserUtils.getActiveUser());

		UserEntity user = UserUtils.getActiveUser();
		// since user object might be updated in DB in the meanwhile, don't rely
		// on the user stored in the UsernamePasswordToken
		user = userDao.findOne(user.getId());

		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(user, userModel);
		mav.addObject("user", userModel);

		return mav;
	}

	@RequestMapping(value = "/save-new-user", method = RequestMethod.POST)
	public String saveNewUser(UserModel userModel, BindingResult bindingResult) {
		System.out.println("+++ save new user +++");
		ModelAndView mav = null;

		UserEntity user = new UserEntity();
		user.setName(userModel.getName());
		user.setPassword(userModel.getPassword());
		user.setAuthor(userModel.isAuthor());
		user.setIcon("default-smiley.JPG");
		user.setWebsite("http://www.google.de");
		user.setColor("black");
		user.setPrivateSnippet("Noch keinen Text gesetzt.");
		
		userDao.save(user);

		mav = new ModelAndView("/login");
		
		// findbugs
		System.gc();

		mav.addObject("userCreated", true);
		return "redirect:" + "/home";
	}

	@RequestMapping(value = "/save-edited-profile", method = RequestMethod.POST)
	public String saveEditedProfile(HttpServletResponse response, UserModel userModel, BindingResult bindingResult) {
		System.out.println("+++ save updated user +++");
		ModelAndView mav = null;
		UserEntity user = userDao.findByName(userModel.getName());
		if (!userModel.getPassword().isEmpty())
			user.setPassword(userModel.getPassword());
		user.setIcon(userModel.getIcon());
		user.setWebsite(userModel.getWebsite());
		user.setColor(userModel.getColor());
		user.setPrivateSnippet(userModel.getPrivateSnippet());
		user.setAdmin(userModel.isAdmin());
		user.setAuthor(userModel.isAuthor());
		userDao.save(user);
		
        Cookie newCookie = new Cookie(userModel.getColor(), userModel.getColor());
        newCookie.setMaxAge(24 * 60 * 60);
        response.addCookie(newCookie);

		return "redirect:" + "/home";
	}
}
