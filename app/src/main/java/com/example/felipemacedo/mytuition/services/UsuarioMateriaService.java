package com.example.felipemacedo.mytuition.services;

import android.content.Context;

import com.example.felipemacedo.mytuition.listeners.JsonRequestListener;

public interface UsuarioMateriaService {

    void finalizarMateria(Context context, String emailUsuario, Long materiaId, JsonRequestListener listener);
}
