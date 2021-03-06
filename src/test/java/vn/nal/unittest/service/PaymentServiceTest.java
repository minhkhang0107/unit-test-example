package vn.nal.unittest.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import vn.nal.unittest.PaymentMethod;
import vn.nal.unittest.model.Customer;
import vn.nal.unittest.model.Payment;
import vn.nal.unittest.repository.CustomerRepository;
import vn.nal.unittest.repository.PaymentRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private PaymentService service;

    private Customer customer;
    private Payment lastPaymentInYear;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        customer = new Customer();
        customer.setCode("CUS00001");

        lastPaymentInYear = new Payment();
        lastPaymentInYear.setCode("CUS00001-19-000099");
    }

    @Test
    public void payByCash_ReturnValidPaymentObject_IfCustomerIdAndTotalAmountAreValid() {
        String customerCode = "CUS00001";
        double totalAmount = 1000000d;

        Mockito.when(customerRepository.findByCode(customerCode)).thenReturn(customer);
        Mockito.when(paymentRepository.getLastPaymentYear()).thenReturn(lastPaymentInYear);

        Payment paymentResult = service.payByCash(customerCode, totalAmount);

        assertNotNull(paymentResult);
        assertTrue(paymentResult.getCode().matches("CUS00001-[0-9]{2}-000100"));
        assertEquals(PaymentMethod.CASH, paymentResult.getPaymentMethod());
        assertEquals(new Double(totalAmount + totalAmount * 0.1), paymentResult.getTotalAmount());
        assertEquals(customer, paymentResult.getCustomer());
    }

    @Test
    public void payByCash_ReturnValidPaymentObject_IfCustomerIdAndTotalAmountAreValidAndPaymentIsTheFirstInYear() {
        String customerCode = "CUS00001";
        double totalAmount = 1000000d;

        Mockito.when(customerRepository.findByCode(customerCode)).thenReturn(customer);
        Mockito.when(paymentRepository.getLastPaymentYear()).thenReturn(null);

        Payment paymentResult = service.payByCash(customerCode, totalAmount);

        assertNotNull(paymentResult);
        assertTrue(paymentResult.getCode().matches("CUS00001-[0-9]{2}-000001"));
        assertEquals(PaymentMethod.CASH, paymentResult.getPaymentMethod());
        assertEquals(new Double(totalAmount + totalAmount * 0.1), paymentResult.getTotalAmount());
        assertEquals(customer, paymentResult.getCustomer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void payByCash_ThrowException_IfCustomerIdIsNotExist() {
        String customerCode = "CUS00999";

        Mockito.when(customerRepository.findByCode(customerCode)).thenReturn(null);
        Mockito.when(paymentRepository.getLastPaymentYear()).thenReturn(lastPaymentInYear);

        service.payByCash(customerCode, 1000000d);
    }

    // TODO: implement unit tests for other cases here
}
