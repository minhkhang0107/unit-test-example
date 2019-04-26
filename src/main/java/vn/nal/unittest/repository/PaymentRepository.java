package vn.nal.unittest.repository;

import vn.nal.unittest.model.Payment;

public interface PaymentRepository {

    Payment getLastPaymentYear();
}
