package org.tensorflow.demo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Activity2 extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        backbutton();

        listView = (ExpandableListView) findViewById(R.id.lvExp);
        initData();
        listAdapter = new org.tensorflow.demo.env.ExpandableListAdapter(this, listDataHeader, listHash);
        listView.setAdapter(listAdapter);
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("What are the types of eczema this app detect?");
        listDataHeader.add("What is the difference between these 3 different types of eczema?");
        listDataHeader.add("What should I do if I have eczema?");

        List<String> acute = new ArrayList<>();
        acute.add("This eczema detector classifies eczema into 3 different types, Acute eczema, Subacute eczema and Chronic eczema");

        List<String> subacute = new ArrayList<>();
        subacute.add("test\ntest");

        List<String> chronic = new ArrayList<>();
        chronic.add("");

        listHash.put(listDataHeader.get(0),acute);
        listHash.put(listDataHeader.get(1),subacute);
        listHash.put(listDataHeader.get(2),chronic);
    }


    private void backbutton() {
        FloatingActionButton floatingbtn6 = (FloatingActionButton) findViewById(R.id.floatingActionButton6);
        floatingbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Log.i("MyApp", "this is a magic log message!");*/
                ///Toast.makeText(getActivity().getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
                openCameraConnectionFragment();
            }
        });
    }
    public void openCameraConnectionFragment() {
        Intent intent2 = new Intent(Activity2.this , ClassifierActivity.class);
        startActivity(intent2);
    }

}
