package vn.nal.unittest.model;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {

    @Test
    public void getFullName_ValidFullName_IfFirstNameAndLastNameAreValid() {
        Customer customer = new Customer();
        customer.setFirstName("Jon");
        customer.setLastName("Snow");
        Assert.assertEquals(customer.getFullName(), "Jon Snow");
    }

    @Test
    public void getFullName_ValidFullName_IfFirstNameIsNull() {
        Customer customer = new Customer();
        customer.setLastName("Snow");
        Assert.assertEquals(customer.getFullName(), "Snow");
    }

    @Test
    public void getFullName_ValidFullName_IfLastNameIsNull() {
        Customer customer = new Customer();
        customer.setFirstName("Jon");
        Assert.assertEquals(customer.getFullName(), "Jon");
    }

    @Test
    public void getFullName_ValidFullName_IfLastNameAndLastNameAreNull() {
        Customer customer = new Customer();
        Assert.assertNull(customer.getFullName());
    }
}
