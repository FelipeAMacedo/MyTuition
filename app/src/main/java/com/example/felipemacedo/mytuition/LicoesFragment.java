package com.example.felipemacedo.mytuition;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

//import com.example.felipemacedo.mytuition.dao.LicaoDao;
import com.example.felipemacedo.mytuition.database.Database;
import com.example.felipemacedo.mytuition.licoes.LicoesAdapter;
import com.example.felipemacedo.mytuition.licoes.RecyclerViewOnItemClickListener;
import com.example.felipemacedo.mytuition.model.Licao;

import java.util.ArrayList;
import java.util.List;


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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private RecyclerView mRecyclerView;
    private List<Licao> licoes;
    private LicoesAdapter licoesAdapter;
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

        licoes = new ArrayList<>();



//        new AsyncTask<Void, Void, Void>() {
//
//
//
//            @Override
//            protected List<Licao> doInBackground(Void... voids) {
//                Database db = Room.inMemoryDatabaseBuilder(getActivity().getApplicationContext(),
//                        Database.class).build();
//                List<Licao> licoes = db.licaoDao().findAll();
//
//
//
//                if(licoesAdapter != null) {
//                    System.out.println("AQUI");
//                    licoesAdapter.notifyDataSetChanged();
//                    System.out.println(licoes.size());
//                }
//
//                return licoes;
//            }
//        }.execute();
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


//        Database db = Room.inMemoryDatabaseBuilder(getActivity().getApplicationContext(),
//                Database.class).build();
//        licoes = db.licaoDao().findAll();
//        licoes = new LicaoDao(getContext()).findAll();
        licoes = new ArrayList<>();
        licoesAdapter = new LicoesAdapter(getActivity(), licoes);
        licoesAdapter.setmRecyclerViewOnClickListener(this);

        mRecyclerView.setAdapter(licoesAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new LicoesAsyncTask(licoesAdapter, this.getContext()).execute();

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
    public void onItemClickListener (View view, int position) {
        Toast.makeText(getActivity(), "ID: " + licoes.get(position).getId(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), ConteudoActivity.class);
        intent.putExtra("LicaoId", licoes.get(position).getId());
        startActivity(intent);
    }
}
