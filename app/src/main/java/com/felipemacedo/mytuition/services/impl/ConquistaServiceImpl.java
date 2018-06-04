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
import com.felipemacedo.mytuition.dto.save.wrapper.ConquistaBuscaWrapper;
import com.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.example.felipemacedo.mytuition.services.ConquistaService;
import com.felipemacedo.mytuition.utils.LocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConquistaServiceImpl implements ConquistaService {

    private static final String urlConquista = Configuration.API_URL + "conquista";

    @Override
    public void buscarTodas(Context context, String email, JsonRequestListener listener) {
        String url = urlConquista + "/buscarNovasAtualizacoes";

        JSONObject jsonBody;

        try {
            jsonBody = getJSONObject(buscarConquistasLocais());
        } catch (JSONException e) {
            e.printStackTrace();
            jsonBody = new JSONObject();
        }


        JsonObjectRequest getRequest = buildPostRequest(context, url, jsonBody, email, listener);

        Volley.newRequestQueue(context).add(getRequest);

    }

    private ConquistaBuscaWrapper buscarConquistasLocais() {
        // TODO: buscar todas as conquistas que se tem localmente
        ConquistaBuscaWrapper wrapper = new ConquistaBuscaWrapper();
        wrapper.setConquistaBuscaDTO(new ArrayList<>());

        return wrapper;
    }

    private JsonObjectRequest buildPostRequest(Context context, String url, JSONObject jsonBody, String email, JsonRequestListener listener) {

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

    private JSONObject getJSONObject(Object object) throws JSONException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        String json = gson.toJson(object).replace("\n", "");
        return new JSONObject(json);
    }
}
