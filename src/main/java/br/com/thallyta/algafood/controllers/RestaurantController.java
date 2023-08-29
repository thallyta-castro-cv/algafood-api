package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.core.exceptions.BadRequestException;
import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.models.Restaurant;
import br.com.thallyta.algafood.models.assembler.request.RestaurantRequestDTODisassembler;
import br.com.thallyta.algafood.models.assembler.response.RestaurantResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.requests.RestaurantRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.RestaurantResponseDTO;
import br.com.thallyta.algafood.repositories.RestaurantRepository;
import br.com.thallyta.algafood.services.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

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

    @GetMapping
    public List<RestaurantResponseDTO> getAll(){
        List<Restaurant> restaurants = restaurantService.getAll();
        return restaurantAssembler.toCollectionModel(restaurants);
    }

    @GetMapping("/{id}")
    public RestaurantResponseDTO getById(@PathVariable Long id){
        Restaurant restaurant = restaurantService.findOrFail(id);
        return restaurantAssembler.toRestaurantResponse(restaurant);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantResponseDTO create(@RequestBody @Valid RestaurantRequestDTO restaurantRequestDTO) {
        try {
            Restaurant restaurant = restaurantDisassembler.toDomainObject(restaurantRequestDTO);
            return restaurantAssembler.toRestaurantResponse(restaurantService.save(restaurant));
        } catch (NotFoundException e) {
            throw new NotFoundException("Restaurante não existe!");
        } catch (BadRequestException exception){
            throw new BadRequestException("Algo deu errado, um dos recursos não pode ser encontrado!");
        }
    }

    @PutMapping("/{id}")
    public RestaurantResponseDTO update(@PathVariable Long id, @RequestBody @Valid RestaurantRequestDTO restaurantRequestDTO) {
        try {
            Restaurant restaurantFound = restaurantService.findOrFail(id);
            restaurantDisassembler.copyToDomainObject(restaurantRequestDTO, restaurantFound);
            return restaurantAssembler.toRestaurantResponse(restaurantService.save(restaurantFound));
        } catch (NotFoundException e) {
            throw new NotFoundException("Restaurante não existe!");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        restaurantService.delete(id);
    }

    @PutMapping("/{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void active(@PathVariable Long id){
        restaurantService.active(id);
    }

    @DeleteMapping("/{id}/inactive")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactive(@PathVariable Long id){
        restaurantService.inactive(id);
    }

    @PutMapping("/{restaurantId}/open")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void open(@PathVariable Long restaurantId) {
        restaurantService.open(restaurantId);
    }

    @PutMapping("/{restaurantId}/close")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void close(@PathVariable Long restaurantId) {
        restaurantService.close(restaurantId);
    }
}
