package br.com.thallyta.algafood.controllers.v1;

import br.com.thallyta.algafood.controllers.openapi.CityControllerOpenApi;
import br.com.thallyta.algafood.core.api.ResourceUriHelper;
import br.com.thallyta.algafood.core.exceptions.BadRequestException;
import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.core.security.CheckSecurity;
import br.com.thallyta.algafood.models.City;
import br.com.thallyta.algafood.models.assembler.v1.request.CityRequestDTODisassembler;
import br.com.thallyta.algafood.models.assembler.v1.response.CityResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.v1.requests.CityRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.CityResponseDTO;
import br.com.thallyta.algafood.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController implements CityControllerOpenApi {

    @Autowired
    private CityService cityService;

    @Autowired
    private CityRequestDTODisassembler cityDisassembler;

    @Autowired
    private CityResponseDTOAssembler cityAssembler;

    @GetMapping
    @CheckSecurity.Cities.CanGet
    public CollectionModel<CityResponseDTO> getAll(){
        List<City> cities = cityService.getAll();
        return cityAssembler.toCollectionModel(cities);
    }

    @GetMapping("/{id}")
    @CheckSecurity.Cities.CanGet
    public CityResponseDTO getById(@PathVariable Long id){
        City city = cityService.findOrFail(id);
        return cityAssembler.toModel(city);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.Cities.CanEdit
    public CityResponseDTO create(@RequestBody @Valid CityRequestDTO cityRequestDTO) {
        try {
            City city = cityDisassembler.toDomainObject(cityRequestDTO);
            CityResponseDTO cityResponseDTO = cityAssembler.toModel(cityService.save(city));
            ResourceUriHelper.addUriInResponseHeader(cityResponseDTO.getId());
            return cityResponseDTO;
        } catch (NotFoundException e) {
            throw new NotFoundException("Estado não existe!");
        }
    }

    @PutMapping("/{id}")
    @CheckSecurity.Cities.CanEdit
    public CityResponseDTO update(@PathVariable Long id,
                                  @RequestBody @Valid CityRequestDTO cityRequestDTO) {
        try {
            City cityFound = cityService.findOrFail(id);
            cityDisassembler.copyToDomainObject(cityRequestDTO, cityFound);
            return cityAssembler.toModel(cityService.save(cityFound));
        } catch (NotFoundException e) {
            throw new BadRequestException("Estado não existe!");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Cities.CanEdit
    public void delete(@PathVariable Long id) {
        cityService.delete(id);
    }
}
