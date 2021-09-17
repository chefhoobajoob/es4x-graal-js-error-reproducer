package org.acme;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(VertxExtension.class)
public class Tests {
    Logger _logger = LoggerFactory.getLogger(Tests.class);
    ListAppender<ILoggingEvent> _appender;

    @BeforeEach
    void setup() {
        _appender = new ListAppender<>();
        _appender.start();
    }

    @Test
    public void jsFactoryDeploys( Vertx theVertx, VertxTestContext theContext ) {
        _logger = LoggerFactory.getLogger( Tests.class.getCanonicalName() + ".jsFactoryDeploys" );
        jvm11OrLater( theContext );
        if ( theContext.failed() ) {
            return;
        }

        ((ch.qos.logback.classic.Logger)LoggerFactory.getLogger( "es4x.tests.index" )).addAppender( _appender );
        String catchphrase = "Marcia, Marcia, Marcia!";
        deploy( theVertx, "js", catchphrase )
        .onSuccess(id -> {
            loggedCatchphrase( theContext, catchphrase );
            theVertx.undeploy( id, (ignored) -> theContext.completeNow() );
        })
        .onFailure(theContext::failNow);
    }

    @Test
    public void mjsFactoryDeploys( Vertx theVertx, VertxTestContext theContext ) {
        _logger = LoggerFactory.getLogger( Tests.class.getCanonicalName() + ".mjsFactoryDeploys" );
        jvm11OrLater( theContext );
        if ( theContext.failed() ) {
            return;
        }

        ((ch.qos.logback.classic.Logger)LoggerFactory.getLogger( "es4x.tests.index" )).addAppender( _appender );
        String catchphrase = "Shazbot! Nanu-nanu";
        deploy( theVertx, "mjs", catchphrase )
        .onSuccess( id -> {
            loggedCatchphrase( theContext, catchphrase );
            theVertx.undeploy( id, (ignored) -> theContext.completeNow() );
        })
        .onFailure( theContext::failNow );
    }

    private void jvm11OrLater( VertxTestContext theContext ) {
        String version = System.getProperty( "java.version" );
        _logger.info( "Test running on jvm {}", version );
        theContext.verify(() -> {
            assertThat( Integer.parseInt( version.split("\\.")[0] ) ).as( "require jvm >= 11" ).isGreaterThanOrEqualTo( 11 );
        });
    }

    private Future<String> deploy( Vertx theVertx, String theFactory, String theCatchphrase ) {
        return theVertx.deployVerticle( theFactory + ":index.js", new DeploymentOptions().setConfig( new JsonObject().put( "catchphrase", theCatchphrase ) ) );
    }

    private void loggedCatchphrase( VertxTestContext theContext, String catchphrase ) {
        theContext.verify( () -> {
            assertThat( _appender.list ).hasSize( 1 );
            assertThat( _appender.list.get( 0 ).getLevel() ).isEqualTo( Level.INFO );
            assertThat( _appender.list.get( 0 ).getFormattedMessage() ).isEqualTo( "Hello from ES4X, the catchphrase is: " + catchphrase );
        });
    }

}
