package org.beccaria.raspi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class AppEvent {
    private static final Logger LOGGER = LoggerFactory.getLogger("org.beccaria");

    @Inject
    Broker broker;

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("==========> [AppEvent] Application starting...");
        try {
            broker.subscribeToCommands();
            LOGGER.info("==========> [AppEvent] Controller subscribed to commands topic");
            broker.notifyPresence();
            LOGGER.info("==========> [AppEvent] Controller presence notified");
        } catch (MqttException e) {
            LOGGER.info("==========> [AppEvent] ERROR: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("==========> [AppEvent] The application is stopping...");
    }
}
