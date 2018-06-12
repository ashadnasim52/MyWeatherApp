package com.superoriginal.rootashadnasim.myweatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    ImageView we;
    EditText textofcity;
    Button buttontocheck;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textofcity =findViewById(R.id.editText);
        buttontocheck=findViewById(R.id.button);
        we=findViewById(R.id.w);
        we.animate().rotation(360).setDuration(10000);
        String humidity,pressure,description,main,temp,tempmax,tempmin,lattitutde,longitude;
        buttontocheck.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(textofcity.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"PLEASE ENTER SOMETHING",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String baseurl="http://api.openweathermap.org/data/2.5/weather?q=";
                    String lasturl="&appid=3036317a20d7b997758b04defd1d4ce8";
                    String city=textofcity.getText().toString();
                    String myurl=baseurl+city+lasturl;
                    Log.i("url","is "+myurl);
                    JsonObjectRequest newjsonobjectrequest=new JsonObjectRequest(Request.Method.GET, myurl, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.i("responce","is "+response);


                                    try
                                    {
                                        String coord=response.getString("coord");


                                        JSONObject main=response.getJSONObject("main");
                                        String temp=main.getString("temp");
                                        String pressure=main.getString("pressure");
                                        String humidity=main.getString("humidity");
                                        String tempmax=main.getString("temp_max");
                                        String tempmian=main.getString("temp_min");
                                        JSONObject wind=response.getJSONObject("wind");
                                        String windspeed=wind.getString("speed");
                                        String winddegree=wind.getString("deg");




                                        Log.i("temp","is "+temp);
                                        Log.i("pressure ","is "+pressure);
                                        Log.i("humidity ","is "+humidity);
                                        Log.i("tempmax ","is "+tempmax);
                                        Log.i("tempminnium ","is "+tempmian);
                                        Log.i("windspeed ","is "+windspeed);
                                        Log.i("winddegree ","is "+winddegree);









                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(),"SOMETHING WENTS WRONG",Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                    mysingleton.getInstance(getApplicationContext()).addToRequestque(newjsonobjectrequest);

                }

                Intent i=new Intent(getApplicationContext(),Main2Activity.class);

                startActivity(i);
                finish();


            }


        });
    }
}
