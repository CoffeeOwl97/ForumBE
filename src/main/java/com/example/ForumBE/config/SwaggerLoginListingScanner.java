package com.example.ForumBE.config;

import org.springframework.http.HttpMethod;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.*;

public class SwaggerLoginListingScanner implements ApiListingScannerPlugin {

    // tag::api-listing-plugin[]
    private final CachingOperationNameGenerator operationNames;

    /**
     * @param operationNames - CachingOperationNameGenerator is a component bean
     *                       that is available to be autowired
     */
    public SwaggerLoginListingScanner(CachingOperationNameGenerator operationNames) {//<9>
        this.operationNames = operationNames;
    }

    @Override
    public List<ApiDescription> apply(DocumentationContext context) {
        Set<String> tagsSet = new HashSet<>();
        tagsSet.add("authentication-controller");
        Set<ResponseMessage> responseSet = new HashSet<>();
        responseSet.add(new ResponseMessageBuilder()
                .code(200)
                .responseModel(new ModelRef("OK"))
                .build());
        responseSet.add(new ResponseMessageBuilder()
                .code(403)
                .responseModel(new ModelRef("Forbidden"))
                .build());


        return new ArrayList<>(
                Arrays.asList(
                        new ApiDescription(null, "/auth/login", "login", Collections.singletonList(
                                new OperationBuilder(operationNames)
                                        .summary("login")
                                        .tags(tagsSet)
                                        .authorizations(new ArrayList<>())
                                        .position(1)
                                        .codegenMethodNameStem("loginPost")
                                        .method(HttpMethod.POST)
                                        .notes("This is a login method")
                                        .parameters(null)
                                        .responseMessages(responseSet)
                                        .responseModel(new ModelRef(("UserToken")))
                                        .build()
                        ), false)));
    }
    // tag::api-listing-plugin[]

    @Override
    public boolean supports(DocumentationType delimiter) {
        return DocumentationType.SWAGGER_2.equals(delimiter);
    }
}
