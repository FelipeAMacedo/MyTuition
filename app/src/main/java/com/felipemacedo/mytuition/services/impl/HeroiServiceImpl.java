package com.felipemacedo.mytuition.services.impl;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.felipemacedo.mytuition.conf.Configuration;
import com.felipemacedo.mytuition.dto.save.wrapper.AtualizacaoExperienciaWrapper;
import com.felipemacedo.mytuition.dto.save.wrapper.AumentarPontosWrapper;
import com.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.felipemacedo.mytuition.services.HeroiService;
import com.felipemacedo.mytuition.utils.RequestQueueSingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

public class HeroiServiceImpl implements HeroiService {

    private static final String urlHeroi = Configuration.API_URL + "heroi";

    @Override
    public void aumentarPontos(Context context, AumentarPontosWrapper wrapper, JsonRequestListener listener) {
        String url = urlHeroi + "/aumentarPontos";

        JSONObject jsonBody;

        try {
            jsonBody = getJSONObject(wrapper);
        } catch (JSONException e) {
            e.printStackTrace();
            jsonBody = new JSONObject();
        }

        JsonObjectRequest postRequest = buildPostRequest(context, url, jsonBody, listener);

        RequestQueueSingleton.getInstance(context).addToRequestQueue(postRequest);
    }

    @Override
    public void adicionarExperiencia(Context context, AtualizacaoExperienciaWrapper wrapper, JsonRequestListener listener) {
        String url = urlHeroi + "/aumentarExperiencia";

        JSONObject jsonBody;

        try {
            jsonBody = getJSONObject(wrapper);
        } catch (JSONException e) {
            e.printStackTrace();
            jsonBody = new JSONObject();
        }

        JsonObjectRequest postRequest = buildPostRequest(context, url, jsonBody, listener);

        RequestQueueSingleton.getInstance(context).addToRequestQueue(postRequest);
    }

    private JsonObjectRequest buildPostRequest(Context context, String url, JSONObject jsonBody, JsonRequestListener listener) {
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
                .create();

        String json = gson.toJson(object).replace("\n", "");
        return new JSONObject(json);
    }
}