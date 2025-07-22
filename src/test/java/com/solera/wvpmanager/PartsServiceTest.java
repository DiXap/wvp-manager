package com.solera.wvpmanager;

import com.solera.wvpmanager.models.PartsModel;
import com.solera.wvpmanager.repositories.PartsRepository;
import com.solera.wvpmanager.services.PartsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Optional;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PartsServiceTest {

    private PartsRepository partsRepository;
    private PartsService partsService;

    //Creating a mock object and initializing the PartsService before each test
    @BeforeEach
    public void setUp() {
        partsRepository = mock(PartsRepository.class);
        partsService = new PartsService(partsRepository);
    }


    //When a real object is created, variable result be assigned the mock object
    //Then we can use the mock object to simulate the behavior of the real object
    @Test
    public void CreatePartValidPartTest() {
        PartsModel part = new PartsModel("Filtro", 123, "Bosch");
        when(partsRepository.save(part)).thenReturn(part);

        PartsModel result = partsService.createPart(part);
        assertEquals(part, result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testParts.csv", numLinesToSkip = 1)
    public void GetAllPartsTest(String name, int number, String brand) {
        PartsModel testParts = new PartsModel(name, number, brand);

        when(partsRepository.count()).thenReturn(2L);
        when(partsRepository.findAll()).thenReturn(Collections.singletonList(testParts));

        Iterable<PartsModel> result = partsService.getAllParts();
        assertEquals(Collections.singletonList(testParts), result);
    }

    @Test
    public void GetAllPartsExceptionTest() {
        when(partsRepository.count()).thenReturn(0L);
        assertThrows(IllegalArgumentException.class, () -> partsService.getAllParts());
    }

    @Test
    public void GetPartByIDTest() {
        PartsModel part = new PartsModel("Filtro", 123, "Bosch");
        when(partsRepository.existsById(1)).thenReturn(true);
        when(partsRepository.findById(1)).thenReturn(Optional.of(part));

        PartsModel result = partsService.getPartByID(1);
        assertEquals(part, result);
    }


    @Test
    public void UpdatePartValidationTest() {
        PartsModel oldPart = new PartsModel("Filtro", 123, "Bosch");
        PartsModel newPart = new PartsModel("Bujía", 456, "NGK");

        when(partsRepository.existsById(1)).thenReturn(true);
        when(partsRepository.findById(1)).thenReturn(Optional.of(oldPart));
        when(partsRepository.save(oldPart)).thenReturn(oldPart);

        PartsModel updated = partsService.updatePart(1, newPart);
        assertEquals("Bujía", updated.getParts_name());
        assertEquals(456, updated.getPart_num());
        assertEquals("NGK", updated.getBrand_part());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testParts.csv", numLinesToSkip = 1)
    public void DeletePartByIdValidTest(String name, int number, String brand) {
        PartsModel part = mock(PartsModel.class);
        when(partsRepository.existsById(number)).thenReturn(true);
        when(partsRepository.findById(number)).thenReturn(Optional.of(part));
        when(part.getVehicles()).thenReturn(Collections.emptyList());

        assertDoesNotThrow(() -> partsService.deletePartById(number));
        verify(partsRepository).deleteById(number);
    }



}