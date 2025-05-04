package com.example.javaproject2025.utils;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;


public class ScreenUtils {

    public static void drawStars(Pane pane) {

        for (int i = 0; i < 100; i++) {
            double x = Math.random() * pane.getPrefWidth();
            double y = Math.random() * pane.getPrefHeight();

            Circle star = new Circle(x, y, Math.random()*0.5, Color.WHITE);

            pane.getChildren().add(star);
        }
    }


    public static Label createNavigationLabel(String text) {
        Label label = new Label(text);

        label.setFont(Font.font("Orbitron", 16));
        label.setTextFill(Color.LIGHTGRAY);
        label.setEffect(createNeonGlow(Color.LIGHTGRAY));

        return label;
    }

    public static DropShadow createNeonGlow(Color color) {
        DropShadow glow = new DropShadow();
        glow.setOffsetX(0);
        glow.setOffsetY(0);
        glow.setColor(color);
        glow.setRadius(20);
        return glow;
    }
}