package com.example.tom13.fooddemo.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.example.tom13.fooddemo.R;

import java.io.File;

import static android.app.Activity.RESULT_OK;

/**
 * Created by tom13 on 06/03/2018.
 */

public class CaptureImagePresenter {

    private Activity activity;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    public CaptureImagePresenter(Activity activity) {
        this.activity = activity;
    }

    public void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null)
            activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ImageView imageView = activity.findViewById(R.id.imageView);
            imageView.setImageBitmap(imageBitmap);
        }
    }
}
