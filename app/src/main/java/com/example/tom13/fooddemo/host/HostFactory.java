package com.example.tom13.fooddemo.host;

/**
 * Created by tom13 on 06/03/2018.
 */

public class HostFactory {

    public Host createHost() {
        Host host = new Host.HostBuilder("34.241.30.253")
                    .withDns("ec2-34-241-30-253.eu-west-1.compute.amazonaws.com")
                    .withPort(5000)
                    .withRoute("/classifyImage/")
                    .build();
        return host;
    }
}
