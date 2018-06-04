package com.example.felipemacedo.mytuition.services;

import android.content.Context;

import com.felipemacedo.mytuition.listeners.JsonRequestListener;

public interface DisciplinaService {

    void buscarTodas(Context context, String email, JsonRequestListener listener);
}
