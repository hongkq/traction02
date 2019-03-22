package com.example.traction02.util;
public class UrlBean {
    /**
     * url : 192.168.1.101
     * port : 8088
     */
    private static String url;
    private static String port;

    public static String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }


}
