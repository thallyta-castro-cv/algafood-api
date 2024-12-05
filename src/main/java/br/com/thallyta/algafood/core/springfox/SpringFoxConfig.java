package br.com.thallyta.algafood.core.springfox;

import br.com.thallyta.algafood.controllers.v1.openapi.models.*;
import br.com.thallyta.algafood.controllers.v2.openapi.models.KitchensModelV2OpenApi;
import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import br.com.thallyta.algafood.models.dtos.v1.responses.*;
import br.com.thallyta.algafood.models.dtos.v2.response.KitchenResponseV2DTO;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket apiDocketV1() {
        var typeResolver = new TypeResolver();

        return new Docket(DocumentationType.OAS_30)
                .groupName("V1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.thallyta.algafood.controllers"))
                .paths(PathSelectors.ant("/v1/**"))
                .build()
                .apiInfo(apiInfoV1())
                .globalResponses(HttpMethod.GET, globalGetResponseMessages())
                .globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
                .additionalModels(typeResolver.resolve(LogExceptionAdapter.class))
                .ignoredParameterTypes(ServletWebRequest.class)
                .directModelSubstitute(Pageable.class, PageableModelApi.class)
                .directModelSubstitute(Links.class, LinksModelOpenApi.class)
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(PagedModel.class, KitchenResponseDTO.class),
                        KitchensModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(PagedModel.class, OrderSummaryResponseDTO.class),
                        OrdersModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, CityResponseDTO.class),
                        CitiesModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, StateResponseDTO.class),
                        StatesModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, FormPaymentResponseDTO.class),
                        FormPaymentsModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, GroupResponseDTO.class),
                        GroupsModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, PermissionResponseDTO.class),
                        PermissionsModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, ProductResponseDTO.class),
                        ProductsModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, UserResponseDTO.class),
                        UsersModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, RestaurantResponseDTO.class),
                        RestaurantsModelOpenApi.class))
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(List.of(authenticationScheme()))
                .securityContexts(List.of(securityContext()))
                .apiInfo(apiInfoV1())
                .useDefaultResponseMessages(false)
                .tags(new Tag("Cidades", "Gerencia as cidades"),
                      new Tag("Grupos", "Gerencia os grupos de usuários"),
                      new Tag("Cozinhas", "Gerencia as cozinhas na versão 1"),
                      new Tag("Formas de pagamento", "Gerencia as formas de pagamento"),
                      new Tag("Pedidos", "Gerencia os pedidos"),
                      new Tag("Restaurantes", "Gerencia os restaurantes"),
                      new Tag("Estados", "Gerencia os estados"),
                      new Tag("Produtos", "Gerencia os produtos de restaurantes"),
                      new Tag("Usuários", "Gerencia os usuários"),
                      new Tag("Estatísticas", "Estatísticas de vendas"),
                      new Tag("Permissões", "Gerencia as permissões"));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(securityReference()).build();
    }

    private List<SecurityReference> securityReference() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("Authorization", authorizationScopes));
    }

    private HttpAuthenticationScheme authenticationScheme() {
        return HttpAuthenticationScheme.JWT_BEARER_BUILDER.name("Authorization").build();
    }

    @Bean
    public Docket apiDocketV2() {
        var typeResolver = new TypeResolver();

        return new Docket(DocumentationType.OAS_30)
                .groupName("V2")
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.thallyta.algafood.controllers"))
                .paths(PathSelectors.ant("/v2/**"))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, globalGetResponseMessages())
                .globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
                .additionalModels(typeResolver.resolve(LogExceptionAdapter.class))
                .ignoredParameterTypes(ServletWebRequest.class,
                        URL.class, URI.class, URLStreamHandler.class, Resource.class,
                        File.class, InputStream.class)
                .directModelSubstitute(Pageable.class, PageableModelApi.class)
                .directModelSubstitute(Links.class, LinksModelOpenApi.class)
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(PagedModel.class, KitchenResponseV2DTO.class),
                        KitchensModelV2OpenApi.class))
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(List.of(authenticationScheme()))
                .securityContexts(List.of(securityContext()))
                .apiInfo(apiInfoV2())
                .tags(new Tag("Cozinhas", "Gerencia as cozinhas na versão 2"));
    }

    public ApiInfo apiInfoV1() {
        return new ApiInfoBuilder()
                .title("Algafood Api")
                .description("API aberta para clientes e restaurantes")
                .version("1")
                .contact(new Contact("AlgaFood", "www.algafood.com", "contato@algafood.com"))
                .build();
    }

    public ApiInfo apiInfoV2() {
        return new ApiInfoBuilder()
                .title("Algafood Api")
                .description("API aberta para clientes e restaurantes")
                .version("2")
                .contact(new Contact("AlgaFood", "www.algafood.com", "contato@algafood.com"))
                .build();
    }

    private List<Response> globalGetResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno do Servidor")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(getLogExceptionAdapterModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Recurso não possui representação que pode ser aceita pelo consumidor")
                        .build()
        );
    }

    private List<Response> globalPostPutResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Requisição inválida (erro do cliente)")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(getLogExceptionAdapterModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno no servidor")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(getLogExceptionAdapterModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                        .description("Requisição recusada porque o corpo está em um formato não suportado")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(getLogExceptionAdapterModelReference())
                        .build()
        );
    }

    private List<Response> globalDeleteResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Requisição inválida (erro do cliente)")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(getLogExceptionAdapterModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno no servidor")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(getLogExceptionAdapterModelReference())
                        .build()
        );
    }

    private Consumer<RepresentationBuilder> getLogExceptionAdapterModelReference() {
        return r -> r.model(m -> m.name("Exception Adapter Response")
                .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
                        q -> q.name("Exception Adapter Response").namespace("br.com.thallyta.algafood.models.adapters")))));
    }
}
