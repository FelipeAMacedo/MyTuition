package com.example.felipemacedo.mytuition.licoes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.felipemacedo.mytuition.R;
import com.example.felipemacedo.mytuition.model.Conteudo;
import com.example.felipemacedo.mytuition.model.Licao;

import java.util.List;

/**
 * Created by felipemacedo on 19/09/17.
 */

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
        LicoesViewHolder lvh = new LicoesViewHolder(v);

        return lvh;
    }

    @Override
    public void onBindViewHolder(LicoesViewHolder holder, int position) {
        holder.nomeLicao.setText(licoes.get(position).toString());

//        int conteudoCompleto = 0;
//        int size = licoes.get(position).getConteudos().size();
//
//        for(Conteudo c : licoes.get(position).getConteudos()) {
//            if (c.isCompletado()) {
//                conteudoCompleto++;
//            }
//        }

//        holder.qtdLicoes.setText(conteudoCompleto + "/" + size);


//        holder.mProgressBar.setProgress((conteudoCompleto * 100) / size);

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
        private RelativeLayout statusBackground;
        private RelativeLayout statusActual;
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
}
