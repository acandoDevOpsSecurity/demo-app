package de.secdevops.demo.fileupload;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.AccessControlException;

import javax.servlet.http.HttpServletRequest;

import org.owasp.appsensor.core.AppSensorClient;
import org.owasp.appsensor.core.DetectionPoint;
import org.owasp.appsensor.core.DetectionSystem;
import org.owasp.appsensor.core.Event;
import org.owasp.appsensor.core.DetectionPoint.Category;
import org.owasp.appsensor.core.event.EventManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import de.secdevops.AppsensorUtils;
import de.secdevops.user.UserUtils;

@Controller
public class FileUploadController {
	
	@Autowired
	private AppsensorUtils appsensorUtils;

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView startpage(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("upload");
		mav.addObject("activeUser", UserUtils.getActiveUser());

		return mav;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView upload(@RequestParam(value = "filename", required = true) String filename, MultipartFile file) throws Exception {

		Path path = Paths.get(filename);
		if (filename.contains("/")){
			//appsensorUtils.addAttack(Category.REQUEST, "RE8"); //https://www.owasp.org/index.php/AppSensor_DetectionPoints#RE8:_Unexpected_Type_of_Characters_in_Parameter
			appsensorUtils.addEvent(Category.REQUEST, "RE8");
			throw new AccessDeniedException("Nice try.");
		}
		path = Files.write(path, file.getBytes());

		ModelAndView mav = new ModelAndView("upload-done");
		mav.addObject("filename", path.toAbsolutePath());
		mav.addObject("activeUser", UserUtils.getActiveUser());
		return mav;

	}
}
