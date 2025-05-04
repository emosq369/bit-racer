package com.example.javaproject2025;

public class Session {
    private static String player1;
    private static String player2;

    public static void setPlayer1(String username) {
        player1 = username;
    }

    public static void setPlayer2(String username) {
        player2 = username;
    }

    public static String getPlayer1() {
        return player1;
    }

    public static String getPlayer2() {
        return player2;
    }
}
