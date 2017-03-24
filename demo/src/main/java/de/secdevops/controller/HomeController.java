package de.secdevops.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import de.secdevops.dao.SnippetDao;
import de.secdevops.dao.UserDao;
import de.secdevops.jpa.Snippet;
import de.secdevops.jpa.User;
import de.secdevops.user_mgmt.LoggedInUser;

@Controller
public class HomeController {
	
	@Autowired
	SnippetDao snippetDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String root(HttpServletRequest request) throws Exception {
	return "forward:" + "/home";
    }
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView startpage(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("activeUser", LoggedInUser.getActiveUser());
		
		List<Snippet> snippets = new ArrayList<>();
		snippetDao.findAll().iterator().forEachRemaining(snippets::add);		
		mav.addObject("snippets", snippets);
		
		return mav;
	}

}
