package org.tensorflow.demo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;

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

        listDataHeader.add("What are the types of eczema can this app detect?");
        listDataHeader.add("What is the difference between the 3 different types of eczema?");
        listDataHeader.add("What should I do if I have eczema?");

        List<String> types = new ArrayList<>();
        types.add("This eczema detector classifies 3 different types of eczema: ACUTE, SUBACUTE and CHRONIC eczema.");

        List<String> symptoms = new ArrayList<>();
        symptoms.add("\nACUTE\n\n" +
                "\"Acute\" refers to an eczema rash that has just started. Some characteristics in the stage of acute eczema include:\n\n" +
                "- Blisters\n" +
                "- Extreme redness\n" +
                "- Intense itching\n" +
                "- Pain\n" +
                "- Tenderness\n" +
                "- Swelling\n" +
                "- Heat\n");

        symptoms.add("\nSUBACUTE\n\n" +
                "The subacute stage is the transitional phase between the acute and chronic stages. Eczema doesn't always spend the same amount of time in the subacute stage." +
                " Each case of eczema transitions from stage to stage differently. The eczema rash evolves and takes on these new characteristics:\n\n" +
                "- Flaky, scaly skin\n" +
                "- Less redness\n" +
                "- Cracks in the skin\n" +
                "- Itching, burning, and/or stinging\n");

        symptoms.add("\nCHRONIC\n\n" +
                "Symptoms are at their most severe during the chronic stage. The chronic stage refers to eczema flares that last three or more months." +
                " Chronic eczema is quite different from the other two stages in the following ways:\n\n" +
                "- Thickened, leathery-looking skin or lichenification\n" +
                "- Accentuated skin lines\n" +
                "- Cracks in the skin\n" +
                "- Skin appears dark and dull\n" +
                "- Larger areas of skin breakdown called excoriations\n" +
                "- Itching\n");

        List<String> recommendations = new ArrayList<>();
        recommendations.add("\nACUTE\n\n" +
                "- Apply some eczema creams if the symptoms are mild\n" +
                "- For information: https://www.webmd.com/skin-problems-and-treatments/eczema/treatment-16/treatments-for-you\n");
        recommendations.add("\nSUBACUTE\n\n" +
                "- Consult a dermatologist for treatments");
        recommendations.add("\nCHRONIC\n\n" +
                "- Visit a hospital immediately");

        listHash.put(listDataHeader.get(0),types);
        listHash.put(listDataHeader.get(1),symptoms);
        listHash.put(listDataHeader.get(2),recommendations);
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
