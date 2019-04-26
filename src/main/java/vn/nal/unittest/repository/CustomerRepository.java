package vn.nal.unittest.repository;

import vn.nal.unittest.model.Customer;

public interface CustomerRepository {

    Customer findByCode(String code);
}
