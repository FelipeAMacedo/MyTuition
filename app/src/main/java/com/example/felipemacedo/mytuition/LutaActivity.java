package com.example.felipemacedo.mytuition;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.felipemacedo.mytuition.conf.Configuration;
import com.example.felipemacedo.mytuition.dto.conteudo.ConteudoResultDTO;
import com.example.felipemacedo.mytuition.dto.heroi.AtualizacaoExperienciaDTO;
import com.example.felipemacedo.mytuition.dto.save.wrapper.AtualizacaoExperienciaWrapper;
import com.example.felipemacedo.mytuition.dto.wrapper.response.ConteudoResponseWrapper;
import com.example.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.example.felipemacedo.mytuition.model.eclipse.Ataque;
import com.example.felipemacedo.mytuition.model.eclipse.Conteudo;
import com.example.felipemacedo.mytuition.services.ConteudoService;
import com.example.felipemacedo.mytuition.services.HeroiService;
import com.example.felipemacedo.mytuition.services.impl.ConteudoServiceImpl;
import com.example.felipemacedo.mytuition.services.impl.HeroiServiceImpl;
import com.example.felipemacedo.mytuition.utils.LocalTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LutaActivity extends AppCompatActivity {

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    private Button btnLutaAtacar;
    private Ataque ataque;

    private ProgressBar vidaVilaoProgress;
    private ProgressBar vidaHeroiProgress;

    private List<Conteudo> questoes;
    private Queue<Conteudo> questoesNaoRespondidas;

    private static final Long MATERIA_ID = 1L;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
//            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                    | View.SYSTEM_UI_FLAG_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
//            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_luta);

        mVisible = true;
//        mControlsView = findViewById(R.id.fullscreen_content_controls);
//        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
//        mContentView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggle();
//            }
//        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
//        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);

        initComponents();
        initListeners();
        loadData();
    }

    private void loadData() {
        ataque = new Ataque();
        //TODO:
//        ataque.setUsuario(Configuration.usuario);

        ataque.setData(LocalDateTime.now());

        ConteudoService conteudoService = new ConteudoServiceImpl();

        conteudoService.buscarQuestoes(this, MATERIA_ID, new JsonRequestListener<JSONObject>() {
            @Override
            public void onSuccess(JSONObject response) {
                Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer())
                        .create();

                ConteudoResponseWrapper wrapper = gson.fromJson(String.valueOf(response), ConteudoResponseWrapper.class);
                questoes = convertConteudoDTOToEntity(wrapper.getConteudos());
//                TODO:
//                ataque.setConteudo(questoes);

                Collections.shuffle(questoes);
                questoesNaoRespondidas = new LinkedBlockingQueue<>(questoes);

            }

            @Override
            public void onError(JSONObject response) {

            }
        });
    }

    private List<Conteudo> convertConteudoDTOToEntity(Set<ConteudoResultDTO> conteudos) {
        List<Conteudo> conteudosConvertidos = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        for (ConteudoResultDTO c : conteudos) {
            conteudosConvertidos.add(mapper.map(c, Conteudo.class));
        }

        return conteudosConvertidos;
    }

    private void initComponents() {
        vidaHeroiProgress = (ProgressBar) findViewById(R.id.pgbHPHeroi);
        vidaVilaoProgress = (ProgressBar) findViewById(R.id.pgbHPVilao);
        btnLutaAtacar = (Button) findViewById(R.id.btnLutaAtacar);

        vidaHeroiProgress.setMax(100);
        vidaHeroiProgress.setProgress(100);
        vidaVilaoProgress.setMax(100);
        vidaVilaoProgress.setProgress(100);
    }

    private void initListeners() {
        btnLutaAtacar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Conteudo conteudo = questoesNaoRespondidas.poll();

                if (questoesNaoRespondidas.isEmpty()) {
                    // TODO: fazer inserção na queue de forma aleatória
                    questoesNaoRespondidas = new LinkedBlockingQueue<>(questoes);
                }

                //TODO: fazer com que o Conteudo Activity esteja preparado para receber somente uma questao e nenhuma materia
                Intent intent = new Intent(LutaActivity.this, ConteudoActivity.class);
                intent.putExtra("somenteQuestao", true);
                intent.putExtra("conteudo", (Serializable) conteudo);

                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1) {
            //TODO: ataque será de acordo com a pontuacao de habilidade de força do vilão
            int ataqueVilao = 3;
            int defesaVilao = 1;

            Configuration.usuario.getHeroiResponseDTO().setForca(10);

            if (data.getExtras().getBoolean("acertou") == false)
                reduzirVida(calcularDano(Configuration.usuario.getHeroiResponseDTO().getDefesa(), ataqueVilao), Adversario.HEROI);
            else
                reduzirVida(calcularDano(defesaVilao, Configuration.usuario.getHeroiResponseDTO().getForca()), Adversario.VILAO);
        }


    }

    private int calcularDano(int defesa, int ataque) {
        return new Double((ataque / defesa) * ataque * 2).intValue();
    }

    private void reduzirVida(int dano, Adversario adversario) {
        HpProgressBarAnimation animation;

        if (adversario.equals(Adversario.HEROI)) {
            int vida = vidaHeroiProgress.getProgress() - dano;

            animation = new HpProgressBarAnimation(vidaHeroiProgress, vidaHeroiProgress.getProgress(), vida);
            animation.setDuration(10000L);
            vidaHeroiProgress.startAnimation(animation);
//            vidaHeroiProgress.setProgress(vida);

//
//            if (vida <= 0) {
//                finalizarLuta(Adversario.VILAO);
//            }
        } else {
            int vida = vidaVilaoProgress.getProgress() - dano;

            animation = new HpProgressBarAnimation(vidaVilaoProgress, vidaVilaoProgress.getProgress(), vida);
            animation.setDuration(10000L);
            vidaVilaoProgress.startAnimation(animation);
//            vidaVilaoProgress.setProgress(vida);

//            if (vida <= 0) {
//                finalizarLuta(Adversario.HEROI);
//            }
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;

        decorView.setSystemUiVisibility(uiOptions);
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
//        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
//        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
//        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    private void finalizarLuta(Adversario vencedor) {
        final Dialog mensagem = new Dialog(this);
        mensagem.setContentView(R.layout.dialog_luta_fim);

        TextView tvResultado = (TextView) mensagem.findViewById(R.id.tvDialogLutaResultado);
        TextView tvPontos = (TextView) mensagem.findViewById(R.id.tvDialogLutaPontos);
        Button btnOk = (Button) mensagem.findViewById(R.id.btnDialogLutaFim);

        int pontosVitoria = 3000;
        int pontosDerrota = -5000;

        if (vencedor.equals(Adversario.HEROI)) {
//            ataque.venceu(true);
//            ataque.setPontos(pontos);

            mudarExperienciaBanco(pontosVitoria);

            tvResultado.setText(R.string.prompt_win_fight);
            tvResultado.setTextColor(Color.parseColor("#37A20D"));

            tvPontos.setText("GANHOU " + pontosVitoria + " PONTOS");
            tvPontos.setTextColor(Color.parseColor("#6C7077"));
            tvPontos.setBackgroundColor(Color.parseColor("#A8FCAA"));
        } else {
//            ataque.venceu(false);
//            ataque.setPontos(pontos);

            mudarExperienciaBanco(pontosDerrota);

            tvResultado.setText(R.string.prompt_lose_fight);
            tvResultado.setTextColor(Color.parseColor("#F95F62"));

            tvPontos.setText("PERDEU " + (pontosDerrota * -1) + " PONTOS");
            tvPontos.setTextColor(Color.parseColor("#980103"));
            tvPontos.setBackgroundColor(Color.parseColor("#F7C9CA"));
        }

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mensagem.cancel();
                LutaActivity.this.finish();
            }
        });

        mensagem.show();
    }

    private void mudarExperienciaBanco(int pontos) {
        AtualizacaoExperienciaDTO dto = new AtualizacaoExperienciaDTO();
        dto.setPontosAdicionais(pontos);
        dto.setId(Configuration.usuario.getHeroiResponseDTO().getId());

        AtualizacaoExperienciaWrapper wrapper = new AtualizacaoExperienciaWrapper();
        wrapper.setAtualizacaoExperienciaDTO(dto);

        HeroiService usuarioService = new HeroiServiceImpl();
        usuarioService.adicionarExperiencia(this, wrapper, new JsonRequestListener() {
            @Override
            public void onSuccess(Object response) {
                mudarExperienciaLocalmente(pontos);
            }

            @Override
            public void onError(Object response) {

            }

            private void mudarExperienciaLocalmente(int pontos) {
                Configuration.usuario.getHeroiResponseDTO().setXp(Configuration.usuario.getHeroiResponseDTO().getXp() + pontos);
            }
        });
    }

    private enum Adversario {
        HEROI, VILAO;
    }

    private class HpProgressBarAnimation extends Animation {
        private ProgressBar progressBar;
        private float from;
        private float  to;

        public HpProgressBarAnimation(ProgressBar progressBar, float from, float to) {
            super();
            this.progressBar = progressBar;
            this.from = from;
            this.to = to;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            float value = from + (to - from) * interpolatedTime;

            progressBar.setProgress((int) value);


            if (value < 70) {
                Resources res = getResources();
                progressBar.getProgressDrawable().getBounds();

                if (value >= 30) {
                    progressBar.setBackgroundColor(Color.parseColor("#AFAF00"));
                } else if (value < 30) {
                    progressBar.setBackgroundColor(Color.parseColor("#EA0101"));
                }
            }
        }
    }
}
