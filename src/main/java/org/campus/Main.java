package org.campus;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(Config.PORT);

        CorsFilter.run();   // or enable(), depending on your implementation

        GET.run();
        POST.run();

        System.out.println(
                EnvConfig.APP_NAME + " started on port " + Config.PORT
        );
    }
}