package com.felipemacedo.mytuition.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.felipemacedo.mytuition.conf.Configuration;
import com.felipemacedo.mytuition.listeners.JsonRequestListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NetworkManager {

    private Context context;

    private static NetworkManager instance = null;

    private static final String TAG = "NetworkManager";

    private static final String urlPrefix = Configuration.API_URL;

    public RequestQueue requestQueue;

    private NetworkManager(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized NetworkManager getInstance(Context context)
    {
        if (null == instance)
            instance = new NetworkManager(context);
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized NetworkManager getInstance()
    {
        if (null == instance)
        {
            throw new IllegalStateException(NetworkManager.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    public void somePostRequestReturningString(String url, final JsonRequestListener<String> listener)
    {

        url = urlPrefix + url;

        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("param1", "a");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonParams),
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.d(TAG + ": ", "somePostRequest Response : " + response.toString());
                        if(null != response.toString())
                            listener.onSuccess(response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        if (null != error.networkResponse)
                        {
                            Log.d(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);
                            listener.onError("false");
                        }
                    }
                });

        requestQueue.add(request);
    }
}
