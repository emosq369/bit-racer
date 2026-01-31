package com.example.javaproject2025.config;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Db {

    private static final Dotenv envFile = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    private static String get(String key, String def) {
        String v = System.getenv(key);          // real env vars first
        if (v == null || v.isBlank()) v = envFile.get(key); // fallback to .env
        return (v == null || v.isBlank()) ? def : v.trim();
    }

    private static final String HOST = get("DB_HOST", "127.0.0.1");
    private static final String PORT = get("DB_PORT", "3306");
    private static final String NAME = get("DB_NAME", "bitracer");
    private static final String USER = get("DB_USER", "appuser");
    private static final String PASS = get("DB_PASS", "");

    private static final String URL = String.format(
            "jdbc:mysql://%s:%s/%s?sslMode=DISABLED&allowPublicKeyRetrieval=true",
            HOST, PORT, NAME
    );

    private Db() {}

    public static Connection get() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
