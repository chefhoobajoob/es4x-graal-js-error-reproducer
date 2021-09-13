# es4x-graal-js-error-reproducer
simple reproducer to generate graalvm error running on jdk 11: java.lang.NoClassDefFoundError: Could not initialize class org.graalvm.polyglot.Engine$ImplHolder

Running the unit test 'test' is sufficient to produce the error during injection of a `Vertx` instance
