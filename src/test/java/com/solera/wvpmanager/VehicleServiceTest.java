package com.solera.wvpmanager;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.solera.wvpmanager.models.VehicleModel;
import com.solera.wvpmanager.repositories.VehicleRepository;
import com.solera.wvpmanager.services.VehicleService;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {
    @Mock
    private VehicleModel vehicleMock;

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    
    @Test
    void shouldThrowOnInvalidVinTest() {
        String invalidVin = "123";
        when(vehicleMock.getVin()).thenReturn(invalidVin);

        assertThrows(IllegalArgumentException.class, () -> {
            vehicleService.createNewVehicle(vehicleMock);
        });
    }

    @Test
    void shouldThrowWhenIdIsZeroOrNegativeOnGetTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            vehicleService.getVehicleById(0);
        }, "Workshop id is zero");

        assertThrows(IllegalArgumentException.class, () -> {
            vehicleService.getVehicleById(-1);
        }, "Workshop id is negative");
    }
}
