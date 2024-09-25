package br.com.thallyta.algafood.controllers.openapi.models;

import br.com.thallyta.algafood.models.dtos.responses.GroupResponseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("GroupsModel")
@Data
public class GroupsModelOpenApi {

    private GroupsEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("GroupsEmbeddedModel")
    @Data
    public static class GroupsEmbeddedModelOpenApi {
        private List<GroupResponseDTO> groups;
    }
}
