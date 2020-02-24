package com.example.pushnotification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    String url = "http://192.168.1.67/php_scripts/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String token = FirebaseInstanceId.getInstance().getToken();
        registerToken(token);

    }
    private void registerToken(final String token) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    String success = jsonObject.getString("success");
//                    if (success.equals("1")) {
//                        
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "error"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "error"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String>params = new HashMap<>();
                params.put("Token",token);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }

}
