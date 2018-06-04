package com.example.felipemacedo.mytuition.services.impl;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.felipemacedo.mytuition.conf.Configuration;
import com.example.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.example.felipemacedo.mytuition.services.DisciplinaService;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DisciplinaServiceImpl implements DisciplinaService {

    private static final String disciplinaUrl = Configuration.API_URL + "disciplina";

    @Override
    public void buscarTodas(Context context, String email, JsonRequestListener listener) {
        String url = disciplinaUrl;

        JsonObjectRequest getRequest = buildGetRequest(context, url, email, listener);

        Volley.newRequestQueue(context).add(getRequest);

    }

    private JsonObjectRequest buildGetRequest(Context context, String url, String email, JsonRequestListener listener) {

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                //headers.put("Content-Type", "application/json");
                headers.put("email", email);
                return headers;
            }
        };


        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return request;

    }
}
