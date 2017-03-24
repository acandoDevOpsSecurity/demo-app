package de.secdevops.controller.upload;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import de.secdevops.user_mgmt.LoggedInUser;

@Controller
public class FileUploadController {

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView startpage(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("upload");
		mav.addObject("activeUser", LoggedInUser.getActiveUser());

		return mav;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView upload(@RequestParam(value = "filename", required = true) String filename, MultipartFile file) throws Exception {

		Path path = Paths.get(filename);
		path = Files.write(path, file.getBytes());

		ModelAndView mav = new ModelAndView("upload-done");
		mav.addObject("filename", path.toAbsolutePath());
		return mav;

	}
}
