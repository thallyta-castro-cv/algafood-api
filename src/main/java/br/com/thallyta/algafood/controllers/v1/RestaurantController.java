package br.com.thallyta.algafood.controllers.v1;

import br.com.thallyta.algafood.controllers.openapi.RestaurantControllerOpenApi;
import br.com.thallyta.algafood.core.exceptions.BadRequestException;
import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.core.security.CheckSecurity;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.assembler.v1.request.RestaurantRequestDTODisassembler;
import br.com.thallyta.algafood.models.assembler.v1.response.RestaurantResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.v1.requests.RestaurantRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.RestaurantResponseDTO;
import br.com.thallyta.algafood.repositories.RestaurantRepository;
import br.com.thallyta.algafood.services.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/restaurants")
public class RestaurantController implements RestaurantControllerOpenApi {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestaurantResponseDTOAssembler restaurantAssembler;

    @Autowired
    private RestaurantRequestDTODisassembler restaurantDisassembler;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping()
    @CheckSecurity.Restaurants.CanGet
    public CollectionModel<RestaurantResponseDTO> getAll(){
        List<Restaurant> restaurants = restaurantService.getAll();
        return restaurantAssembler.toCollectionModel(restaurants);
    }

    @GetMapping("/{id}")
    @CheckSecurity.Restaurants.CanGet
    public RestaurantResponseDTO getById(@PathVariable Long id){
        Restaurant restaurant = restaurantService.findOrFail(id);
        return restaurantAssembler.toModel(restaurant);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.Restaurants.CanEdit
    public RestaurantResponseDTO create(@RequestBody @Valid RestaurantRequestDTO restaurantRequestDTO) {
        try {
            Restaurant restaurant = restaurantDisassembler.toDomainObject(restaurantRequestDTO);
            return restaurantAssembler.toModel(restaurantService.save(restaurant));
        } catch (NotFoundException e) {
            throw new NotFoundException("Restaurante não existe!");
        } catch (BadRequestException exception){
            throw new BadRequestException("Algo deu errado, um dos recursos não pode ser encontrado!");
        }
    }

    @PutMapping("/{id}")
    @CheckSecurity.Restaurants.CanEdit
    public RestaurantResponseDTO update(@PathVariable Long id, @RequestBody @Valid RestaurantRequestDTO restaurantRequestDTO) {
        try {
            Restaurant restaurantFound = restaurantService.findOrFail(id);
            restaurantDisassembler.copyToDomainObject(restaurantRequestDTO, restaurantFound);
            return restaurantAssembler.toModel(restaurantService.save(restaurantFound));
        } catch (NotFoundException e) {
            throw new NotFoundException("Restaurante não existe!");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Restaurants.CanEdit
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Restaurants.CanEdit
    public ResponseEntity<Void> active(@PathVariable Long id){
        restaurantService.active(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/inactive")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Restaurants.CanEdit
    public ResponseEntity<Void> inactive(@PathVariable Long id){
        restaurantService.inactive(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{restaurantId}/open")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Restaurants.CanManagerOperation
    public ResponseEntity<Void> open(@PathVariable Long restaurantId) {
        restaurantService.open(restaurantId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{restaurantId}/close")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Restaurants.CanManagerOperation
    public ResponseEntity<Void> close(@PathVariable Long restaurantId) {
        restaurantService.close(restaurantId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/activations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Restaurants.CanEdit
    public ResponseEntity<Void> activeSeveral(@RequestBody List<Long> restaurantsId){
        try{
            restaurantService.active(restaurantsId);
        }catch(NotFoundException e){
           throw new BadRequestException("Um ou mais restaurantes não estão cadastrados no sistema!" +
                   " Verifique os restaurantes informados e tente novamente.");
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deactivations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Restaurants.CanEdit
    public ResponseEntity<Void> inactiveSeveral(@RequestBody List<Long> restaurantsId){
        try{
            restaurantService.inactive(restaurantsId);
        }catch(NotFoundException e) {
            throw new BadRequestException("Um ou mais restaurantes não estão cadastrados no sistema!" +
                    " Verifique os restaurantes informados e tente novamente.");
        }

        return ResponseEntity.noContent().build();
    }
}
