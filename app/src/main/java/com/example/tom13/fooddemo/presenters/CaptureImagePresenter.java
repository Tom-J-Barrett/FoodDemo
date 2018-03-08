package com.example.tom13.fooddemo.presenters;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.tom13.fooddemo.R;
import com.example.tom13.fooddemo.views.MainActivity;
import com.example.tom13.fooddemo.views.UserLogsActivity;

import java.io.File;

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

    }


}
