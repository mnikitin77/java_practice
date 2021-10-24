package com.mvnikitin.practice.lesson4;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Lesson4factory {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory( "com.mvnikitin.practice.lesson4" );

    public static EntityManagerFactory getFactory() {
        if (!factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory( "com.mvnikitin.practice.lesson4" );
        }
        return factory;
    }

    public static void closeFactory() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}
