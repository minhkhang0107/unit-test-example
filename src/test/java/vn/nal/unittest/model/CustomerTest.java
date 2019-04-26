package vn.nal.unittest.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CustomerTest {

    @Test
    public void getFullName_ValidFullName_IfFirstNameAndLastNameAreValid() {
        Customer customer = new Customer();
        customer.setFirstName("Jon");
        customer.setLastName("Snow");
        assertEquals("Jon Snow", customer.getFullName());
    }

    @Test
    public void getFullName_ValidFullName_IfFirstNameIsNull() {
        Customer customer = new Customer();
        customer.setLastName("Snow");
        assertEquals("Snow", customer.getFullName());
    }

    @Test
    public void getFullName_ValidFullName_IfLastNameIsNull() {
        Customer customer = new Customer();
        customer.setFirstName("Jon");
        assertEquals("Jon", customer.getFullName());
    }

    @Test
    public void getFullName_ValidFullName_IfLastNameAndLastNameAreNull() {
        Customer customer = new Customer();
        assertNull(customer.getFullName());
    }
}
