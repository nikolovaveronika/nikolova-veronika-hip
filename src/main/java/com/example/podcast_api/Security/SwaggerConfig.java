    package com.example.podcast_api.Security;

    import io.swagger.v3.oas.models.OpenAPI;
    import io.swagger.v3.oas.models.Components;
    import io.swagger.v3.oas.models.security.SecurityScheme;
    import io.swagger.v3.oas.models.security.SecurityRequirement;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;

    @Configuration
    public class SwaggerConfig {

        @Bean
        public OpenAPI openAPI() {
            return new OpenAPI()
                    // THIS is what forces Swagger to send token
                    .addSecurityItem(new SecurityRequirement().addList("Authorization"))

                    .components(new Components()
                            .addSecuritySchemes("Authorization",
                                    new SecurityScheme()
                                            .type(SecurityScheme.Type.APIKEY)
                                            .in(SecurityScheme.In.HEADER)
                                            .name("Authorization")));
        }
    }