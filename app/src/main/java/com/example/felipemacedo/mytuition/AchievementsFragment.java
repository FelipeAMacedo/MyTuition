package com.example.felipemacedo.mytuition;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.felipemacedo.mytuition.conf.Configuration;
import com.example.felipemacedo.mytuition.model.CurrentUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class AchievementsFragment extends Fragment {

    private TextView mNomeHeroi;
    private TextView mNivel;
    private ProgressBar mProgress;
    private TextView mPontosFaltam;
    private CurrentUser currUser = CurrentUser.getInstance();



    public AchievementsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_achievements, container, false);

        initComponents(view);
        initListeners();

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        loadProgress();
    }

    private void initComponents(View view) {
        mNomeHeroi = (TextView) view.findViewById(R.id.tvAchHeroiNome);
        mNivel = (TextView) view.findViewById(R.id.tvAchNivel);
        mProgress = (ProgressBar) view.findViewById(R.id.pbAchNivel);
        mPontosFaltam = (TextView) view.findViewById(R.id.tvAchPontos);

        mNomeHeroi.setText(currUser.nomeHeroi);
    }

    private void initListeners() {
    }

    private void loadProgress() {
        int xp = Configuration.usuario.getHeroiResponseDTO().getXp();
        int level = Level.calculateLevel(xp);

        int neededExp = Level.calculateNeededExp(level + 1);
        int neededExpActualLevel = Level.calculateNeededExp(level);

        mNivel.setText("Nível " + level);

        mProgress.setMax(neededExp - neededExpActualLevel);
        mProgress.setProgress(xp
                - neededExpActualLevel);

        mPontosFaltam.setText("+" + (neededExp - xp) + " pontos para o nível " + (level + 1));
    }

}
