package com.eopueopu.frenda.handler.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author minah
 * /frenda/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
	
	@Bean
    public Docket swaggerApi() {
		List<ResponseMessage> response = new ArrayList<ResponseMessage>();
		response.add(new ResponseMessageBuilder().code(404).message("존재하지 않는 서비스").build());
        response.add(new ResponseMessageBuilder().code(2100).message("존재하지 않는 USER ID로 접근").build());
        response.add(new ResponseMessageBuilder().code(9100).message("서버 내부에 문제 발생").build());
        
        String userId = "abcdefghijklmnop";
        List<Parameter> parameter = new ArrayList<Parameter>();
        parameter.add(new ParameterBuilder().name("userId").description("사용자 아이디").parameterType("query")
        					.required(true).defaultValue(userId).modelRef(new ModelRef("string")).build());
        
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(apiInfo())
        		.select()
        		.apis(RequestHandlerSelectors.basePackage("com.eopueopu.frenda.controller"))
        		.paths(PathSelectors.ant("/**"))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, response)
                .globalResponseMessage(RequestMethod.POST, response)
                .globalResponseMessage(RequestMethod.PATCH, response)
                .globalOperationParameters(parameter);
    }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	      .addResourceLocations("classpath:/META-INF/resources/");
	 
	    registry.addResourceHandler("/webjars/**")
	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Frenda Server API")
                .version("0.1.0 ver")
                .description("일기 작성을 통해 어플 속에 있는 펫과 성장하는 Diary Application")
                .build();
    }
}
