package com.example.tom13.fooddemo.host;

/**
 * Created by tom13 on 06/03/2018.
 */

public class HostFactory {

    public Host createHost() {
        Host host = new Host.HostBuilder("")
                    .withDns("")
                    .withPort(5000)
                    .build();

        return host;
    }
}
