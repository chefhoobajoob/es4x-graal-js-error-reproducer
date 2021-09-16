package org.acme;

import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(VertxExtension.class)
public class Tests {
    Logger _logger = LoggerFactory.getLogger(Tests.class);

    @Test
    public void testRunsOnJvm11OrLater(Vertx theVertx, VertxTestContext theContext) {
        String version = System.getProperty("java.version");
        _logger.info("Test running on jvm {}", version);
        theContext.verify(() -> {
            assertThat(Integer.parseInt(version.split("\\.")[0])).as("require jvm >= 11").isGreaterThanOrEqualTo(11);
        });

        theVertx.deployVerticle("js:index.js")
                .onSuccess(id -> theContext.completeNow())
                .onFailure(theContext::failNow);
    }
}
