package vn.nal.unittest.service;

import vn.nal.unittest.PaymentMethod;
import vn.nal.unittest.model.Customer;
import vn.nal.unittest.model.Payment;
import vn.nal.unittest.repository.CustomerRepository;
import vn.nal.unittest.repository.PaymentRepository;
import vn.nal.unittest.util.PaymentUtil;

import java.util.Date;

public class PaymentService {

    private PaymentRepository paymentRepository;
    private CustomerRepository customerRepository;

    public PaymentService(PaymentRepository paymentRepository, CustomerRepository customerRepository) {
        this.paymentRepository = paymentRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * To calculate tax, fill out payment information
     * @param customerCode
     * @param totalAmount
     * @return
     */
    public Payment payByCash(String customerCode, Double totalAmount) {
        Customer customer = customerRepository.findByCode(customerCode);

        if (customer == null) {
            throw new IllegalArgumentException("No customer found");
        }

        Payment lastPaymentInYear = paymentRepository.getLastPaymentYear();
        int paymentOrder = 0;
        if (lastPaymentInYear != null) {
            paymentOrder = PaymentUtil.getPaymentOrder(lastPaymentInYear.getCode());
        }
        paymentOrder++;

        Payment payment = new Payment();
        payment.setCustomer(customer);
        payment.setCode(PaymentUtil.generateCode(customer.getCode(), paymentOrder));
        payment.setPaymentMethod(PaymentMethod.CASH);
        payment.setPaymentDate(new Date());
        payment.setTotalAmount(totalAmount + totalAmount * 0.1); // amount after tax
        return payment;
    }
}
