package org.tensorflow.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PopActivity extends Activity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        backbutton();

        imageView = (ImageView)findViewById(R.id.imageView);
        Bundle extras = getIntent().getExtras();
        Bitmap bitmap = extras.getParcelable("Bitmap");
        imageView.setImageBitmap(bitmap);
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
        Intent intent2 = new Intent(PopActivity.this , ClassifierActivity.class);
        startActivity(intent2);
    }



}
