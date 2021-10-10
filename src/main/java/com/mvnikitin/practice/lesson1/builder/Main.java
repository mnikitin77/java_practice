package com.mvnikitin.practice.lesson1.builder;

public class Main {
    public static void main(String[] args) {
        PersonBuilder builder = new PersonBuilder();

        Person pupkin = builder.person()
                .withFirstName("Василий")
                .withMiddleName("Петрович")
                .withLastName("Пупкин")
                .inCountry("Россия")
                .aged(25)
                .withAddress("Самара, ул. Ленина 45, кв. 10, этаж 3 (код от подъезда 1234)")
                .phone("+7 945 322 2233")
                .ofGender("мужской")
                .build();
        System.out.println(pupkin);

        System.out.println(builder.withFirstName("Гоша").build());
    }
}
