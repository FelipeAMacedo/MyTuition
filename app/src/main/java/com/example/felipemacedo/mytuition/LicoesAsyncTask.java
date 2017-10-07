package com.example.felipemacedo.mytuition;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.example.felipemacedo.mytuition.database.Database;
import com.example.felipemacedo.mytuition.licoes.LicoesAdapter;
import com.example.felipemacedo.mytuition.model.Licao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipemacedo on 29/09/17.
 */

public class LicoesAsyncTask extends AsyncTask {

    private RecyclerView recyclerView;
    private Context context;
    private LicoesAdapter licoesAdapter;
    private List<Licao> licoes;

    public LicoesAsyncTask(LicoesAdapter licoesAdapter, Context context) {
//        this.recyclerView = recyclerView;
        this.licoesAdapter = licoesAdapter;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        licoes = new ArrayList<>();
//        licoesAdapter = new LicoesAdapter(context, licoes);
//        recyclerView.setAdapter(licoesAdapter);
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        Database db = Room.inMemoryDatabaseBuilder(context,
                Database.class).build();

        System.out.println(db.licaoDao().findAll().size());

//        licoesAdapter.notifyDataSetChanged();

//        if(licoesAdapter != null) {
//            System.out.println("AQUI");
//            licoesAdapter.notifyDataSetChanged();
//            System.out.println(licoes.size());
//        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        System.out.println(licoes.size());
        licoesAdapter.notifyDataSetChanged();
    }
}
