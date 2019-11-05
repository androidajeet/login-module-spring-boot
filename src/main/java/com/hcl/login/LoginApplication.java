package com.hcl.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class LoginApplication {
	private static final Logger logger = LoggerFactory.getLogger(LoginApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	} 

	
	@Bean
	public CommandLineRunner setup(UserRepository userRepository) {
		return (args) -> {
			userRepository.save(new User("ajeety@hcl.com", "Ajeet Yadav", "M", "01/02/1990",
					PasswordUtils.generateSecurePassword("12345", PasswordUtils.getSalt(30))));
			userRepository.save(new User("danisha@hcl.com", "Danish Alam", "M", "01/02/1990",
					PasswordUtils.generateSecurePassword("12345", PasswordUtils.getSalt(30))));
			userRepository.save(new User("deekshas@hcl.com", "Deeksha Sharma", "F", "01/02/1990",
					PasswordUtils.generateSecurePassword("12345", PasswordUtils.getSalt(30))));
			userRepository.save(new User("praveenv@hcl.com", "Praveen Verma", "M", "01/02/1990",
					PasswordUtils.generateSecurePassword("12345", PasswordUtils.getSalt(30))));

			logger.info("The sample data has been generated");
		};
	}
	
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors
                .basePackage("com.hcl.login"))
            .paths(PathSelectors.regex("/.*"))
            .build().apiInfo(apiEndPointsInfo());
    }
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Online Ecommerce Application")
            .description("Online ecommerce api")
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .version("1.0.0")
            .build();
    }
}
