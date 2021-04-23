package com.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import ru.vyarus.dropwizard.guice.GuiceBundle;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class MinesweeperApplication extends Application<MinesweeperConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MinesweeperApplication().run(args);
    }

    @Override
    public String getName() {
        return "Minesweeper";
    }

    @Override
    public void initialize(final Bootstrap<MinesweeperConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<MinesweeperConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(final MinesweeperConfiguration minesweeperConfiguration) {
                return minesweeperConfiguration.getSwaggerBundleConfiguration();
            }
        });

        bootstrap.addBundle(GuiceBundle.builder()
                .modules(new MinesweeperModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .build());
    }

    @Override
    public void run(final MinesweeperConfiguration configuration,
                    final Environment environment) {

        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

}
