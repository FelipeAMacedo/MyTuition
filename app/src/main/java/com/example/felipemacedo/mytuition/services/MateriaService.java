package com.example.felipemacedo.mytuition.services;

import android.content.Context;

import com.example.felipemacedo.mytuition.dto.save.wrapper.LoginWrapper;
import com.example.felipemacedo.mytuition.dto.save.wrapper.UsuarioSaveWrapper;
import com.example.felipemacedo.mytuition.listeners.JsonRequestListener;

public interface MateriaService {

    void findByDisciplinaId(Context context, Long id, JsonRequestListener listener);
}
