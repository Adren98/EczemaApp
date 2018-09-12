package org.tensorflow.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.services.s3.AmazonS3Client;
import org.tensorflow.demo.env.ImageUtils;

import java.io.File;
import java.util.Date;

public class PopActivity extends Activity {

    ImageView imageView;
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        backbutton();
        uploadImageToS3();

//        imageView = (ImageView)findViewById(R.id.imageView);
//        Bundle extras = getIntent().getExtras();
//        Bitmap bmp = extras.getParcelable("Bitmap");
//        imageView.setImageBitmap(bmp);

        byte[] byteArray = getIntent().getByteArrayExtra("image");
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageBitmap(bmp);
    }

    private void backbutton() {
        Button btn4 = (Button) findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Log.i("MyApp", "this is a magic log message!");*/
                ///Toast.makeText(getActivity().getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
                openCameraConnectionFragment();
            }
        });
    }

    public void openCameraConnectionFragment() {
        Intent intent2 = new Intent(PopActivity.this, ClassifierActivity.class);
        startActivity(intent2);
    }

    // send images into S3 bucket
    private void uploadImageToS3() {
        Button btn_accept = (Button) findViewById(R.id.button2);
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = new Date();
                long time = date.getTime();
                final String fname = "m" + time + ".png";
                ImageUtils.saveBitmap(bmp, fname);
                final String root =
                        Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "tensorflow";
                final File upFile = new File(root, fname);

                TransferUtility transferUtility =
                        TransferUtility.builder()
                                .context(getApplicationContext())
                                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                                .s3Client(new AmazonS3Client(AWSMobileClient.getInstance().getCredentialsProvider()))
                                .build();

                TransferObserver uploadObserver =
                        transferUtility.upload(fname, upFile);

                // Attach a listener to the observer to get state update and progress notifications
                uploadObserver.setTransferListener(new TransferListener() {

                    @Override
                    public void onStateChanged(int id, TransferState state) {
                        if (TransferState.COMPLETED == state) {
                            // Handle a completed upload.
                            upFile.delete();
                            openCameraConnectionFragment();
                        }
                    }

                    @Override
                    public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                        float percentDonef = ((float) bytesCurrent / (float) bytesTotal) * 100;
                        int percentDone = (int)percentDonef;

                        Log.d("PopActivity", "ID:" + id + " bytesCurrent: " + bytesCurrent
                                + " bytesTotal: " + bytesTotal + " " + percentDone + "%");
                    }

                    @Override
                    public void onError(int id, Exception ex) {
                        // Handle errors
                    }

                });

            }
        });
    }
}
