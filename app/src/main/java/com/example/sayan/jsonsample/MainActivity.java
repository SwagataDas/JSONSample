package com.example.sayan.jsonsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

//http://jsonviewer.stack.hu/
//http://jsoneditoronline.org/
public class MainActivity extends AppCompatActivity {

    private String jSONResult = "{\"image_array\": [\"https://www.w3schools.com/w3images/fjords.jpg\",\"https://www.w3schools.com/w3images/fjords.jpg\",\"https://www.w3schools.com/w3images/fjords.jpg\"],\"letter_object\": { \"a\": \"b\", \"c\": \"d\", \"e\": \"f\"},\"image\": \"https://www.w3schools.com/w3images/fjords.jpg\"}";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        try {
            //outer json object
            JSONObject jsonObject = new JSONObject(jSONResult);

            //inner image url as string
            String imageUrl = jsonObject.getString("image");
            Log.d("sayan1", "image url string: "+ imageUrl);

            //inner json object
            JSONObject innerLetterJSONObject = jsonObject.getJSONObject("letter_object");
            //inner json object data as string
            String a = innerLetterJSONObject.getString("a");
            String c = innerLetterJSONObject.getString("c");
            String e = innerLetterJSONObject.getString("e");
            Log.d("sayan1", "letters string: "+ a +", "+ c +", "+ e +", ");

            //inner json array
            JSONArray innerImageArray = jsonObject.getJSONArray("image_array");

            //for loop to get json array values using i as index
            for (int i = 0 ; i < 3 ; i++){
                Log.d("sayan1", "image url "+ i +": "+ innerImageArray.getString(i));
            }
            // Using Picasso library for loading image from url
            Picasso.with(this)                         // with (context)
                    .load(imageUrl)                    //url for image to load
                    .placeholder(R.drawable.loading)   //placeholder image for showing when image is loading from url
                    .error(R.drawable.error)           //if any connection problem or any other error found
                    .into(imageView);                  // into the image view when the picture will be loaded
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
