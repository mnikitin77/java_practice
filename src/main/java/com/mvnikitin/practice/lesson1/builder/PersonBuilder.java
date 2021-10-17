package com.mvnikitin.practice.lesson1.builder;

public class PersonBuilder {

    private final Person person;

    public PersonBuilder() {
        person = new Person();
    }

    public PersonBuilder person() {
        return this;
    }

    public PersonBuilder withFirstName(String firstName) {
        person.setFirstName(firstName);
        return this;
    }

    public PersonBuilder withLastName(String lastName) {
        person.setLastName(lastName);
        return this;
    }

    public PersonBuilder withMiddleName(String middleName) {
        person.setMiddleName(middleName);
        return this;
    }

    public PersonBuilder inCountry(String country) {
        person.setCountry(country);
        return this;
    }

    public PersonBuilder withAddress(String address) {
        person.setAddress(address);
        return this;
    }

    public PersonBuilder aged(int age) {
        person.setAge(age);
        return this;
    }

    public PersonBuilder ofGender(String gender) {
        person.setGender(gender);
        return this;
    }

    public PersonBuilder phone(String phone) {
        person.setPhone(phone);
        return this;
    }

    public Person build() {
        return person;
    }
}
