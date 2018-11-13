package com.example.darabsingh.readjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Button btn_ReadJSON;
    private TextView txtJSON_Result;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_ReadJSON=(Button)findViewById(R.id.btn_ReadJSON);
        txtJSON_Result=(TextView)findViewById(R.id.txtJSON_Result);
        mQueue= Volley.newRequestQueue(this);

        btn_ReadJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonResponse();
            }
        });
    }
    private void jsonResponse()
    {
        String url="https://api.myjson.com/bins/htphy";
        final JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("students");
                            for (int i=0; i<jsonArray.length();i++)
                            {
                                JSONObject student=jsonArray.getJSONObject(i);
                                String firstName= student.getString("firstname");
                                int age= student.getInt("age");
                                String mail=student.getString("mail");;
                                txtJSON_Result.append(firstName+","+age+","+mail+"\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);

    }
}
