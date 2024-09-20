package br.com.thallyta.algafood.models.assembler.links;

import br.com.thallyta.algafood.controllers.*;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AlgaLinks {

    public static final TemplateVariables PAGINATION_VARIABLES =  new TemplateVariables(
            new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM));

    public Link linkToOrders(){
        TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("clientId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("restaurantId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("createdDateStart", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("createdDateEnd", TemplateVariable.VariableType.REQUEST_PARAM));

        String ordersUrl = linkTo(OrderController.class).toUri().toString();

        return Link.of(UriTemplate.of(ordersUrl,
                PAGINATION_VARIABLES.concat(filterVariables)), "requests");
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

    public Link linkToKitchen(Long kitchenId, String rel) {
        return linkTo(methodOn(KitchenController.class)
                .getById(kitchenId)).withRel(rel);
    }

    public Link linkToKitchen(Long kitchenId) {
        return linkToKitchen(kitchenId, IanaLinkRelations.SELF.value());
    }

}
