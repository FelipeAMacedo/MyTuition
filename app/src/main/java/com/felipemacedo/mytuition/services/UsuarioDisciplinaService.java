package com.felipemacedo.mytuition.services;

import android.content.Context;

import com.felipemacedo.mytuition.dto.save.wrapper.UsuarioDisciplinaSaveWrapper;
import com.felipemacedo.mytuition.listeners.JsonRequestListener;

public interface UsuarioDisciplinaService {

    void finalizarDisciplina(Context context, UsuarioDisciplinaSaveWrapper wrapper, JsonRequestListener listener);
    void iniciarDisciplina(Context context, UsuarioDisciplinaSaveWrapper wrapper, JsonRequestListener listener);
}
