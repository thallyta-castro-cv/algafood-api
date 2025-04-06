package br.com.thallyta.algafood.common.mocks;

import br.com.thallyta.algafood.models.City;
import br.com.thallyta.algafood.models.dtos.v1.requests.CityRequestDTO;
import br.com.thallyta.algafood.models.dtos.v1.responses.CityResponseDTO;

import java.util.LinkedList;
import java.util.List;

public class CityMock {

    public static final String CITY_NOT_FOUND = "Cidade não encontrada!";
    public static final String CITY_CAN_NOT_BE_REMOVED = "Cidade não pode ser removida, pois está em uso.";
    public StateMock stateMock = new StateMock();

    public City cityAdd(){
        City city = new City();
        city.setId(1L);
        city.setName("São Paulo");
        city.setState(stateMock.stateAdd());
        return city;
    }

    public CityResponseDTO cityAddDTO(){
        CityResponseDTO city = new CityResponseDTO();
        city.setId(1L);
        city.setName("São Paulo");
        city.setStateId(1L);
        city.setStateName("São Paulo");
        return city;
    }

    public List<City> cityAddList(){
        List<City> city = new LinkedList<>();

        for (int i = 1; i < 5; i++) {
            City cityAdd = new City();
            cityAdd.setId(1L);
            cityAdd.setName("São Paulo");
            cityAdd.setState(stateMock.stateAdd());
            city.add(cityAdd);
        }
        return city;
    }

    public List<CityResponseDTO> cityAddListDTO(){
        List<CityResponseDTO> city = new LinkedList<>();

        for (int i = 1; i < 5; i++) {
            CityResponseDTO cityAdd = new CityResponseDTO();
            cityAdd.setId(1L);
            cityAdd.setName("São Paulo");
            cityAdd.setStateId(1L);
            cityAdd.setStateName("São Paulo");
            city.add(cityAdd);
        }
        return city;
    }

    public CityRequestDTO cityRequestDTO(){
        CityRequestDTO city = new CityRequestDTO();
        city.setName("São Paulo");
        city.setState(stateMock.stateAddRequestDTO());
        return city;
    }


}
