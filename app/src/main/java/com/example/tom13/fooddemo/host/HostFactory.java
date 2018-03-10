package com.example.tom13.fooddemo.host;

/**
 * Created by tom13 on 06/03/2018.
 */

public class HostFactory {

    public Host createHost() {
        Host host = new Host.HostBuilder("34.245.171.85")
                    .withDns("ec2-34-245-171-85.eu-west-1.compute.amazonaws.com")
                    .withPort(5000)
                    .withDirectory("/classifyImage/")
                    .build();

        return host;
    }
}
