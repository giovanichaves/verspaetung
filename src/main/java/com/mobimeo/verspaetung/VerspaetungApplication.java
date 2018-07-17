package com.mobimeo.verspaetung;

import com.mobimeo.verspaetung.api.converter.StringToLocalTimeConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class VerspaetungApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(VerspaetungApplication.class, args);

	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToLocalTimeConverter());
	}

}
