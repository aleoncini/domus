package org.domotics.hub.model;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.util.concurrent.TimeUnit;

public class ControllerService {

    private String uuid;
    private String gpio;
    private String status;
    private String ipAddress;
    private String port;
    private String method;

    public static final String METHOD_ON = "on";
    public static final String METHOD_OFF = "off";

    public String getUuid() {
        return uuid;
    }

    public ControllerService setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getGpio() {
        return gpio;
    }

    public ControllerService setGpio(String gpio) {
        this.gpio = gpio;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ControllerService setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public ControllerService setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public String getPort() {
        return port;
    }

    public ControllerService setPort(String port) {
        this.port = port;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public ControllerService setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getUrl() {
        //http://192.168.1.14/rs/terminal/94a3171a-5604-460a-bd76-cf261a686b6f/on
        return "http://" + ipAddress + ":" + port + "/rs/gpio/" + gpio + "/" + method;
    }

    public ControllerService on() {
        this.method = METHOD_ON;
        invoke();
        return this;
    }

    public ControllerService off() {
        this.method = METHOD_OFF;
        invoke();
        return this;
    }

    public ControllerService status() {
        this.method = "";
        invoke();
        return this;
    }

    private void invoke(){
        Response response = null;
        status = "error";
        try {
            Client client = new ResteasyClientBuilder()
                    .establishConnectionTimeout(2, TimeUnit.SECONDS)
                    .socketTimeout(4, TimeUnit.SECONDS)
                    .build();
            ResteasyWebTarget target = (ResteasyWebTarget) client.target(getUrl());
            //response = target.request().accept(MediaType.APPLICATION_JSON).get();
            response = target.request().get();
            if ( response.getStatus() == Response.Status.OK.getStatusCode() ) {
                System.out.println("============ [INVOKER] response: OK");
                status = "success";
            } else {
                this.status = response.getStatusInfo().getReasonPhrase();
            }
        } catch (Exception e) {
            // nothing to do here
        } finally {
            if (response != null){
                response.close();  // You should close connections!
            }
        }
    }

}