package com.example.felipemacedo.mytuition.services;

import android.content.Context;

import com.example.felipemacedo.mytuition.dto.save.wrapper.LoginWrapper;
import com.example.felipemacedo.mytuition.dto.save.wrapper.UsuarioSaveWrapper;
import com.example.felipemacedo.mytuition.listeners.JsonRequestListener;

public interface UsuarioService {

    void registrar(Context context, UsuarioSaveWrapper wrapper, JsonRequestListener listener);
    void logar(Context context, LoginWrapper wrapper, JsonRequestListener listener);
}
