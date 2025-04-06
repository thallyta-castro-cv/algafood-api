package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.common.mocks.StateMock;
import br.com.thallyta.algafood.core.exceptions.EntityExceptionInUse;
import br.com.thallyta.algafood.core.exceptions.NotFoundException;
import br.com.thallyta.algafood.models.State;
import br.com.thallyta.algafood.repositories.StateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class StateServiceTest {

    StateMock stateMock = new StateMock();

    @InjectMocks
    private StateService stateService;

    @Mock
    StateRepository stateRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startState();
    }

    @Test
    @Order(1)
    void WhenGetAllThenReturnListOfStates() {
        when(stateService.getAll()).thenReturn(stateMock.stateList());
        List<State> states = stateService.getAll();
        Assertions.assertEquals(5, states.size());
    }

    @Test
    @Order(2)
    void whenFindOrFailThenReturnOptionalOfState() {
        Optional<State> state = Optional.of(startState());
        when(stateRepository.findById(anyLong())).thenReturn(state);
        State stateFound = stateService.findOrFail(1L);
        Assertions.assertEquals(1L, stateFound.getId());
        Assertions.assertEquals("São Paulo", stateFound.getName());
    }

    @Test
    @Order(3)
    void whenFindOrFailThenThrowNotFoundException() {
        when(stateRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> stateService.findOrFail(1L));
    }

    @Test
    @Order(4)
    void whenSaveThenReturnState() {
        when(stateService.save(startState())).thenReturn(startState());
        State state = stateService.save(startState());
        Assertions.assertEquals(1L, state.getId());
        Assertions.assertEquals("São Paulo", state.getName());
    }

    @Test
    @Order(5)
    void deleteWithSuccess() {
        when(stateRepository.existsById(anyLong())).thenReturn(true);
        Assertions.assertDoesNotThrow(() -> stateService.delete(1L));
    }

    @Test
    @Order(6)
    void whenDeleteThenReturnDataIntegrityViolationException() {
        doThrow(new DataIntegrityViolationException("")).when(stateRepository).deleteById(anyLong());
        Assertions.assertThrows(EntityExceptionInUse.class, () -> stateService.delete(1L));
    }

    @Test
    @Order(7)
    void whenDeleteThenThrowNotFoundException() {
        doThrow(new EmptyResultDataAccessException(1)).when(stateRepository).deleteById(anyLong());
        Assertions.assertThrows(NotFoundException.class, () -> stateService.delete(1L));
    }

    private State startState() {
        return stateMock.stateAdd();
    }
}
