package com.felipemacedo.mytuition.services.impl;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.felipemacedo.mytuition.conf.Configuration;
import com.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.felipemacedo.mytuition.services.ConteudoService;
import com.felipemacedo.mytuition.utils.RequestQueueSingleton;

import org.json.JSONObject;

public class ConteudoServiceImpl implements ConteudoService {

    private static final String conteudoUrl = Configuration.API_URL + "conteudo";

    @Override
    public void findByMateriaId(Context context, Long id, JsonRequestListener listener) {
        String url = conteudoUrl + "/materia/" + id;

        JsonObjectRequest getRequest = buildGetRequest(context, url, listener);

        RequestQueueSingleton.getInstance(context).addToRequestQueue(getRequest);

    }

    @Override
    public void buscarQuestoes(Context context, Long id, JsonRequestListener listener) {

        String url = conteudoUrl + "/materia/" + id + "/questoes";

        JsonObjectRequest getRequest = buildGetRequest(context, url, listener);

        RequestQueueSingleton.getInstance(context).addToRequestQueue(getRequest);
    }

    private JsonObjectRequest buildGetRequest(Context context, String url, JsonRequestListener listener) {

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
                });

        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return request;

    }
}