package app.netlify.qaautomationpractice.api.service_objects.pojos;
/*
Author: Daniel Martins
Email: daniel.d.martins@outlook.com
*/
public class Employee {
    private String dob;
    private String email;
    private String firstName;
    private int id;
    private String lastName;

    public Employee(String dob, String email, String firstName, int id, String lastName) {
        this.dob = dob;
        this.email = email;
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
    }

    public Employee() {
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "{id=%s, firstName=%s, lastName=%s, dob=%s, email=%s}",
                this.id,
                this.firstName,
                this.lastName,
                this.dob,
                this.email);
    }
}
