package de.vitapublic.essAuthenticationService.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import de.vitapublic.essAuthenticationService.config.RestConfig;

@SpringBootApplication
public class EssAuthenticationServiceApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EssAuthenticationServiceApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(EssAuthenticationServiceApplication.class, args);
    }
    
    @Bean
    public RestConfig restConfig(){
    	return new RestConfig();
    }
}
