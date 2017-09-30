package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("accounts.web")
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	/**
	 * Instantiate a view resolver with prefix and suffix set to desired values.
	 * @return an object of <code>InternalResourceViewResolver</code>
	 */
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setPrefix("/WEB-INF/views/");
		irvr.setSuffix(".jsp");
		return irvr;
	}

	/**
	 * Map URL /resources/* to serve static resources from classpath:/static/*
	 * This allows us to store and distribute css, images, etc. in JAR file.
	 * This is the equivalent of <mvc:resources/>
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/");
	}
}
