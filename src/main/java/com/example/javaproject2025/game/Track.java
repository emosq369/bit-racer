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

        Line l1 = new Line(2/3.0*width, 1/3.0*height, 300, 0);
        Line r1 = new Line(500, 1/2.0*height, 2/3.0*width, 0);
        Line l2 = new Line(width * 1/3, height*1/3, 2/3.0*width, 1/3.0*height);
        Line r2 = new Line(1/2.0*width, 1/2.0* height, 500, 1/2.0*height);
        Line l3 = new Line(250, height*2/3, width * 1/3, height*1/3);
        Line r3 = new Line(350, 2/3.0* height, 1/2.0*width, 1/2.0* height);
        Line l4 = new Line(width * 1/3.0, height,250, height*2/3 );
        Line r4 = new Line(width * 2/3.0, height,350, height*2/3);
        Line c= new Line (300,275,400,275);
        for (Line l : List.of(l1, r1, l2, r2, l3, r3, l4, r4)) {
            l.setStroke(Color.WHITE);
            l.setStrokeWidth(4);
            boundaries.add(l);
        }
    }

    public void buildLevel3Layout(double width, double height) {
        boundaries.clear();
        Line l1 = new Line(1/3.0*width, height, 1/2.0*width, 3/4.0*height);
        Line r1 = new Line(2/3.0*width, height, 350, 3/4.0*height);
        Line l2 = new Line(1/2.0*width, 3/4.0*height, 350,400);
        Line r2 = new Line(350, 3/4.0*height,3/4.0*width, 2/3.0*height);
        Line l3 = new Line( 350,400,1/4.0*width, 1/2.0*height);
        Line r3 = new Line(3/4.0*width, 2/3.0*height,1/2.0*width, 350);
        Line l4 = new Line( 1/4.0*width, 1/2.0*height,1/2.0*width, 1/8.0*height);
        Line r4 = new Line(1/2.0*width, 350,1/2.0*width, 1/4.0*height);
        Line l5 = new Line( 1/2.0*width, 1/8.0*height,2/3.0*width, 130);
        Line r5 = new Line(1/2.0*width, 1/4.0*height,2/3.0*width+20, 1/4.0*height);
        Line l6 = new Line( 2/3.0*width, 130,1/2.0*width,0);
        Line r6 = new Line(2/3.0*width+20, 1/4.0*height,2/3.0*width+20,0);
        for (Line l : List.of(l1,r1,l2,r2,l3,r3,l4,r4,l5,r5,l6,r6)) {
            l.setStroke(Color.WHITE);
            l.setStrokeWidth(4);
            boundaries.add(l);
        }
    }
}



