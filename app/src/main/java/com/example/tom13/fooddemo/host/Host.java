package com.example.tom13.fooddemo.host;

/**
 * Created by tom13 on 06/03/2018.
 */

/**
 * Defines a Host object
 */
public class Host {
    private final String ipv4;
    private final String dns;
    private final int port;

    private Host(HostBuilder builder) {
        this.ipv4 = builder.ipv4;
        this.dns = builder.dns;
        this.port = builder.port;
    }

    public String getIpv4() {
        return ipv4;
    }

    public String getDns() {
        return dns;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return new StringBuilder(this.getIpv4())
                .append(",").append(this.getDns())
                .append(",").append(this.getPort())
                .append("\n").toString();
    }

    public static class HostBuilder {
        private final String ipv4;
        private String dns;
        private int port;

        public HostBuilder(String ipv4) {
            this.ipv4 = ipv4;
        }

        public HostBuilder withDns(String dns) {
            this.dns = dns;
            return this;
        }

        public HostBuilder withPort(int port) {
            this.port = port;
            return this;
        }

        public Host build() {
            return new Host(this);
        }

    }
}
