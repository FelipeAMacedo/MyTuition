package com.example.felipemacedo.mytuition.services.impl;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.felipemacedo.mytuition.conf.Configuration;
import com.example.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.example.felipemacedo.mytuition.services.MateriaService;
import com.example.felipemacedo.mytuition.utils.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class MateriaServiceImpl implements MateriaService {

    private static final String materiaUrl = Configuration.API_URL + "materia";

    @Override
    public void findByDisciplinaId(Context context, Long id, JsonRequestListener listener) {
        String url = materiaUrl + "/disciplina/" + id;

        JsonObjectRequest getRequest = buildGetRequest(context, url, listener);

        Volley.newRequestQueue(context).add(getRequest);

    }

    private JsonObjectRequest buildGetRequest(Context context, String url, JsonRequestListener listener) {

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, response.toString() + "\nTUDO OK", Toast.LENGTH_LONG).show();
                        listener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage() + "\nDEU ERRADO", Toast.LENGTH_LONG).show();
                        listener.onError(error);
                    }
                });

        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return request;

    }
}
