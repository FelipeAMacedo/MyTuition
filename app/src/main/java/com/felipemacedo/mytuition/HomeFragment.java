package com.felipemacedo.mytuition;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.felipemacedo.mytuition.conf.Configuration;
import com.felipemacedo.mytuition.dto.heroi.AumentarPontosDTO;
import com.felipemacedo.mytuition.dto.save.wrapper.AumentarPontosWrapper;
import com.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.felipemacedo.mytuition.services.HeroiService;
import com.felipemacedo.mytuition.services.impl.HeroiServiceImpl;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private ProgressBar mLevelProgress;
    private TextView mLevel;
    private ImageButton alertButton;
    private TextView tvNomeHeroiHome;
    private TextView tvAtaqueHome;
    private TextView tvDefesaHome;
    private Button btnAumentarAtaque;
    private Button btnAumentarDefesa;
    private Timer atualizarPontosTimer;
    private TimerTask atualizarPontosTask;
    private HeroiService heroiService;
    private static long pontosTimerDelay = 5000L;

    private int pontosDisponiveis;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadProgress();

        calcularPontosDisponiveis();

    }

    /**
     * Inicia os componentes visuais da tela.
     *
     * @param view
     */
    private void initComponents(View view) {
        mLevel = view.findViewById(R.id.txtHomeLevel);
        mLevelProgress = view.findViewById(R.id.levelProgress);
        alertButton = view.findViewById(R.id.alertButton);
        tvNomeHeroiHome = view.findViewById(R.id.tvNomeHeroiHome);
        tvAtaqueHome = view.findViewById(R.id.tvAtaqueHome);
        tvDefesaHome = view.findViewById(R.id.tvDefesaHome);
        btnAumentarAtaque = view.findViewById(R.id.btnAumentarAtaque);
        btnAumentarDefesa = view.findViewById(R.id.btnAumentarDefesa);

        tvNomeHeroiHome.setText(Configuration.usuario.getHeroiResponseDTO().getNome());
        tvAtaqueHome.setText(getResources().getText(R.string.home_hero_attack) + String.valueOf(Configuration.usuario.getHeroiResponseDTO().getAtaque()));
        tvDefesaHome.setText(getResources().getText(R.string.home_hero_defense) + String.valueOf(Configuration.usuario.getHeroiResponseDTO().getDefesa()));

        atualizarPontosTimer = new Timer();

    }

    private void loadProgress() {
        int xp = Configuration.usuario.getHeroiResponseDTO().getXp();
        int level = Level.calculateLevel(xp);

        int neededExp = Level.calculateNeededExp(level + 1);
        int neededExpActualLevel = Level.calculateNeededExp(level);

        mLevel.setText("" + level);

        mLevelProgress.setMax(neededExp - neededExpActualLevel);
        mLevelProgress.setProgress(xp
                - neededExpActualLevel);
    }

    /**
     * Configura os listeners dos componentes da tela.
     */
    private void initListeners() {

        alertButton.setOnClickListener((view) -> startActivity(new Intent(HomeFragment.this.getContext(), LutaActivity.class)));

        btnAumentarAtaque.setOnClickListener((view) -> {
            if (existePontosParaAdicionar()) {
                int ataqueAtual = Configuration.usuario.getHeroiResponseDTO().getAtaque() + 1;
                Configuration.usuario.getHeroiResponseDTO().setAtaque(ataqueAtual);


                tvAtaqueHome.setText(getResources().getString(R.string.home_hero_attack) + ataqueAtual);

                atualizarPontosTimer.cancel();
                atualizarPontosTimer = new Timer();
                atualizarPontosTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(() -> {
                            atualizaPontos();
                        });
                    }
                }, pontosTimerDelay);
            }
        });

        btnAumentarDefesa.setOnClickListener((view) -> {
            if (existePontosParaAdicionar()) {
                int defesaAtual = Configuration.usuario.getHeroiResponseDTO().getDefesa() + 1;
                Configuration.usuario.getHeroiResponseDTO().setDefesa(defesaAtual);

                tvDefesaHome.setText(getResources().getString(R.string.home_hero_defense) + defesaAtual);

                atualizarPontosTimer.cancel();
                atualizarPontosTimer = new Timer();
                atualizarPontosTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(() -> {
                            atualizaPontos();
                        });
                    }
                }, pontosTimerDelay);
            }
        });
    }

    private void atualizaPontos() {
        heroiService = new HeroiServiceImpl();

        heroiService.aumentarPontos(getContext(), montarAumentarPontosWrapper(), new JsonRequestListener() {
            @Override
            public void onSuccess(Object response) { }

            @Override
            public void onError(Object response) { }
        });
    }

    private AumentarPontosWrapper montarAumentarPontosWrapper() {
        AumentarPontosDTO dto = new AumentarPontosDTO();
        dto.setId(Configuration.usuario.getHeroiResponseDTO().getId());
        dto.setAtaque(Configuration.usuario.getHeroiResponseDTO().getAtaque());
        dto.setDefesa(Configuration.usuario.getHeroiResponseDTO().getDefesa());

        AumentarPontosWrapper wrapper = new AumentarPontosWrapper();
        wrapper.setAumentarPontosDTO(dto);
        return wrapper;
    }

    private synchronized boolean existePontosParaAdicionar() {
        int xp = Configuration.usuario.getHeroiResponseDTO().getXp();
        int level = Level.calculateLevel(xp);
        int ataque = Configuration.usuario.getHeroiResponseDTO().getAtaque();
        int defesa = Configuration.usuario.getHeroiResponseDTO().getDefesa();

        if (level <= ataque + defesa - 1) {
            btnAumentarAtaque.setVisibility(View.INVISIBLE);
            btnAumentarDefesa.setVisibility(View.INVISIBLE);

            return false;
        }

        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initComponents(view);
        initListeners();

        calcularPontosDisponiveis();

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

//        if (getView() != null) {
//            if (isVisibleToUser) {
//                calcularPontosDisponiveis();
//            }
//        }
    }

    private void calcularPontosDisponiveis() {
        int xp = Configuration.usuario.getHeroiResponseDTO().getXp();
        int level = Level.calculateLevel(xp);
        int ataque = Configuration.usuario.getHeroiResponseDTO().getAtaque();
        int defesa = Configuration.usuario.getHeroiResponseDTO().getDefesa();

        //            Toast.makeText(getContext(),"ESTÁ VISÍVEL", Toast.LENGTH_SHORT);


        if (level > ataque + defesa - 1) {
            //                Toast.makeText(getContext(),"LEVEL > ", Toast.LENGTH_SHORT);
            pontosDisponiveis = level - ataque + defesa - 1;

            btnAumentarAtaque.setVisibility(View.VISIBLE);
            btnAumentarDefesa.setVisibility(View.VISIBLE);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
