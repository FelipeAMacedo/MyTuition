package com.felipemacedo.mytuition.licoes;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.felipemacedo.mytuition.R;
import com.felipemacedo.mytuition.model.eclipse.Materia;
import com.felipemacedo.mytuition.utils.RecyclerViewOnItemClickListener;

import java.util.List;

public class MateriasAdapter extends RecyclerView.Adapter<MateriasAdapter.MateriasViewHolder> {

    private List<Materia> materias;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnItemClickListener mRecyclerViewOnItemClickListener;

    public MateriasAdapter(Context context, List<Materia> materias) {
        this.materias = materias;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MateriasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_disciplina, parent, false);
        return new MateriasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MateriasViewHolder holder, int position) {
        Materia materia = materias.get(position);

        holder.nomeMateria.setText(materia.getNome());

        if(materia.getUsuarioMateria() == null) {
            holder.status.setBackgroundColor(Color.parseColor("#BBBBBB"));
        } else if(materia.getUsuarioMateria().iterator().next().getConclusao() == null) {
            holder.status.setBackgroundColor(Color.parseColor("#F5B64E"));
        } else {
            holder.status.setBackgroundColor(Color.parseColor("#13CE66"));
        }

//        int completado = 0;

        // calcula o quanto já foi estudado
//        if (licao.getUsuarios() != null && licao.getUsuarios().containsKey(getUid())) {
//            completado = licao.getUsuarios().get(getUid()).intValue();
//        }

//        holder.qtdLicoes.setText(completado + "/" + licao.getConteudoCount());

//        holder.mProgressBar.setProgress(completado > 0 ? (completado * 100) / licao.getConteudoCount() : 0);
    }

    @Override
    public int getItemCount() {
        return materias.size();
    }

    public void setmRecyclerViewOnClickListener(RecyclerViewOnItemClickListener r) {
        mRecyclerViewOnItemClickListener = r;
    }

    public class MateriasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nomeMateria;
        private TextView qtdConteudo;
        private RelativeLayout status;
        private ProgressBar mProgressBar;

        public MateriasViewHolder(View itemView) {
            super(itemView);

            nomeMateria = itemView.findViewById(R.id.txtNomeLicao);
            qtdConteudo = itemView.findViewById(R.id.txtQtdLicoes);
            status = itemView.findViewById(R.id.licao_status);
            mProgressBar = itemView.findViewById(R.id.progresso_da_licao);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mRecyclerViewOnItemClickListener != null) {
                mRecyclerViewOnItemClickListener.onItemClickListener(view, getAdapterPosition());
            }

        }
    }

}