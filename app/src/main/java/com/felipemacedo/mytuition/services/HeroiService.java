package com.felipemacedo.mytuition.services;

import android.content.Context;

import com.felipemacedo.mytuition.dto.save.wrapper.AtualizacaoExperienciaWrapper;
import com.felipemacedo.mytuition.listeners.JsonRequestListener;

public interface HeroiService {

    void adicionarExperiencia(Context context, AtualizacaoExperienciaWrapper wrapper, JsonRequestListener listener);
}
