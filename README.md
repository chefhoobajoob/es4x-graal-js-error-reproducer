# es4x-graal-js-error-reproducer
simple reproducer to generate graalvm error running on jdk 11: java.lang.NoClassDefFoundError: Could not initialize class org.graalvm.polyglot.Engine$ImplHolder

**EDIT**: after revising the build script to automate JVM args to include the graal compiler modules for running test cases, this revision eliminates the error. Earlier tests run via the IDE must have been setting module path properties incorrectly.
