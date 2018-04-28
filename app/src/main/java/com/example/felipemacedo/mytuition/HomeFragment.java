package com.example.felipemacedo.mytuition;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.felipemacedo.mytuition.conf.Configuration;
import com.example.felipemacedo.mytuition.model.CurrentUser;
import com.example.felipemacedo.mytuition.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


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
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private Usuario usuario;

    private Button button3;
    private EditText editTextHome;

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

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
        mAuth = FirebaseAuth.getInstance();

        System.out.println(" --------------------- CHAVES DO FIREBASE -------------------");
        for (int x = 0; x < 5; x++) {
            System.out.println(x + ": ");
            System.out.println(mDatabase.push());
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        loadProgress();
    }

    private void loadData() {
        RequestQueue queue = Volley.newRequestQueue(this.getContext());

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, Configuration.API_URL, null, response -> {
            String nome = "";
            try {
                nome = response.getString("nome");
            } catch (Exception e) {

            }
            editTextHome.setText(nome);
        }, error -> {
            System.out.println(error);
        });


        StringRequest postRequest = new StringRequest(Request.Method.POST, Configuration.API_URL, response -> {
            System.out.println("Foi tentado o insert");
            queue.stop();
        }, error -> {
            System.out.println(error);
            queue.stop();
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        postRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(stringRequest);
        queue.add(postRequest);

        mDatabase.child("usuarios").child(mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);


                CurrentUser currUser = CurrentUser.getInstance();
                currUser.id = mAuth.getUid();
                currUser.email = usuario.email;
                currUser.nomeHeroi = usuario.nomeHeroi;
                currUser.level = usuario.level;
                currUser.xp = usuario.xp;

                loadProgress();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initComponents(View view) {
        mLevel = (TextView) view.findViewById(R.id.txtHomeLevel);
        mLevelProgress = (ProgressBar) view.findViewById(R.id.levelProgress);

        button3 = (Button) view.findViewById(R.id.button3);
        editTextHome = (EditText) view.findViewById(R.id.editTextHome);

        loadData();
    }

    private void loadProgress() {
        int level = CurrentUser.getInstance().level;
        int xp = CurrentUser.getInstance().xp;


        int neededExp = Level.calculateNeededExp(level + 1);
        int neededExpActualLevel = Level.calculateNeededExp(level);

        mLevel.setText("" + level);

        mLevelProgress.setMax(neededExp - neededExpActualLevel);
        mLevelProgress.setProgress(xp
                - neededExpActualLevel);
    }

    private void initListeners() {
        button3.setOnClickListener((view) -> {

//            new PostServiceTask().execute();
        });
    }

    private class PostServiceTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder result = new StringBuilder();

            try {
                URL url = new URL("http://10.0.2.2:8080/");

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            editTextHome.setText(result);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initComponents(view);
        initListeners();

        return view;
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
