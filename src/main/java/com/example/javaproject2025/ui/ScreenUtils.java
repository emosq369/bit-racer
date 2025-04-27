package com.example.javaproject2025.ui;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class ScreenUtils {

    public static void drawStars(Pane pane) {

        for (int i = 0; i < 100; i++) {
            double x = Math.random() * pane.getPrefWidth();
            double y = Math.random() * pane.getPrefHeight();

            Circle star = new Circle(x, y, Math.random()*0.5, Color.WHITE);

            pane.getChildren().add(star);
        }
    }

    public static Label createMenuLabel(String text) {

        Label label = new Label(text);
        label.setFont(Font.font("Orbitron", FontWeight.BOLD, 35));
        label.setTextFill(Color.WHITE);

        DropShadow normalGlow = createNeonGlow(Color.WHITE);
        DropShadow strongGlow = createNeonGlow(Color.WHITE);
        strongGlow.setRadius(40); // Bigger glow when hovered

        label.setEffect(normalGlow);

        // Hover effects
        label.setOnMouseEntered(event -> {
            label.setEffect(strongGlow);
            label.setScaleX(1.1);
            label.setScaleY(1.1);
        });

        label.setOnMouseExited(event -> {
            label.setEffect(normalGlow);
            label.setScaleX(1.0);
            label.setScaleY(1.0);
        });

        return label;
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