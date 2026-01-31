package com.example.javaproject2025.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Bit {
    private Circle shape;
    private Line directionLine;
    private String name;
    private double angle = 270; //in degrees, it goes clockwise instead of counter clock wise
    //that's why it's 270 instead of 90 to point upwards
    public boolean launched = false;
    public boolean moved = false;
    public double dx = 0;
    public double dy = 0;


    public Bit(String name, double startX, double startY, Color color) {
        this.name = name;
        shape = new Circle(startX, startY, 5);
        shape.setFill(color);

        directionLine = new Line(startX, startY, startX, startY - 20);
        directionLine.setStroke(color);
        directionLine.setStrokeWidth(2);
    }

    public Circle getShape() {
        return shape;
    }

    public Line getDirectionLine() {
        return directionLine;
    }

    private void updateDirectionLine() {
        double length = 20;
        double radians = Math.toRadians(angle);
        double endX = shape.getCenterX() + length * Math.cos(radians);
        double endY = shape.getCenterY() + length * Math.sin(radians);
        directionLine.setStartX(shape.getCenterX());
        directionLine.setStartY(shape.getCenterY());
        directionLine.setEndX(endX);
        directionLine.setEndY(endY);
    }

    public double getAngle() {
        return angle;
    }

    public void rotate(double deltaAngle) {
        angle = (angle + deltaAngle) % 360;
        updateDirectionLine();
    }

    public double getX() {
        return shape.getCenterX();
    }

    public double getY() {
        return shape.getCenterY();
    }

    public void move(double dx, double dy) {
        shape.setCenterX(shape.getCenterX() + dx);
        shape.setCenterY(shape.getCenterY() + dy);
    }

    public String getName() {
        return name;
    }

    public void launch(double speed) {
        if (launched) return; //prevent double launch
        double radians = Math.toRadians(angle);
        dx = speed * Math.cos(radians);
        dy = speed * Math.sin(radians);
        launched = true;
    }

    public void moveIfLaunched() {
        if (launched) {
            move(dx, dy);
            // so the arrow follows
            updateDirectionLine();

        }
    }

    public boolean checkCollision() {
        if(this.getY() < 0)
            return true;
        return false;
    }

}
