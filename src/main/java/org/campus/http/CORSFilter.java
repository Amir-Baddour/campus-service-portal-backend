package org.campus.http;

import static spark.Spark.*;

public class CORSFilter {

    public static void enable() {

        options("/*", (request, response) -> {

            String headers = request.headers("Access-Control-Request-Headers");

            if (headers != null) {
                response.header("Access-Control-Allow-Headers", headers);
            }

            String methods = request.headers("Access-Control-Request-Method");

            if (methods != null) {
                response.header("Access-Control-Allow-Methods", methods);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Headers", "*");
            response.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.type("application/json");
        });
    }
}