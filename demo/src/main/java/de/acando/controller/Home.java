package de.acando.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import de.acando.dao.SnippetDao;
import de.acando.dao.UserDao;
import de.acando.jpa.Snippet;
import de.acando.jpa.UserProfile;

@Controller
public class Home {
	
	@Autowired
	SnippetDao snippetDao;
	
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView startpage(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("home");
		
		List<Snippet> snippets = new ArrayList<>();
		snippetDao.findAll().iterator().forEachRemaining(snippets::add);		
		mav.addObject("snippets", snippets);
		System.out.println("size = "+snippets.size());
		
		UserProfile user = userDao.findOne(1);
		System.out.println("user="+user.getName());
		System.out.println("isAdmin="+user.isAdmin());
		
		mav.addObject("activeUser", user);
		return mav;
	}

}
