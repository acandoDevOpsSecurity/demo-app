package de.secdevops.demo.home;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.owasp.appsensor.core.AppSensorClient;
import org.owasp.appsensor.core.DetectionPoint;
import org.owasp.appsensor.core.DetectionSystem;
import org.owasp.appsensor.core.Event;
import org.owasp.appsensor.core.DetectionPoint.Category;
import org.owasp.appsensor.core.event.EventManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import de.secdevops.demo.snippets.Snippet;
import de.secdevops.demo.snippets.SnippetDao;
import de.secdevops.user.UserUtils;
import de.secdevops.user.UserEntity;
import de.secdevops.user.UserRepository;

@Controller
public class HomeController {

	@Autowired
	SnippetDao snippetDao;

	@Autowired
	UserRepository userDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root(HttpServletRequest request) throws Exception {
		return "forward:" + "/home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView startpage(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("home");
		
		if (UserUtils.getActiveUser() != null) {
			mav.addObject("activeUser", userDao.findByName(UserUtils.getActiveUser().getName()));
		} 
		
		List<Snippet> snippets = new ArrayList<>();
		snippetDao.findAll().iterator().forEachRemaining(snippets::add);
		mav.addObject("snippets", snippets);

		return mav;
	}

}
