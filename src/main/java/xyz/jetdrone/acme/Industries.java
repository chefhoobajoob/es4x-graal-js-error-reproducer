package xyz.jetdrone.acme;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
public interface Industries {

    /**
     * You already know what will happen to coyote...
     */
    void catchRoadRunner();

    static Industries create() {
        return new Industries() {
            @Override
            public void catchRoadRunner() {
                System.out.println("BOOM!");
            }
        };
    }
}
