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
    Logger _logger = LoggerFactory.getLogger( Tests.class );

    @Test
    public void test( Vertx theVertx, VertxTestContext theContext )
    {
        _logger.info("Test is running!");
        assertThat( true ).isTrue();
        theContext.completeNow();
    }
}
