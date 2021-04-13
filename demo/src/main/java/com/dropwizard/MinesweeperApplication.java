package com.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        // TODO: application initialization
    }

    @Override
    public void run(final MinesweeperConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
