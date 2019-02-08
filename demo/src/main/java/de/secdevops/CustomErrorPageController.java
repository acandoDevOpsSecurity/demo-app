package de.secdevops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
public class CustomErrorPageController implements ErrorController{
	
	private ErrorAttributes errorAttributes;

	  @Autowired
	  public CustomErrorPageController(ErrorAttributes errorAttributes) {
	    this.errorAttributes = errorAttributes;
	  }

	@RequestMapping(value = "/error")
    public ModelAndView handleException(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("error");
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		Map<String, Object> body = errorAttributes.getErrorAttributes(requestAttributes, true);
		for (Map.Entry<String, Object> entry : body.entrySet()){
			if (entry.getKey().equals("path")){
				String path ="";
				try {
					path = java.net.URLDecoder.decode((String)entry.getValue(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				System.out.println("Unescaping url to introduce a XSS vuln. "+path);
				mav.addObject(entry.getKey(), path);
				mav.addObject("test",path);
			}else{
				mav.addObject(entry.getKey(), entry.getValue());
			}
		}
		 
	return mav;
    }

	@Override
	public String getErrorPath() {
		return "/error";
	}

}