package org.example;

public class BookingService {
    private final PaymentService paymentService;

    public BookingService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public boolean bookService(int bookingId) {
        return paymentService.processPayment(bookingId);
    }
}

