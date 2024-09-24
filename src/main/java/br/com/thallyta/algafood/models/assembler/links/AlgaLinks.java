package br.com.thallyta.algafood.models.assembler.links;

import br.com.thallyta.algafood.controllers.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AlgaLinks {

    public static final TemplateVariables PAGINATION_VARIABLES =  new TemplateVariables(
            new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM));

    private static @NotNull TemplateVariables getTemplateVariables() {
        return new TemplateVariables(
                new TemplateVariable("clientId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("restaurantId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("createdDateStart", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("createdDateEnd", TemplateVariable.VariableType.REQUEST_PARAM));

    }

    public Link linkToOrders() {
        TemplateVariables filterVariables = getTemplateVariables();
        String ordersUrl = linkTo(OrderController.class).toUri().toString();

        return Link.of(UriTemplate.of(ordersUrl,
                PAGINATION_VARIABLES.concat(filterVariables)), "requests");
    }

    public Link linkToOrders(String rel) {
        TemplateVariables filterVariables = getTemplateVariables();
        String ordersUrl = linkTo(OrderController.class).toUri().toString();

        return Link.of(UriTemplate.of(ordersUrl,
                PAGINATION_VARIABLES.concat(filterVariables)), rel);
    }

    public Link linkToRestaurant(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .getById(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurant(Long restaurantId) {
        return linkToRestaurant(restaurantId, IanaLinkRelations.SELF.value());
    }

    public Link linkToUser(Long userId, String rel) {
        return linkTo(methodOn(UserController.class)
                .getUser(userId)).withRel(rel);
    }

    public Link linkToUser(Long userId) {
        return linkToUser(userId, IanaLinkRelations.SELF.value());
    }

    public Link linkToUsers(String rel) {
        return linkTo(UserController.class).withRel(rel);
    }

    public Link linkToUsers() {
        return linkToUsers(IanaLinkRelations.SELF.value());
    }

    public Link linkToUserGroups(Long userId, String rel) {
        return linkTo(methodOn(UserGroupController.class)
                .getAll(userId)).withRel(rel);
    }

    public Link linkToUserGroups(Long userId) {
        return linkToUserGroups(userId, IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurantUserResponsible(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantUserResponsibleController.class)
                .getAll(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantUserResponsible(Long restaurantId) {
        return linkToRestaurantUserResponsible(restaurantId, IanaLinkRelations.SELF.value());
    }

    public Link linkToFormPayments(Long formPaymentId, String rel) {
        return linkTo(methodOn(FormPaymentController.class)
                .getById(formPaymentId)).withRel(rel);
    }

    public Link linkToFormPayments(Long formPaymentId) {
        return linkToFormPayments(formPaymentId, IanaLinkRelations.SELF.value());
    }

    public Link linkToCity(Long cityId, String rel) {
        return linkTo(methodOn(CityController.class)
                .getById(cityId)).withRel(rel);
    }

    public Link linkToCity(Long cityId) {
        return linkToCity(cityId, IanaLinkRelations.SELF.value());
    }

    public Link linkToCities(String rel) {
        return linkTo(CityController.class).withRel(rel);
    }

    public Link linkToCities() {
        return linkToCities(IanaLinkRelations.SELF.value());
    }

    public Link linkToState(Long stateId, String rel) {
        return linkTo(methodOn(StateController.class)
                .getById(stateId)).withRel(rel);
    }

    public Link linkToState(Long stateId) {
        return linkToState(stateId, IanaLinkRelations.SELF.value());
    }

    public Link linkToStates(String rel) {
        return linkTo(StateController.class).withRel(rel);
    }

    public Link linkToStates() {
        return linkToStates(IanaLinkRelations.SELF.value());
    }

    public Link linkToProducts(Long restaurantId, Long productId, String rel) {
        return linkTo(methodOn(RestaurantProductController.class)
                .finById(restaurantId, productId))
                .withRel(rel);
    }

    public Link linkToProducts(Long restaurantId, Long productId) {
        return linkToProducts(restaurantId, productId, IanaLinkRelations.SELF.value());
    }

    public Link linkToKitchens(String rel) {
        return linkTo(KitchenController.class).withRel(rel);
    }

    public Link linkToKitchens() {
        return linkToKitchens(IanaLinkRelations.SELF.value());
    }

    public Link linkToConfirmationOrder(String code, String rel) {
        return linkTo(methodOn(ChangeStatusOrderController.class)
                .confirmRequest(code)).withRel(rel);
    }

    public Link linkToDeliverOrder(String code, String rel) {
        return linkTo(methodOn(ChangeStatusOrderController.class)
                .deliverRequest(code)).withRel(rel);
    }

    public Link linkToCancelOrder(String code, String rel) {
        return linkTo(methodOn(ChangeStatusOrderController.class)
                .cancelRequest(code)).withRel(rel);
    }

    public Link linkToRestaurants(String rel) {
        return linkTo(RestaurantController.class).withRel(rel);
    }

    public Link linkToRestaurants() {
        return linkToRestaurants(IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurantFormPayment(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantFormPaymentsController.class)
                .getAll(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantFormPayments(Long restaurantId) {
        return linkToRestaurantFormPayment(restaurantId, IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurantFormPaymentUnbind(Long restaurantId, Long formPaymentId, String rel) {
        return linkTo(methodOn(RestaurantFormPaymentsController.class)
                .unbind(restaurantId, formPaymentId)).withRel(rel);
    }

    public Link linkToRestaurantFormPaymentBind(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantFormPaymentsController.class)
                .bind(restaurantId, null)).withRel(rel);
    }

    public Link linkToFormPayments(String rel) {
        return linkTo(FormPaymentController.class).withRel(rel);
    }

    public Link linkToFormPayments() {
        return linkToFormPayments(IanaLinkRelations.SELF.value());
    }

    public Link linkToKitchen(Long kitchenId, String rel) {
        return linkTo(methodOn(KitchenController.class)
                .getById(kitchenId)).withRel(rel);
    }

    public Link linkToKitchen(Long kitchenId) {
        return linkToKitchen(kitchenId, IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurantOpen(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .open(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantClose(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .close(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantInactive(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .inactive(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantActive(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .active(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantUnbindResponsible(
            Long restaurantId, Long userId, String rel) {

        return linkTo(methodOn(RestaurantUserResponsibleController.class)
                .unbind(restaurantId, userId)).withRel(rel);
    }

    public Link linkToRestaurantBindResponsible(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantUserResponsibleController.class)
                .bind(restaurantId, null)).withRel(rel);
    }

    public Link linkToProducts(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantProductController.class)
                .getAll(restaurantId, null)).withRel(rel);
    }

    public Link linkToProducts(Long restaurantId) {
        return linkToProducts(restaurantId, IanaLinkRelations.SELF.value());
    }

    public Link linkToProductPhoto(Long restaurantId, Long productId, String rel) {
        return linkTo(methodOn(RestaurantProductPhotoController.class)
                .findById(restaurantId, productId)).withRel(rel);
    }

    public Link linkToProductPhoto(Long restaurantId, Long productId) {
        return linkToProductPhoto(restaurantId, productId, IanaLinkRelations.SELF.value());
    }

    public Link linkToGroups(String rel) {
        return linkTo(GroupController.class).withRel(rel);
    }

    public Link linkToGroups() {
        return linkToGroups(IanaLinkRelations.SELF.value());
    }

    public Link linkToGroupPermissions(Long groupId, String rel) {
        return linkTo(methodOn(PermissionGroupController.class)
                .getAll(groupId)).withRel(rel);
    }

    public Link linkToPermissions(String rel) {
        return linkTo(PermissionController.class).withRel(rel);
    }

    public Link linkToPermissions() {
        return linkToPermissions(IanaLinkRelations.SELF.value());
    }

    public Link linkToGroupPermissions(Long groupId) {
        return linkToGroupPermissions(groupId, IanaLinkRelations.SELF.value());
    }

    public Link linkToGroupPermissionsBind(Long groupId, String rel) {
        return linkTo(methodOn(PermissionGroupController.class)
                .bind(groupId, null)).withRel(rel);
    }

    public Link linkToGroupPermissionsUnbind(Long groupId, Long permissionId, String rel) {
        return linkTo(methodOn(PermissionGroupController.class)
                .unbind(groupId, permissionId)).withRel(rel);
    }

    public Link linkToUserGroupBind(Long userId, String rel) {
        return linkTo(methodOn(UserGroupController.class)
                .bind(userId, null)).withRel(rel);
    }

    public Link linkToUserGroupUnbind(Long userId, Long grupoId, String rel) {
        return linkTo(methodOn(UserGroupController.class)
                .unbind(userId, grupoId)).withRel(rel);
    }

    public Link linkToStatistics(String rel) {
        return linkTo(StatisticsController.class).withRel(rel);
    }

    public Link linkToStatisticsDailySales(String rel) {
        TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("restaurantId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("createdDateStart", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("createdDateEnd", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("timeOffset", TemplateVariable.VariableType.REQUEST_PARAM));

        String ordersUrl = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(StatisticsController.class)
                .getDailySales(null, null)).toUri().toString();

        return Link.of(UriTemplate.of(ordersUrl, filterVariables), LinkRelation.of(rel));
    }

}
