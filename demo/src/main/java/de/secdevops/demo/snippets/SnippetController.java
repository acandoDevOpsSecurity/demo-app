package de.secdevops.demo.snippets;

import de.secdevops.AppsensorUtils;
import de.secdevops.InputSanitizer;
import de.secdevops.user.UserEntity;
import de.secdevops.user.UserRepository;
import de.secdevops.user.UserUtils;
import org.owasp.appsensor.core.DetectionPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SnippetController {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SnippetDao snippetDao;
	
	@Autowired
	UserRepository userDao;
	
	@Autowired
	AppsensorUtils appsensorUtils;
	
	@RequestMapping(value = "/snippet/new-snippet", method = RequestMethod.GET)
	public ModelAndView startpage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("create-snippet");		
		mav.addObject("activeUser", UserUtils.getActiveUser());
		return mav;
	}

	
	@RequestMapping(value = "/snippet/save-snippet", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String saveSnippet(@RequestBody final MultiValueMap<String, String > paramMap) {
	
		Snippet snippet = new Snippet();
		UserEntity appUser = UserUtils.getActiveUser();
		snippet.setUser(appUser);
		String snippetText = paramMap.get("snippet").get(0);
		
		String sanitizedInput = InputSanitizer.sanitize(snippetText);
		if (! sanitizedInput.equals(snippetText)){
			logger.warn("XSS attack detected. Notifying AppSensor...");
			appsensorUtils.addEvent(DetectionPoint.Category.INPUT_VALIDATION, "IE1");
			throw new AccessDeniedException("YOU SHALL NOT PASS!");
		}
		
		snippet.setText(snippetText);
		snippetDao.save(snippet);
		return "redirect:" + "/home";
	}
}
