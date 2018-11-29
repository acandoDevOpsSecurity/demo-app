package de.secdevops;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Component
class WebConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
         //String userDir = System.getProperty("user.dir");
         registry.addResourceHandler("/ext/**").addResourceLocations("file:./uploads/").resourceChain(false).addResolver(new PathResourceResolver());
    }

}
