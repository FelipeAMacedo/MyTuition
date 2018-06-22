package com.felipemacedo.mytuition.services.impl;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.felipemacedo.mytuition.conf.Configuration;
import com.felipemacedo.mytuition.dto.save.wrapper.UsuarioMateriaSaveWrapper;
import com.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.felipemacedo.mytuition.services.UsuarioMateriaService;
import com.felipemacedo.mytuition.utils.LocalDateTimeAdapter;
import com.felipemacedo.mytuition.utils.RequestQueueSingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class UsuarioMateriaServiceImpl implements UsuarioMateriaService {

    private static final String urlUsuarioMateria = Configuration.API_URL + "usuarioMateria";

    @Override
    public void finalizarMateria(Context context, UsuarioMateriaSaveWrapper wrapper, JsonRequestListener listener) {
        StringBuilder url = new StringBuilder(urlUsuarioMateria).append("/finalizar");

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
    public void iniciarMateria(Context context, UsuarioMateriaSaveWrapper wrapper, JsonRequestListener listener) {
        StringBuilder url = new StringBuilder(urlUsuarioMateria).append("/iniciar");

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