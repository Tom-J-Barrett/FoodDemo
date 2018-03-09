package com.example.tom13.fooddemo.requests;

import com.example.tom13.fooddemo.host.Host;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by tom13 on 09/03/2018.
 */

public class RequestFactory {

    private Host host;
    private List<NameValuePair> nameValuePairs;

    public RequestFactory(Host host, List<NameValuePair> nameValuePairs) {
        this.host = host;
        this.nameValuePairs = nameValuePairs;
    }

    public HttpUriRequest getRequest() throws UnsupportedEncodingException {
        HttpPost httppost = new HttpPost(host.getUrl());
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        return httppost;
    }
}
