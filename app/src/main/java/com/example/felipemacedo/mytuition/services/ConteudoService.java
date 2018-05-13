package com.example.felipemacedo.mytuition.services;

import android.content.Context;

import com.example.felipemacedo.mytuition.listeners.JsonRequestListener;

public interface ConteudoService {

    void findByMateriaId(Context context, Long id, JsonRequestListener listener);
}
