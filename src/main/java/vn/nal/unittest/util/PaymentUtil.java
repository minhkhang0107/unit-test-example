package vn.nal.unittest.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentUtil {

    private static final String PAYMENT_REGEX = "(CUS([0-9]{5}))-([0-9]{2})-([0-9]{6,})";

    /**
     * To validate a payment code
     * @param paymentCode
     * @return true if payment code is valid, false if payment code is invalid
     */
    public static boolean validateCode(String paymentCode) {
        if (paymentCode == null || paymentCode.trim().isEmpty()) {
            return false;
        }
        return paymentCode.matches(PAYMENT_REGEX);
    }

    /**
     * To generate a payment code
     * Structure of a payment code: [CUSTOMER_CODE]-[CURRENT_YEAR]-[BILLING_ORDER]
     * Detail:
     * - CUSTOMER_CODE with format: CUS#####. Ex: CUS00001
     * - CURRENT_YEAR with format: yy
     * - BILLING_ORDER with format: ######. Ex: 000123
     * @param customerCode
     * @return a String of payment code
     */
    public static String generateCode(String customerCode, int paymentOrder) {
        if(customerCode == null || customerCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer code cannot be null");
        }

        if(paymentOrder <= 0) {
            throw new IllegalArgumentException("Payment order is invalid");
        }

        SimpleDateFormat yearFormat = new SimpleDateFormat("yy");
        String currentYear = yearFormat.format(new Date());

        DecimalFormat paymentOrderFormat = new DecimalFormat("000000");

        return customerCode + "-" + currentYear + "-" + paymentOrderFormat.format(paymentOrder);
    }

    /**
     * To extract payment order from a payment code
     * @param paymentCode
     * @return payment order
     */
    public static int getPaymentOrder(String paymentCode) {
        if (!validateCode(paymentCode)) {
            throw new IllegalArgumentException("Payment code is invalid");
        }

        Pattern r = Pattern.compile(PAYMENT_REGEX);
        Matcher m = r.matcher(paymentCode);
        if (m.find()) {
            return new Integer(m.group(4));
        }
        throw new IllegalArgumentException("Payment code is invalid");
    }
}
