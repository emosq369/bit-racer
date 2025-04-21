package com.example.javaproject2025.game;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import java.util.ArrayList;
import java.util.List;

public class Track {
    private List<Line> boundaries = new ArrayList<>();
    final private String name;

    public Track(String name) {
        this.name = name;
    }

    public void render(Pane root) {
        root.getChildren().addAll(boundaries);
    }

    public List<Line> getBoundaries() {
        return boundaries;
    }

    public void buildLevel1Layout(double width, double height) {
        double centerY = height / 2;

        Line leftTop = new Line(180, centerY, 400, 0);
        Line rightTop = new Line(369, centerY, 420, 0);
        Line leftBottom = new Line(width * 1/3, height, 180, centerY);
        Line rightBottom = new Line(width * 2/3, height, 369, centerY);

        for (Line l : List.of(leftTop, rightTop, leftBottom, rightBottom)) {
            l.setStroke(Color.WHITE);
            l.setStrokeWidth(4);
            boundaries.add(l);
        }
    }
}
