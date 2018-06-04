package com.felipemacedo.mytuition.services;

import android.content.Context;

import com.felipemacedo.mytuition.listeners.JsonRequestListener;

public interface ConquistaService {

    void buscarTodas(Context context, String email, JsonRequestListener listener);
}
