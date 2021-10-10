package com.mvnikitin.practice.lesson1.poly;

public abstract class Shape implements ShapeMetrics{
    String getType() {
        return this.getClass().getSimpleName();
    }
}
