package com.example.felipemacedo.mytuition.licoes;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.felipemacedo.mytuition.R;
import com.example.felipemacedo.mytuition.model.Licao;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class LicoesAdapter extends RecyclerView.Adapter<LicoesAdapter.LicoesViewHolder> {

    private List<Licao> licoes;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnItemClickListener mRecyclerViewOnItemClickListener;

    public LicoesAdapter(Context context, List<Licao> licoes) {
        this.licoes = licoes;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public LicoesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_licao, parent, false);
        return new LicoesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LicoesViewHolder holder, int position) {
        Licao licao = licoes.get(position);

        holder.nomeLicao.setText(licao.toString());

        int completado = 0;

        if (licao.getUsuarios() != null && licao.getUsuarios().containsKey(getUid())) {
            completado = licao.getUsuarios().get(getUid()).intValue();
        }

        holder.qtdLicoes.setText(completado + "/" + licao.getConteudoCount());

        holder.mProgressBar.setProgress(completado > 0 ? (completado * 100) / licao.getConteudoCount() : 0);
    }

    @Override
    public int getItemCount() {
        return licoes.size();
    }

    public void setmRecyclerViewOnClickListener(RecyclerViewOnItemClickListener r) {
        mRecyclerViewOnItemClickListener = r;
    }

    public class LicoesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nomeLicao;
        private TextView qtdLicoes;
        private RelativeLayout status;
        private ProgressBar mProgressBar;

        public LicoesViewHolder(View itemView) {
            super(itemView);

            nomeLicao = (TextView) itemView.findViewById(R.id.txtNomeLicao);
            qtdLicoes = (TextView) itemView.findViewById(R.id.txtQtdLicoes);
            status = (RelativeLayout) itemView.findViewById(R.id.licao_status);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progresso_da_licao);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mRecyclerViewOnItemClickListener != null) {
                mRecyclerViewOnItemClickListener.onItemClickListener(view, getAdapterPosition());
            }

        }
    }

    private String getUid() {
        return FirebaseAuth.getInstance().getUid();
    }
}
