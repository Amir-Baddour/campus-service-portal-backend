package org.campus;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(Config.PORT);

        get("/health", (req, res) -> {
            res.type("application/json");
            return """
                    {
                      "status": "OK",
                      "app": "Campus Service Portal",
                      "message": "Backend is running successfully"
                    }
                    """;
        });

        System.out.println(EnvConfig.APP_NAME + " started on port " + Config.PORT);
    }
}