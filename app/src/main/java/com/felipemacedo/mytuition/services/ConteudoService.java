package com.felipemacedo.mytuition.services;

import android.content.Context;

import com.felipemacedo.mytuition.listeners.JsonRequestListener;

public interface ConteudoService {

    void findByMateriaId(Context context, Long id, JsonRequestListener listener);
    void buscarQuestoes(Context context, Long id, JsonRequestListener listener);
}
