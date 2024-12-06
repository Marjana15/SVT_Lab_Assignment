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
public class BookingServiceTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void testBookService() {
        int bookingId = 1;

        when(paymentService.processPayment(1)).thenReturn(true);

        boolean result = bookingService.bookService(1);
        assertTrue(result);

        verify(paymentService).processPayment(1);
    }
}
