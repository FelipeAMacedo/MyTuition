package com.felipemacedo.mytuition.services.impl;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.felipemacedo.mytuition.conf.Configuration;
import com.felipemacedo.mytuition.dto.save.wrapper.UsuarioDisciplinaSaveWrapper;
import com.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.felipemacedo.mytuition.services.UsuarioDisciplinaService;
import com.felipemacedo.mytuition.utils.LocalDateTimeAdapter;
import com.felipemacedo.mytuition.utils.RequestQueueSingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class UsuarioDisciplinaServiceImpl implements UsuarioDisciplinaService {

    private static final String urlUsuarioDisciplina = Configuration.API_URL + "usuarioDisciplina";

    @Override
    public void finalizarDisciplina(Context context, UsuarioDisciplinaSaveWrapper wrapper, JsonRequestListener listener) {
        StringBuilder url = new StringBuilder(urlUsuarioDisciplina).append("/finalizar");

        JSONObject jsonBody;

        try {
            jsonBody = getJSONObject(wrapper);
        } catch (JSONException e) {
            e.printStackTrace();
            jsonBody = new JSONObject();
        }

        JsonObjectRequest postRequest = buildRequest(context, url.toString(), Request.Method.PUT, jsonBody, listener);

        RequestQueueSingleton.getInstance(context).addToRequestQueue(postRequest);
    }

    @Override
    public void iniciarDisciplina(Context context, UsuarioDisciplinaSaveWrapper wrapper, JsonRequestListener listener) {
        StringBuilder url = new StringBuilder(urlUsuarioDisciplina).append("/iniciar");

        JSONObject jsonBody;

        try {
            jsonBody = getJSONObject(wrapper);
        } catch (JSONException e) {
            e.printStackTrace();
            jsonBody = new JSONObject();
        }

        JsonObjectRequest postRequest = buildRequest(context, url.toString(), Request.Method.POST, jsonBody, listener);

        RequestQueueSingleton.getInstance(context).addToRequestQueue(postRequest);
    }

    private JsonObjectRequest buildRequest(Context context, String url, int requestMethod, JSONObject jsonBody, JsonRequestListener listener) {
        JsonObjectRequest request = new JsonObjectRequest
                (requestMethod, url, jsonBody, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error);
                    }
                });

        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return request;

    }

    private JSONObject getJSONObject(Object object) throws JSONException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        String json = gson.toJson(object).replace("\n", "");
        return new JSONObject(json);
    }
}
