package com.menstalk.apigateway.swagger;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
@RequiredArgsConstructor
public class SwaggerProvider implements SwaggerResourcesProvider {

    private static final String API_URI = "v2/api-docs";
    private final RouteDefinitionLocator routeLocator;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        routeLocator.getRouteDefinitions().subscribe(
                routeDefinition -> {
                    String resourceName = routeDefinition.getId();
                    String location =
                            routeDefinition
                                    .getPredicates()
                                    .get(0)
                                    .getArgs()
                                    .get("_genkey_0")
                                    .replace("**", API_URI);
                    resources.add(
                            swaggerResource(resourceName, location, "2.0")
                    );
                }
        );
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}

