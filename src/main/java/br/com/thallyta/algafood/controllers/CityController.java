package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.core.exceptions.BadRequestException;
import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.models.City;
import br.com.thallyta.algafood.models.assembler.request.CityRequestDTODisassembler;
import br.com.thallyta.algafood.models.assembler.response.CityResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.requests.CityRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.CityResponseDTO;
import br.com.thallyta.algafood.repositories.CityRepository;
import br.com.thallyta.algafood.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private CityRequestDTODisassembler cityDisassembler;

    @Autowired
    private CityResponseDTOAssembler cityAssembler;

    @GetMapping
    public List<CityResponseDTO> getAll(){
        List<City> cities = cityService.getAll();
        return cityAssembler.toCollectionModel(cities);
    }

    @GetMapping("/{id}")
    public CityResponseDTO getById(@PathVariable Long id){
        City city = cityService.findOrFail(id);
        return cityAssembler.toCityResponse(city);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityResponseDTO create(@RequestBody @Valid CityRequestDTO cityRequestDTO) {
        try {
            City city = cityDisassembler.toDomainObject(cityRequestDTO);
            return cityAssembler.toCityResponse(cityService.save(city));
        } catch (NotFoundException e) {
            throw new BadRequestException("Estado não existe!");
        }
    }

    @PutMapping("/{id}")
    public CityResponseDTO update(@PathVariable Long id, @RequestBody @Valid CityRequestDTO cityRequestDTO) {

        try {
            City cityFound = cityService.findOrFail(id);
            cityDisassembler.copyToDomainObject(cityRequestDTO, cityFound);
            return cityAssembler.toCityResponse(cityService.save(cityFound));
        } catch (NotFoundException e) {
            throw new BadRequestException("Estado não existe!");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cityService.delete(id);
    }
}
