package org.acme;

import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(VertxExtension.class)
public class Tests {
    @Test
    public void test( Vertx theVertx, VertxTestContext theContext )
    {
        assertThat( true ).isTrue();
        theContext.completeNow();
    }
}
