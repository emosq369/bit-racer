package com.example.javaproject2025.game;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import static com.example.javaproject2025.utils.ScreenUtils.*;

import java.util.ArrayList;
import java.util.List;

public class Track {
    private List<Line> boundaries = new ArrayList<>();
    private List<Image> stars = new ArrayList<>();
    final private String name;

    public Track(String name) {
        this.name = name;
    }

    public void render(Pane root) {
        for (Line line : boundaries) {
            line.setStrokeWidth(4);
            root.getChildren().add(line);
        }
    }


    public List<Line> getBoundaries() {
        return boundaries;
    }

    public void buildLevel1Layout(double width, double height) {
        boundaries.clear();
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

    public void buildLevel2Layout(double width, double height) {
        boundaries.clear();

        double centerY = height / 2;
        double upperY = height * 0.25;

        Line leftBottom = new Line(width * 1/3, height, 180, centerY);
        Line leftMiddle = new Line(180, centerY, 160, upperY);
        Line leftTop = new Line(160, upperY, 300, 0);

        Line rightBottom = new Line(width * 2/3, height, 400, centerY);
        Line rightMiddle = new Line(400, centerY, 420, upperY);
        Line rightTop = new Line(420, upperY, 500, 0);

        for (Line l : List.of(leftBottom, leftMiddle, leftTop, rightBottom, rightMiddle, rightTop)) {
            l.setStroke(Color.WHITE);
            l.setStrokeWidth(4);
            boundaries.add(l);
        }
    }

    public void buildLevel3Layout(double width, double height) {
        boundaries.clear();

        double bottomY = height;
        double lowerMiddleY = height * 0.66;
        double upperMiddleY = height * 0.33;
        double topY = 0;

        // Left side: zigzag up
        Line leftBottom = new Line(width * 1/3, bottomY, 180, lowerMiddleY);
        Line leftZig = new Line(180, lowerMiddleY, 140, upperMiddleY);
        Line leftTop = new Line(140, upperMiddleY, 300, topY);

        // Right side: zigzag up
        Line rightBottom = new Line(width * 2/3, bottomY, 400, lowerMiddleY);
        Line rightZig = new Line(400, lowerMiddleY, 440, upperMiddleY);
        Line rightTop = new Line(440, upperMiddleY, 500, topY);

        for (Line l : List.of(leftBottom, leftZig, leftTop, rightBottom, rightZig, rightTop)) {
            l.setStroke(Color.WHITE);
            l.setStrokeWidth(4);
            boundaries.add(l);
        }
    }




}



