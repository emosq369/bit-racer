package com.example.javaproject2025.game;

import javafx.scene.shape.Line;

public class GamePhysics {

    public boolean isCircleCollidingWithLine(double cx, double cy, double radius, double x1, double y1, double x2, double y2) {

        // Vector from line start to circle center
        double dx = x2 - x1;
        double dy = y2 - y1;

        // distance of the whole line
        double lengthSquared = dx * dx + dy * dy;

        // Project point onto the line segment, clamped from 0 to 1
        double t = ((cx - x1) * dx + (cy - y1) * dy) / lengthSquared;
        t = Math.max(0, Math.min(1, t));


        // Closest point on line segment
        double closestX = x1 + t * dx;
        double closestY = y1 + t * dy;

        // Distance to closest point
        double distX = closestX - cx;
        double distY = closestY - cy;
        double distance = Math.sqrt(distX * distX + distY * distY);

        return distance <= radius;

    }

}