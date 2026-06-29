package org.campus.api;

import static spark.Spark.get;

public class GET {

    public static void run() {
        get("/health", (request, response) -> {
            response.type("application/json");

            return """
                    {
                      "status": "OK",
                      "message": "Campus Service Portal is running"
                    }
                    """;
        });
    }
}