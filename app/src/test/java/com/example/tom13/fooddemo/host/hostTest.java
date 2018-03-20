package com.example.tom13.fooddemo.host;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by tom13 on 20/03/2018.
 */

public class hostTest {

    private Host host;

    private static final String ip = "52.214.205.157";
    private static final String dns = "ec2-52-214-205-157.eu-west-1.compute.amazonaws.com";
    private static final int port = 5000;
    private static final String route = "/classifyImage/";
    private static final String urlString = "http://ec2-52-214-205-157.eu-west-1.compute.amazonaws.com:5000/classifyImage/";

    @Before
    public void setUp() {
        host = new Host.HostBuilder("52.214.205.157")
                .withDns("ec2-52-214-205-157.eu-west-1.compute.amazonaws.com")
                .withPort(5000)
                .withRoute("/classifyImage/")
                .build();
    }

    @Test
    public void shouldAssertCorrectValues() {
        assert this.ip.equals(host.getIpv4());
        assert this.dns.equals(host.getDns());
        assert this.port == host.getPort();
        assert this.route.equals(host.getRoute());
        assert this.urlString.equals(host.getUrl());
    }
}
