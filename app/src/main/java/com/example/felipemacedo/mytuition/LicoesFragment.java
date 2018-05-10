package com.example.felipemacedo.mytuition;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.felipemacedo.mytuition.dto.materia.MateriaResultDTO;
import com.example.felipemacedo.mytuition.dto.wrapper.response.MateriaResponseWrapper;
import com.example.felipemacedo.mytuition.licoes.MateriasAdapter;
import com.example.felipemacedo.mytuition.licoes.RecyclerViewOnItemClickListener;
import com.example.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.example.felipemacedo.mytuition.model.eclipse.Materia;
import com.example.felipemacedo.mytuition.services.MateriaService;
import com.example.felipemacedo.mytuition.services.impl.MateriaServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import com.example.felipemacedo.mytuition.dao.LicaoDao;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LicoesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LicoesFragment extends Fragment implements RecyclerViewOnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "MATERIAS";


    // Variável temporária
    private static final Long disciplinaId = 1L;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView mRecyclerView;
    private List<Materia> materias;
    private MateriasAdapter materiasAdapter;

    private MateriaService service;


    private OnFragmentInteractionListener mListener;

    public LicoesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LicoesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LicoesFragment newInstance(String param1, String param2) {
        LicoesFragment fragment = new LicoesFragment();
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

        Toast.makeText(getContext(), "ENTROU NO CREATE", Toast.LENGTH_LONG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_licoes, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_licoes);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);


        service = new MateriaServiceImpl();

        materias = new ArrayList<>();

        service.findByDisciplinaId(this.getContext(), disciplinaId, new JsonRequestListener<JSONObject>() {
            @Override
            public void onSuccess(JSONObject response) {
                Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .create();

                MateriaResponseWrapper wrapper = gson.fromJson(String.valueOf(response), MateriaResponseWrapper.class);

                materias = convertDTOToEntity(wrapper.getMaterias());

                materiasAdapter = new MateriasAdapter(getActivity(), materias);
                materiasAdapter.setmRecyclerViewOnClickListener(LicoesFragment.this);

                mRecyclerView.setAdapter(materiasAdapter);

                materiasAdapter.notifyDataSetChanged();

            }

            private List<Materia> convertDTOToEntity(Set<MateriaResultDTO> materias) {
                List<Materia> convertedMaterias = new ArrayList<>();
                ModelMapper mapper = new ModelMapper();

                for (MateriaResultDTO m : materias) {
                    convertedMaterias.add(mapper.map(m, Materia.class));
                }

                return convertedMaterias;
            }

            @Override
            public void onError(JSONObject response) {
                Toast.makeText(LicoesFragment.this.getContext(), "Não foi possível carregar as matérias", Toast.LENGTH_LONG);
                Log.e(TAG, response.toString());
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
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

    @Override
    public void onItemClickListener(View view, int position) {


        Intent intent = new Intent(getActivity(), ConteudoActivity.class);

        intent.putExtra("materia", materias.get(position));

        // Este trecho de código estava passando os conteúdos da matéria selecionada para a próxima activity
//        intent.putExtra("licao", licoes.get(position));
//
//
//        Long conteudosCompletados = 0L;
//
//
//        if (licoes.get(position).getUsuarios().size() > 0) {
//            conteudosCompletados = (Long) licoes.get(position).getUsuarios().values().toArray()[0];
//        }
//
//        intent.putExtra("conteudos_completados", conteudosCompletados);
//
//        Bundle bundle = new Bundle();
//        bundle.putString("licao", licao.getTitulo());
//        bundle.putLong("timestamp_inicio", new Date().getTime());
//
//        FirebaseAnalytics fa = FirebaseAnalytics.getInstance(getContext());
//        fa.setUserId(CurrentUser.getInstance().id);
//        fa.logEvent("inicio_licao", bundle);

        startActivity(intent);
    }
}
