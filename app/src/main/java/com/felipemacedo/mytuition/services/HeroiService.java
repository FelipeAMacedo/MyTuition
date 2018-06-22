package com.felipemacedo.mytuition.services;

import android.content.Context;

import com.felipemacedo.mytuition.dto.save.wrapper.AtualizacaoExperienciaWrapper;
import com.felipemacedo.mytuition.dto.save.wrapper.AumentarPontosWrapper;
import com.felipemacedo.mytuition.listeners.JsonRequestListener;

public interface HeroiService {

    void adicionarExperiencia(Context context, AtualizacaoExperienciaWrapper wrapper, JsonRequestListener listener);

    void aumentarPontos(Context context, AumentarPontosWrapper wrapper, JsonRequestListener listener);
}
