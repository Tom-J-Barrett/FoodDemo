package com.example.tom13.fooddemo.backgroundProcesses;

import android.os.AsyncTask;

import com.example.tom13.fooddemo.host.Host;
import com.example.tom13.fooddemo.presenters.CaptureImagePresenter;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.mock;

/**
 * Created by tom13 on 20/03/2018.
 */

public class backgroundProcessesTest {

    private AsyncTask calorieEstimation;
    private AsyncTask uploadImage;
    private CaptureImagePresenter captureImagePresenter;
    private Host host;
    private static final String food = "banana";

    @Before
    public void setup() {
        captureImagePresenter = mock(CaptureImagePresenter.class);
        host = new Host.HostBuilder("52.214.205.157")
                .withDns("ec2-52-214-205-157.eu-west-1.compute.amazonaws.com")
                .withPort(5000)
                .withRoute("/classifyImage/")
                .build();
        calorieEstimation = new CalorieEstimation(food, captureImagePresenter);
        uploadImage = new UploadImage(host, "Image", captureImagePresenter);
    }

    @Test
    public void shouldGetReturnCalories() {
        calorieEstimation.execute();
    }

    @Test
    public void shouldSendImage() {
        uploadImage.execute();
    }
}
