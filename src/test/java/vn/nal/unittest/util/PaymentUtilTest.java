package vn.nal.unittest.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PaymentUtilTest {

    private static final String A_CUSTOMER_CODE = "CUS00001";
    private static final String A_PAYMENT_CODE = "CUS00001-19-000099";

    @Test
    public void generateCode_ValidCode_IfCustomerCodeAndPaymentOrderAreValid() {
        int paymentOrder = 1;

        SimpleDateFormat yearFormat = new SimpleDateFormat("yy");
        String currentYear = yearFormat.format(new Date());

        String expectedCode = A_CUSTOMER_CODE + "-" + currentYear + "-000001";

        assertEquals(expectedCode, PaymentUtil.generateCode(A_CUSTOMER_CODE, paymentOrder));
    }

    @Test(expected = IllegalArgumentException.class)
    public void generateCode_ThrowException_IfCustomerCodeIsNull() {
        PaymentUtil.generateCode(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generateCode_ThrowException_IfCustomerCodeIsEmpty() {
        PaymentUtil.generateCode("", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generateCode_ThrowException_IfCustomerCodeContainsOnlySpaces() {
        PaymentUtil.generateCode("   ", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generateCode_ThrowException_IfPaymentOrderIsNegative() {
        PaymentUtil.generateCode(A_CUSTOMER_CODE, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generateCode_ThrowException_IfPaymentOrderIsZero() {
        PaymentUtil.generateCode(A_CUSTOMER_CODE, 0);
    }

    @Test
    public void getPaymentOrder_ReturnsCorrectPaymentOrder_IfPaymentCodeIsValid() {
        int code = PaymentUtil.getPaymentOrder(A_PAYMENT_CODE);
        assertEquals(99, code);
    }

    // TODO: Implement others unit test for all of PaymentUtil's method here
}
