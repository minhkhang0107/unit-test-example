package vn.nal.unittest.model;

public class Customer {
    private String code;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        String fullName = null;
        if (this.firstName != null) {
            fullName = this.firstName;
        }
        if (this.lastName != null) {
            fullName = (fullName == null ? "" : fullName + " ") + this.lastName;
        }
        return fullName;
    }
}
