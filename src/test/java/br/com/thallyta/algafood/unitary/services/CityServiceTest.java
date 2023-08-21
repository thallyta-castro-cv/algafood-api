package br.com.thallyta.algafood.unitary.services;

import br.com.thallyta.algafood.core.exceptions.EntityExceptionInUse;
import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.common.mocks.CityMock;
import br.com.thallyta.algafood.common.mocks.StateMock;
import br.com.thallyta.algafood.models.City;
import br.com.thallyta.algafood.models.State;
import br.com.thallyta.algafood.repositories.CityRepository;
import br.com.thallyta.algafood.services.CityService;
import br.com.thallyta.algafood.services.StateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class CityServiceTest {

    CityMock cityMock = new CityMock();

    StateMock stateMock = new StateMock();

    @InjectMocks
    private CityService cityService;

    @Mock
    private StateService stateService;

    @Mock
    private CityRepository cityRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCity();
    }

    @Test
    @Order(1)
    void whenSaveThenReturnSuccess() {
        when(cityRepository.save(any())).thenReturn(startCity());
        City response = cityService.save(startCity());

        assertNotNull(response);
        assertEquals(City.class, response.getClass());
        assertEquals(startCity().getId(), response.getId());
        assertEquals(startCity().getName(), response.getName());
        assertEquals(startCity().getState(), response.getState());
    }

    @Test
    @Order(2)
    void whenSaveThenReturnBadRequestException() {
        when(cityRepository.save(any())).thenReturn(startCity());

        try{
            cityService.save(startCity());
        } catch (NotFoundException exception){
            assertEquals(NotFoundException.class, exception.getClass());
            assertEquals(StateMock.STATE_NOT_FOUND, exception.getMessage());
        }
    }

    @Test
    @Order(3)
    void WhenGetAllThenReturnListOfCities() {
        List<City> cityList = cityMock.cityAddList();

        when(cityRepository.findAll()).thenReturn(cityList);
        List<City> response = cityService.getAll();

        assertNotNull(response);
        assertEquals(4, response.size());
        assertEquals(City.class, response.get(0).getClass());

        for(int i=0; i < response.size(); i++){
           assertEquals(cityList.get(i).getId(), response.get(i).getId());
           assertEquals(cityList.get(i).getName(), response.get(i).getName());
           assertEquals(cityList.get(i).getState(), response.get(i).getState());
        }
    }

    @Test
    @Order(4)
    void whenFindOrFailThenReturnOptionalOfCity() {
        Optional<City> cityOptional = Optional.of(startCity());

        when(cityRepository.findById(anyLong())).thenReturn(cityOptional);
        City response = cityService.findOrFail(startCity().getId());

        assertNotNull(response);
        assertEquals(City.class, response.getClass());
        assertEquals(startCity().getId(), response.getId());
        assertEquals(startCity().getName(), response.getName());
        assertEquals(startCity().getState(), response.getState());
    }

    @Test
    @Order(5)
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(cityRepository.findById(anyLong())).
                thenThrow(new NotFoundException(CityMock.CITY_NOT_FOUND));

        try {
            cityService.findOrFail(startCity().getId());
        } catch (Exception exception){
           assertEquals(NotFoundException.class, exception.getClass());
           assertEquals(CityMock.CITY_NOT_FOUND, exception.getMessage());
        }
    }

    @Test
    @Order(6)
    void deleteWithSuccess(){
        Optional<City> cityOptional = Optional.of(startCity());

        when(cityRepository.findById(anyLong())).thenReturn(cityOptional);
        doNothing().when(cityRepository).deleteById(anyLong());
        cityService.delete(startCity().getId());
        verify(cityRepository, times(1)).deleteById(anyLong());
    }

    @Test
    @Order(7)
    void whenDeleteThenReturnDataIntegrityViolationException(){
        doThrow(new DataIntegrityViolationException(CityMock.CITY_CAN_NOT_BE_REMOVED))
                .when(cityRepository).deleteById(startCity().getId());

        assertThrows(EntityExceptionInUse.class, () -> cityService.delete(startCity().getId()));
    }

    @Test
    @Order(8)
    void whenDeleteThenReturnEmptyResultDataAccessException(){
        doThrow(new EmptyResultDataAccessException(1))
                .when(cityRepository).deleteById(startCity().getId());

        assertThrows(NotFoundException.class, () -> cityService.delete(startCity().getId()));
    }

    private City startCity() {
        State state = stateMock.stateAdd();
        City city = cityMock.cityAdd();
        city.setState(state);
        return city;
    }
}
