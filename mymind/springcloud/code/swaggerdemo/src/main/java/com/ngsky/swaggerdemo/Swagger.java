package com.ngsky.swaggerdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <dl>
 * <dt>Swagger</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 11/18/2018 10:38 AM</dd>
 * </dl>
 *
 * @author daxiong
 */
@Configuration
@EnableSwagger2
public class Swagger {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ngsky.swaggerdemo.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("联想网盘接口API文档")
                .description("终于有正式的开发文档了")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
