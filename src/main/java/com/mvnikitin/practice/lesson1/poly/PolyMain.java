package com.mvnikitin.practice.lesson1.poly;

import java.util.List;

public class PolyMain {
    public static void main(String[] args) {
        List<Shape> listOfShapes = List.of(
                new Circle(5.0),
                new Triangle(3.0, 4.0, 5.0),
                new Square(4.0),
                new Circle(11.0)
        );

        listOfShapes.forEach(it -> System.out.println(it.getType() + ", perimeter= " + it.perimeter() + ", area=" + it.area()));
    }
}
