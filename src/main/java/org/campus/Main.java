package org.campus;

import org.campus.api.GET;
import org.campus.api.POST;
import org.campus.configurations.Config;
import org.campus.configurations.EnvConfig;
import org.campus.core.Queries.DatabaseInitialization;
import org.campus.http.CORSFilter;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(Config.PORT);

        DatabaseInitialization.run();
        CORSFilter.enable();

        GET.run();
        POST.run();

        System.out.println(
                EnvConfig.APP_NAME + " started on port " + Config.PORT
        );
    }
}