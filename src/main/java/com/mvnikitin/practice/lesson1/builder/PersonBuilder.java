package com.mvnikitin.practice.lesson1.builder;

import java.util.Objects;

public class PersonBuilder {

    private Person person;

    public PersonBuilder person() {
        person = new Person();
        return this;
    }

    public PersonBuilder withFirstName(String firstName) {
        checkPerson();
        person.setFirstName(firstName);
        return this;
    }

    public PersonBuilder withLastName(String lastName) {
        checkPerson();
        person.setLastName(lastName);
        return this;
    }

    public PersonBuilder withMiddleName(String middleName) {
        checkPerson();
        person.setMiddleName(middleName);
        return this;
    }

    public PersonBuilder inCountry(String country) {
        checkPerson();
        person.setCountry(country);
        return this;
    }

    public PersonBuilder withAddress(String address) {
        checkPerson();
        person.setAddress(address);
        return this;
    }

    public PersonBuilder aged(int age) {
        checkPerson();
        person.setAge(age);
        return this;
    }

    public PersonBuilder ofGender(String gender) {
        checkPerson();
        person.setGender(gender);
        return this;
    }

    public PersonBuilder phone(String phone) {
        checkPerson();
        person.setPhone(phone);
        return this;
    }

    public Person build() {
        Person builtPerson = person;
        person = null;
        return builtPerson;
    }

    private void checkPerson() {
        Objects.requireNonNull(person, "The builder is not initialized");
    }
}
