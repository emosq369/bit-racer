package com.example.javaproject2025;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import java.util.ArrayList;
import java.util.List;

public class Track {
    private List<Line> boundaries;
    private String name;

    public Track(String name) {
        this.name = name;
        this.boundaries = new ArrayList<>();
    }

    public void addBoundary(Line line) {
        line.setStroke(Color.CYAN);
        line.setStrokeWidth(4);
        boundaries.add(line);
    }

    public void render(Pane root) {
        root.getChildren().addAll(boundaries);
    }

    public String getName() {
        return name;
    }

    public List<Line> getBoundaries() {
        return boundaries;
    }
}