package com.example.javaproject2025.game;

import javafx.scene.shape.Line;

public class Vector {

    public double x;
    public double y;
    public Line direction = new Line();

    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void add(Vector vector){
        this.x += vector.x;
        this.y += vector.y;
    }

    public void subtract(Vector vector){
        this.x -= vector.x;
        this.y -= vector.y;
    }

    public void multiply(double scalar){
        this.x *= scalar;
        this.y *= scalar;
    }

    public void divide(double scalar){
        this.x /= scalar;
        this.y /= scalar;
    }

    public double magnitude(){
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public void normalize(){
        if(this.magnitude() > 0)
            this.divide(this.magnitude());
    }

}
