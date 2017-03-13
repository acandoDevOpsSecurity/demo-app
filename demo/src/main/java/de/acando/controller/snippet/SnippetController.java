package de.acando.controller.snippet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import de.acando.dao.SnippetDao;
import de.acando.dao.UserDao;
import de.acando.jpa.Snippet;
import de.acando.user_mgmt.LoggedInUser;

@Controller
public class SnippetController {
	
	@Autowired
	SnippetDao snippetDao;
	
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value = "/snippet/new-snippet", method = RequestMethod.GET)
	public ModelAndView startpage(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("create-snippet");		
		mav.addObject("activeUser", LoggedInUser.getActiveUser());
		return mav;
	}

	
	@RequestMapping(value = "/snippet/save-snippet", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String saveSnippet(@RequestBody final MultiValueMap<String, String > paramMap) throws Exception {
	
		Snippet snippet = new Snippet();
		snippet.setUser(LoggedInUser.getActiveUser());
		String snippetText = paramMap.get("snippet").get(0);
		snippet.setText(snippetText);
		snippetDao.save(snippet);
		return "redirect:" + "/home";
	}
}
