package com.felipemacedo.mytuition;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.felipemacedo.mytuition.conf.Configuration;
import com.felipemacedo.mytuition.conquistas.ConquistasAdapter;
import com.felipemacedo.mytuition.dto.conquista.ConquistaResultDTO;
import com.felipemacedo.mytuition.dto.wrapper.response.ConquistaResponseWrapper;
import com.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.felipemacedo.mytuition.model.eclipse.Conquista;
import com.felipemacedo.mytuition.services.ConquistaService;
import com.felipemacedo.mytuition.services.impl.ConquistaServiceImpl;
import com.felipemacedo.mytuition.utils.Base64Util;
import com.felipemacedo.mytuition.utils.LocalDateDeserializer;
import com.felipemacedo.mytuition.utils.LocalDateTimeDeserializer;
import com.felipemacedo.mytuition.utils.RecyclerViewOnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Fragment responsável por mostrar as informações da tela de conquista.
 *
 * @author Felipe Alves de Macedo
 */
public class AchievementsFragment extends Fragment implements RecyclerViewOnItemClickListener {

    private static final String TAG = "CONQUISTAS";

    private TextView mNomeHeroi;
    private TextView mNivel;
    private ProgressBar mProgress;
    private TextView mPontosFaltam;


    private RecyclerView mRecyclerView;
    private List<Conquista> conquistas;
    private ConquistasAdapter conquistasAdapter;

    private ConquistaService service;

    public AchievementsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_achievements, container, false);

        initComponents(view);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        loadProgress();
    }

    /**
     * Inicia os componentes visuais da tela.
     *
     * @param view
     */
    private void initComponents(View view) {
        mNomeHeroi = view.findViewById(R.id.tvAchHeroiNome);
        mNivel = view.findViewById(R.id.tvAchNivel);
        mProgress = view.findViewById(R.id.pbAchNivel);
        mPontosFaltam = view.findViewById(R.id.tvAchPontos);

        mNomeHeroi.setText(Configuration.usuario.getHeroiResponseDTO().getNome());

        mRecyclerView = view.findViewById(R.id.rv_conquista);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        service = new ConquistaServiceImpl();

        conquistas = new ArrayList<>();

        service.buscarTodas(this.getContext(), Configuration.usuario.getEmail(), new JsonRequestListener<JSONObject>() {
            @Override
            public void onSuccess(JSONObject response) {
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                        .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                        .setPrettyPrinting()
                        .create();

                ConquistaResponseWrapper wrapper = gson.fromJson(String.valueOf(response), ConquistaResponseWrapper.class);

                conquistas = convertConquistaDTOToEntity(wrapper.getConquistas());

                conquistasAdapter = new ConquistasAdapter(getActivity(), conquistas);
                conquistasAdapter.setmRecyclerViewOnClickListener(AchievementsFragment.this);

                mRecyclerView.setAdapter(conquistasAdapter);

                conquistasAdapter.notifyDataSetChanged();
            }

            private List<Conquista> convertConquistaDTOToEntity(Set<ConquistaResultDTO> conquistas) {
                List<Conquista> convertedConquistas = new ArrayList<>();
                ModelMapper mapper = new ModelMapper();

                for (ConquistaResultDTO c : conquistas) {
                    convertedConquistas.add(mapper.map(c, Conquista.class));
                }

                return convertedConquistas;
            }

            @Override
            public void onError(JSONObject response) {
                Toast.makeText(AchievementsFragment.this.getContext(), "Não foi possível carregar as conquistas", Toast.LENGTH_LONG);
                Log.e(TAG, response.toString());
            }
        });

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

    @Override
    public void onItemClickListener(View view, int position) {
        AppCompatDialog dialog = new AppCompatDialog(getContext());
        dialog.setContentView(R.layout.dialog_descricao_conquista);

        TextView tvTitulo = (TextView) dialog.findViewById(R.id.tvTituloConquista);
        TextView tvDescricao = (TextView) dialog.findViewById(R.id.tvDescricaoConquista);
        AppCompatImageView imgConquista = (AppCompatImageView) dialog.findViewById(R.id.img_dialog_conquista);

        Conquista conquista = conquistas.get(position);

        tvTitulo.setText(conquista.getNome());
        tvDescricao.setText(conquista.getDescricao());
        imgConquista.setImageDrawable(Base64Util.base64ToDrawable(getResources(), conquista.getImagem()));

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.show();
    }
}
