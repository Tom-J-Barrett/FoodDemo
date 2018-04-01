package com.example.tom13.fooddemo.presenters;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.example.tom13.fooddemo.R;
import com.example.tom13.fooddemo.backgroundProcesses.CalorieEstimation;
import com.example.tom13.fooddemo.backgroundProcesses.UploadImage;
import com.example.tom13.fooddemo.calorieEstimation.CalorieEstimationFactory;
import com.example.tom13.fooddemo.foodLog.FoodLogImpl;
import com.example.tom13.fooddemo.host.HostFactory;
import com.example.tom13.fooddemo.image.Base64Image;
import com.example.tom13.fooddemo.image.ExifUtil;
import com.example.tom13.fooddemo.storage.DAO;
import com.example.tom13.fooddemo.storage.DAOFactory;
import com.example.tom13.fooddemo.views.CaptureImageActivity;
import com.example.tom13.fooddemo.views.MainActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;

/**
 * Created by tom13 on 06/03/2018.
 * Presenter class to control the CaptureImageActivity View.
 */

public class CaptureImagePresenter {

    private Activity activity;
    private static final int CONTENT_REQUEST=1337;
    private File output=null;
    private String top1_prediction;
    private String calories;
    private Map<String, Runnable> imageCapture;
    private static final int GALLERY = 1;
    private static Bitmap Image = null;
    private static Bitmap rotateImage = null;

    public CaptureImagePresenter(Activity activity, String medium) {
        this.activity = activity;
        this.imageCapture = new HashMap<>();
        imageCapture.put("camera", () -> takePicture());
        imageCapture.put("gallery", () -> gallery());

        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        output = new File(dir, "FoodImage.jpg");

        imageCapture.get(medium).run();
        dir.delete();
    }

    private void gallery() {
        Intent i =new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));

        activity.startActivityForResult(i, GALLERY);
    }

    private void takePicture() {
        Intent i =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));
        activity.startActivityForResult(i, CONTENT_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ImageView imageView= activity.findViewById(R.id.imageView);
        Bitmap bitmap = null;
        Bitmap orientedBitmap;

        if (EasyPermissions.hasPermissions(activity, galleryPermissions)) { }
        else {
            EasyPermissions.requestPermissions(activity, "Access for storage",
                    101, galleryPermissions);
        }

        if (requestCode == GALLERY && resultCode != 0) {
            Uri mImageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), mImageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap.createScaledBitmap(bitmap, 300, 400, false);
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(output);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        }
        if (requestCode == CONTENT_REQUEST || requestCode == GALLERY  && resultCode == RESULT_OK ) {
            bitmap = BitmapFactory.decodeFile(output.getAbsolutePath());
            orientedBitmap = ExifUtil.rotateBitmap(output.getAbsolutePath(), bitmap);
            Bitmap.createScaledBitmap(orientedBitmap, 300, 400, false);
            imageView.setImageBitmap(orientedBitmap);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
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
        top1_prediction = results[0];
        getCalories();
    }

    public void getCalories() {
        CalorieEstimation calorieEstimation = (CalorieEstimation) new CalorieEstimationFactory(top1_prediction, this).getCalorieEstimator();
        calorieEstimation.execute();
    }

    public void updateUI(String results) {
        calories = results;
        CaptureImageActivity captureImageActivity = (CaptureImageActivity) activity;
        captureImageActivity.onResponse(top1_prediction, calories);
    }

    public void writeToLogs(String food, String calories) {
        DAO dao = new DAOFactory().getDAO(activity);
        dao.addFoodLog(new FoodLogImpl.FoodLogBuilder(food)
                .withCalories(Double.parseDouble(calories))
                .withTimestamp(new Date())
                .build());
        output.delete();
        goToMainActivity();
    }

    public void goToMainActivity() {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    public Activity getActivity(){
        return activity;
    }
}
