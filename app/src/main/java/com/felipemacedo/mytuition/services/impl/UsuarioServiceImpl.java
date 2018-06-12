package com.felipemacedo.mytuition.services.impl;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.felipemacedo.mytuition.conf.Configuration;
import com.felipemacedo.mytuition.dto.save.wrapper.LoginWrapper;
import com.felipemacedo.mytuition.dto.save.wrapper.UsuarioSaveWrapper;
import com.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.felipemacedo.mytuition.services.UsuarioService;
import com.felipemacedo.mytuition.utils.LocalDateAdapter;
import com.felipemacedo.mytuition.utils.RequestQueueSingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class UsuarioServiceImpl implements UsuarioService {

    private static final String urlUsuario = Configuration.API_URL + "usuario";

    @Override
    public void registrar(Context context, UsuarioSaveWrapper wrapper, JsonRequestListener listener) {
        String url = urlUsuario + "/registrar";

        JSONObject jsonBody;

        try {
            jsonBody = getJSONObject(wrapper);
        } catch (JSONException e) {
            e.printStackTrace();
            jsonBody = new JSONObject();
        }

        JsonObjectRequest postRequest = buildRequest(context, url, jsonBody, listener);

        RequestQueueSingleton.getInstance(context).addToRequestQueue(postRequest);
    }

    @Override
    public void logar(Context context, LoginWrapper wrapper, JsonRequestListener listener) {
        String url = urlUsuario + "/logar";

        JSONObject jsonBody;

        try {
            jsonBody = getJSONObject(wrapper);
        } catch (JSONException e) {
            e.printStackTrace();
            jsonBody = new JSONObject();
        }

        JsonObjectRequest postRequest = buildRequest(context, url, jsonBody, listener);

        RequestQueueSingleton.getInstance(context).addToRequestQueue(postRequest);
    }

    private JsonObjectRequest buildRequest(Context context, String url, JSONObject jsonBody, JsonRequestListener listener) {
        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {

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
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String json = gson.toJson(object).replace("\n", "");
        return new JSONObject(json);
    }
}
