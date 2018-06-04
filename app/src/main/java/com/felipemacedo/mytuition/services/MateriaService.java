package com.example.felipemacedo.mytuition.services;

import android.content.Context;

import com.felipemacedo.mytuition.listeners.JsonRequestListener;

public interface MateriaService {

    void findByDisciplinaId(Context context, Long id, String email, JsonRequestListener listener);
}
