package com.example.felipemacedo.mytuition.services;

import android.content.Context;

import com.example.felipemacedo.mytuition.dto.save.wrapper.UsuarioMateriaSaveWrapper;
import com.example.felipemacedo.mytuition.listeners.JsonRequestListener;

public interface UsuarioMateriaService {

    void finalizarMateria(Context context, UsuarioMateriaSaveWrapper wrapper, JsonRequestListener listener);
    void iniciarMateria(Context context, UsuarioMateriaSaveWrapper wrapper, JsonRequestListener listener);
}
