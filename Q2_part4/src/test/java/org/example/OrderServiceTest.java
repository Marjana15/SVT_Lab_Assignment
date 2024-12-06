package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private ShippingService shippingService;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testPlaceOrder_ValidOrder() {
        String item = "Laptop";
        int quantity = 1;
        when(shippingService.ship(item, quantity)).thenReturn(true);
        boolean result = orderService.placeOrder(item, quantity);
        assertTrue(result, "Place order should return true for a valid shipment");
        verify(shippingService).ship(item, quantity);
    }

    @Test
    void testPlaceOrder_InvalidQuantity() {
        String item = "Laptop";
        int quantity = 0;
        boolean result = orderService.placeOrder(item, quantity);
        assertFalse(result, "Place order should return false for invalid quantity");
        verify(shippingService, never()).ship(anyString(), anyInt());
    }

    @Test
    void testPlaceOrder_ShippingFails() {
        String item = "Smartphone";
        int quantity = 2;
        when(shippingService.ship(item, quantity)).thenReturn(false);
        boolean result = orderService.placeOrder(item, quantity);
        assertFalse(result, "Place order should return false if shipping fails");
        verify(shippingService).ship(item, quantity);
    }
}
