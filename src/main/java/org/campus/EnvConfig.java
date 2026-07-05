package org.campus;

public class EnvConfig {
    public static final String APP_NAME = getEnv("APP_NAME", "Campus Service Portal");

    public static final String DB_URL      = getEnv("DB_URL", "jdbc:postgresql://localhost:5432/myapp");
    public static final String DB_USER     = getEnv("DB_USER", "postgres");
    public static final String DB_PASSWORD = getEnv("DB_PASSWORD", "AbaddourBlue@321");

    private static String getEnv(String key, String fallback) {
        String value = System.getenv(key);
        return (value != null) ? value : fallback;
    }
}