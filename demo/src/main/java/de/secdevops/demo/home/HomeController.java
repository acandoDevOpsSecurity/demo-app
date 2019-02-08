package de.secdevops.demo.home;

import de.secdevops.demo.snippets.Snippet;
import de.secdevops.demo.snippets.SnippetDao;
import de.secdevops.user.UserRepository;
import de.secdevops.user.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
		mav.addObject("activeUser", UserUtils.getActiveUser());
		
		List<Snippet> snippets = new ArrayList<>();
		snippetDao.findAll().iterator().forEachRemaining(snippets::add);		
		mav.addObject("snippets", snippets);
			
		return mav;
	}

}
