package com.example.felipemacedo.mytuition.services;

import android.content.Context;

import com.example.felipemacedo.mytuition.dto.save.wrapper.AtualizacaoExperienciaWrapper;
import com.example.felipemacedo.mytuition.listeners.JsonRequestListener;

public interface HeroiService {

    void adicionarExperiencia(Context context, AtualizacaoExperienciaWrapper wrapper, JsonRequestListener listener);
}
