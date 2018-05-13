package com.example.felipemacedo.mytuition.services.impl;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.felipemacedo.mytuition.conf.Configuration;
import com.example.felipemacedo.mytuition.dto.save.wrapper.AtualizacaoExperienciaWrapper;
import com.example.felipemacedo.mytuition.dto.save.wrapper.LoginWrapper;
import com.example.felipemacedo.mytuition.dto.save.wrapper.UsuarioSaveWrapper;
import com.example.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.example.felipemacedo.mytuition.services.HeroiService;
import com.example.felipemacedo.mytuition.services.UsuarioService;
import com.example.felipemacedo.mytuition.utils.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class HeroiServiceImpl implements HeroiService {

    private static final String urlHeroi = Configuration.API_URL + "heroi";

    @Override
    public void adicionarExperiencia(Context context, AtualizacaoExperienciaWrapper wrapper, JsonRequestListener listener) {
        String url = urlHeroi + "/aumentarExperiencia";

        JSONObject jsonBody = null;

        try {
            jsonBody = getJSONObject(wrapper);
        } catch (JSONException e) {
            e.printStackTrace();
            jsonBody = new JSONObject();
        }

        JsonObjectRequest postRequest = buildRequest(context, url, jsonBody, listener);

        Volley.newRequestQueue(context).add(postRequest);
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
                .create();

        String json = gson.toJson(object).replace("\n", "");
        return new JSONObject(json);
    }
}
