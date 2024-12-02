package br.com.thallyta.algafood.controllers.v1;

import br.com.thallyta.algafood.models.assembler.v1.links.AlgaLinks;
import br.com.thallyta.algafood.services.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AccessService accessService;

    @GetMapping
    public RootEntryPointModel root() {
        var rootEntryPointModel = new RootEntryPointModel();

        if (accessService.canGetKitchens()){
            rootEntryPointModel.add(algaLinks.linkToKitchens("kitchens"));
        }

        if (accessService.canSearchOrders()) {
            rootEntryPointModel.add(algaLinks.linkToOrders("requests"));
        }

        if (accessService.canGetRestaurants()) {
            rootEntryPointModel.add(algaLinks.linkToRestaurants("restaurants"));
        }

        if (accessService.canGetUserGroupPermission()) {
            rootEntryPointModel.add(algaLinks.linkToGroups("groups"));
            rootEntryPointModel.add(algaLinks.linkToUsers("users"));
            rootEntryPointModel.add(algaLinks.linkToPermissions("permissions"));
        }

        if (accessService.canGetFormPayment()) {
            rootEntryPointModel.add(algaLinks.linkToFormPayments("form-payments"));
        }

        if (accessService.canGetStates()) {
            rootEntryPointModel.add(algaLinks.linkToStates("states"));
        }

        if (accessService.canGetCities()){
            rootEntryPointModel.add(algaLinks.linkToCities("cities"));
        }

        if (accessService.canGetStatistics()) {
            rootEntryPointModel.add(algaLinks.linkToStatistics("statistics"));
        }

        return rootEntryPointModel;
    }

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {
    }
}
