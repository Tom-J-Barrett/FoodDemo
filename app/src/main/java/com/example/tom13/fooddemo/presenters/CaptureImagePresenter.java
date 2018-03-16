package com.example.tom13.fooddemo.presenters;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.example.tom13.fooddemo.R;
import com.example.tom13.fooddemo.backgroundProcesses.CalorieEstimation;
import com.example.tom13.fooddemo.backgroundProcesses.UploadImage;
import com.example.tom13.fooddemo.calorieEstimation.CalorieEstimationFactory;
import com.example.tom13.fooddemo.host.HostFactory;
import com.example.tom13.fooddemo.image.Base64Image;
import com.example.tom13.fooddemo.views.CaptureImageActivity;
import com.example.tom13.fooddemo.views.MainActivity;

import java.io.File;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;

/**
 * Created by tom13 on 06/03/2018.
 */

public class CaptureImagePresenter {

    private Activity activity;
    private static final int CONTENT_REQUEST=1337;
    private File output=null;

    public CaptureImagePresenter(Activity activity) {
        this.activity = activity;
    }

    public void takePicture() {
        Intent i =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

        output = new File(dir, "CameraContentDemo.jpg");
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));

        activity.startActivityForResult(i, CONTENT_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (EasyPermissions.hasPermissions(activity, galleryPermissions)) { }
        else {
            EasyPermissions.requestPermissions(activity, "Access for storage",
                    101, galleryPermissions);
        }
        if (requestCode == CONTENT_REQUEST && resultCode == RESULT_OK) {
            ImageView imageView= activity.findViewById(R.id.imageView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(output.getAbsolutePath()));
        }
    }

    public void cancel() {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    public void sendImage() {
        UploadImage uploadImage = new UploadImage(new HostFactory().createHost(), new Base64Image(output).getBase64Image(), this);
        uploadImage.execute();
    }

    public void responseFromSever(String response) {
        String[] results = response.split(",");
        getCalories(results[0]);
    }

    public void getCalories(String query) {
        CalorieEstimation calorieEstimation = new CalorieEstimationFactory(query, this).getCalorieEstimator();
        calorieEstimation.execute();
    }

    public void updateUI(String results) {
        CaptureImageActivity captureImageActivity = (CaptureImageActivity) activity;
        captureImageActivity.onResponse(results);
    }

    public Activity getActivity(){
        return activity;
    }
}
