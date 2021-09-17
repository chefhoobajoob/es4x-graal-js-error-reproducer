# es4x-graal-js-error-reproducer
simple reproducer to generate graalvm errors running on jdk 11:

On Windows systems:
* The `js` verticle factory is unable to load the main module with this error: org.graalvm.polyglot.PolyglotException: ModuleError: Module "./index.js" was not found
* The `mjs` verticle factory is unable to load the main module if it has a `.mjs` file extension with this error: org.graalvm.polyglot.PolyglotException: java.nio.file.InvalidPathException: Illegal char <:> at index 76: <project-root>\node_modules\<user-home>\.gradle\caches\modules-2\files-2.1\org.graalvm.js\js\21.1.0\9b6925a2639fb0ae60d6c921dacf64192c284da9
* The `mjs` verticle factory is able to load a main module if it has a `.js` extension, but fails to resolve any `import` statements

On all(?) systems:
* Regardless of verticle factory, the global `config` variable available to each module is always an empty object, even if `vertx.deployVerticle()` is called with a non-empty config `JsonObject`.
