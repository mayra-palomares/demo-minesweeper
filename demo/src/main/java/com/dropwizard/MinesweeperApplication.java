package com.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import ru.vyarus.dropwizard.guice.GuiceBundle;

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
    }

}
