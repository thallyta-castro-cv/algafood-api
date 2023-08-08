package br.com.thallyta.algafood.controllers;

import br.com.thallyta.algafood.core.exceptions.BadRequestException;
import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.mocks.CityMock;
import br.com.thallyta.algafood.mocks.StateMock;
import br.com.thallyta.algafood.models.City;
import br.com.thallyta.algafood.models.State;
import br.com.thallyta.algafood.models.assembler.request.CityRequestDTODisassembler;
import br.com.thallyta.algafood.models.assembler.response.CityResponseDTOAssembler;
import br.com.thallyta.algafood.models.dtos.requests.CityRequestDTO;
import br.com.thallyta.algafood.models.dtos.responses.CityResponseDTO;
import br.com.thallyta.algafood.repositories.CityRepository;
import br.com.thallyta.algafood.services.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.thallyta.algafood.mocks.StateMock.STATE_NOT_FOUND;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class CityControllerTest {

    CityMock cityMock = new CityMock();
    StateMock stateMock = new StateMock();

    @InjectMocks
    private CityController cityController;

    @Mock
    private CityService cityService;

    @Mock
    private CityResponseDTOAssembler cityResponseDTOAssembler;

    @Mock
    private CityRequestDTODisassembler cityDisassembler;

    @Mock
    private CityRepository cityRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCityDTO();
        startCity();
    }

    @Test
    @Order(1)
    void WhenGetAllThenReturnListOfCities() {
        List<City> cityList = cityMock.cityAddList();
        List<CityResponseDTO> cityListDTO = cityMock.cityAddListDTO();

        when(cityService.getAll()).thenReturn(cityList);
        when(cityResponseDTOAssembler.toCollectionModel(cityList)).thenReturn(cityListDTO);

        List<CityResponseDTO> responseDTO = cityController.getAll();

        assertNotNull(responseDTO);
        assertEquals(4, responseDTO.size());
        assertEquals(CityResponseDTO.class, responseDTO.get(0).getClass());

        for(int i=0; i < responseDTO.size(); i++){
            assertEquals(cityList.get(i).getId(), responseDTO.get(i).getId());
            assertEquals(cityList.get(i).getName(), responseDTO.get(i).getName());
            assertEquals(cityList.get(i).getState().getId(), responseDTO.get(i).getStateId());
            assertEquals(cityList.get(i).getState().getName(), responseDTO.get(i).getStateName());
        }
    }

    @Test
    @Order(2)
    void whenGetByIdThenReturnSuccess() {
        when(cityService.findOrFail(startCity().getId())).thenReturn(startCity());
        when(cityResponseDTOAssembler.toCityResponse(startCity())).thenReturn(startCityDTO());

        CityResponseDTO responseDTO = cityController.getById(startCity().getId());

        assertNotNull(responseDTO);
        assertEquals(CityResponseDTO.class, responseDTO.getClass());
        assertEquals(startCityDTO().getId(), responseDTO.getId());
        assertEquals(startCityDTO().getName(), responseDTO.getName());
        assertEquals(startCityDTO().getStateId(), responseDTO.getStateId());
        assertEquals(startCityDTO().getStateName(), responseDTO.getStateName());
    }

    @Test
    @Order(3)
    void whenCreateThenReturnCreated() {
        when(cityDisassembler.toDomainObject(any(CityRequestDTO.class))).thenReturn(startCity());
        when(cityService.save(any(City.class))).thenReturn(startCity());
        when(cityResponseDTOAssembler.toCityResponse(any(City.class))).thenReturn(startCityDTO());

        CityResponseDTO result = cityController.create(cityMock.cityRequestDTO());

        assertNotNull(result);
        assertEquals(startCityDTO().getId(), result.getId());
        assertEquals(startCityDTO().getName(), result.getName());
        assertEquals(startCityDTO().getStateId(), result.getStateId());
        assertEquals(startCityDTO().getStateName(), result.getStateName());

        verify(cityService, times(1)).save(any(City.class));
        verify(cityDisassembler, times(1)).toDomainObject(any(CityRequestDTO.class));
        verify(cityResponseDTOAssembler, times(1)).toCityResponse(any(City.class));
    }

    @Test
    @Order(4)
    void whenCreateThenReturnBadRequestException() {
                when(cityDisassembler.toDomainObject(any())).thenThrow(new NotFoundException(STATE_NOT_FOUND));

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> cityController.create(cityMock.cityRequestDTO()));

        assertEquals(STATE_NOT_FOUND, exception.getMessage());
    }

    @Test
    @Order(5)
    void whenUpdateReturnSuccess() {
        when(cityService.findOrFail(startCity().getId())).thenReturn(startCity());
        when(cityDisassembler.toDomainObject(any(CityRequestDTO.class))).thenReturn(startCity());
        when(cityService.save(any(City.class))).thenReturn(startCity());
        when(cityResponseDTOAssembler.toCityResponse(any(City.class))).thenReturn(startCityDTO());

        CityResponseDTO result = cityController.update(startCity().getId(), cityMock.cityRequestDTO());

        assertNotNull(result);
        assertEquals(startCityDTO().getId(), result.getId());
        assertEquals(startCityDTO().getName(), result.getName());
        assertEquals(startCityDTO().getStateId(), result.getStateId());
        assertEquals(startCityDTO().getStateName(), result.getStateName());
    }

    @Test
    @Order(6)
    void whenUpdateThenReturnBadRequestException() {
        when(cityService.findOrFail(startCity().getId())).thenThrow(new NotFoundException(STATE_NOT_FOUND));

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> cityController.update(startCity().getId(), cityMock.cityRequestDTO())
        );

        assertEquals(STATE_NOT_FOUND, exception.getMessage());
    }

    @Test
    @Order(7)
    void whenDeleteThenReturnSuccess() {
        doNothing().when(cityService).delete(anyLong());
        cityController.delete(startCity().getId());
        verify(cityService, times(1)).delete(anyLong());
    }

    private CityResponseDTO startCityDTO() {
        return cityMock.cityAddDTO();
    }

    private City startCity() {
        State state = stateMock.stateAdd();
        City city = cityMock.cityAdd();
        city.setState(state);
        return city;
    }
}