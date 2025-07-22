package com.solera.wvpmanager;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.solera.wvpmanager.models.WorkshopModel;
import com.solera.wvpmanager.repositories.WorkshopRepository;
import com.solera.wvpmanager.services.WorkshopService;

@ExtendWith(MockitoExtension.class)
class WorkshopServiceTest {
    @Mock
    private WorkshopModel workshopMock;

    @Mock
    private WorkshopRepository workshopRepository;

    @InjectMocks
    private WorkshopService workshopService;


    @Test
    void shouldThrowWhenWorkshopIsNullOnAddTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            workshopService.createWorkshop(null);
        });
    }

    @Test
    void shouldThrowWhenIdIsZeroOrNegativeOnGetTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            workshopService.getWorkShopById(0);
        }, "Workshop id is zero");

        assertThrows(IllegalArgumentException.class, () -> {
            workshopService.getWorkShopById(-1);
        }, "Workshop id is negative");
    }

    @Test
    void shouldReturnNullWhenNotFoundTest() {
        assertNull(workshopService.getWorkShopById(1));
    }

    @Test
    void shouldDeleteWhenNoVehiclesTest() {
        int workshopId = 1;

        when(workshopRepository.existsById(workshopId)).thenReturn(true);
        when(workshopMock.getVehicles()).thenReturn(List.of());
        when(workshopRepository.findById(workshopId)).thenReturn(Optional.of(workshopMock));

        assertDoesNotThrow(() -> {
            workshopService.deleteWorkshopById(workshopId);
        });
    }
}
