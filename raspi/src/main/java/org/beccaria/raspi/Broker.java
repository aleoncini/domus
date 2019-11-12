package org.beccaria.raspi;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class Broker implements MqttCallback {
    @Inject
    @Named("board")
    Board board;

    @ConfigProperty(name = "broker.host")
    String broker;
    @ConfigProperty(name = "broker.port")
    int port;
    @ConfigProperty(name = "commands.topic")
    String commandTopic;
    @ConfigProperty(name = "controllers.topic")
    String controllerTopic;


    MemoryPersistence persistence = new MemoryPersistence();

    public void subscribeToCommands() throws MqttException {
        String broker_url = "tcp://" + broker + ":" + port;
        MqttClient client = new MqttClient(broker_url, this.toString(), persistence);
        client.connect();
        client.setCallback(this);
        client.subscribe(commandTopic);
    }

    public void notifyPresence() throws MqttException {
        String broker_url = "tcp://" + broker + ":" + port;
        MqttClient client = new MqttClient(broker_url, "domus.raspi", persistence);
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);
        client.connect(mqttConnectOptions);
        MqttMessage message = new MqttMessage(board.serial().getBytes());
        message.setQos(0);
        client.publish(controllerTopic, message);
        client.disconnect();
    }

    @Override
    public void connectionLost(Throwable throwable) {
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String message = mqttMessage.toString();
        if (message.startsWith("health")){
            notifyPresence();
            return;
        }
        if (message.startsWith(board.serial())){
            int pin = getPin(message);
            if(pin < 0)return;
            if(! board.isReady)return;
            if (message.endsWith("on")){
                System.out.println("=======> pin: " +  pin + " switch ON");
                board.on(pin);
            }
            if (message.endsWith("off")){
                System.out.println("=======> pin: " +  pin + " switch OFF ");
                board.off(pin);
            }
        }
    }

    private int getPin(String message) {
        int startIndex = message.indexOf("/") + 1;
        int endIndex = startIndex + 1;
        int ret = -1;
        try{
            ret = Integer.parseInt(message.substring(startIndex, endIndex));
        } catch (Exception e){
        }
        return ret;
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }
}
