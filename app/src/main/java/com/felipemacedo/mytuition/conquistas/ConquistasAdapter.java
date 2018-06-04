package com.felipemacedo.mytuition.conquistas;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.felipemacedo.mytuition.R;
import com.felipemacedo.mytuition.model.eclipse.Conquista;
import com.felipemacedo.mytuition.utils.Base64Util;
import com.felipemacedo.mytuition.utils.RecyclerViewOnItemClickListener;

import java.util.List;

public class ConquistasAdapter extends RecyclerView.Adapter<ConquistasAdapter.ConquistasViewHolder> {

    private List<Conquista> conquistas;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnItemClickListener mRecyclerViewOnItemClickListener;
    private Resources res;

    public ConquistasAdapter(Context context, List<Conquista> conquistas) {
        this.conquistas = conquistas;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ConquistasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_conquista, parent, false);
        res = parent.getResources();

        return new ConquistasViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ConquistasViewHolder holder, int position) {
        Conquista conquista = conquistas.get(position);

        holder.imagemConquista.setImageDrawable(Base64Util.base64ToDrawable(res, conquista.getImagem()));

        if (conquista.getUsuarioConquista() != null && !conquista.getUsuarioConquista().isEmpty())
            holder.camada.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return conquistas.size();
    }

    public void setmRecyclerViewOnClickListener(RecyclerViewOnItemClickListener r) {
        mRecyclerViewOnItemClickListener = r;
    }

    public class ConquistasViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        private AppCompatImageView imagemConquista;
        private RelativeLayout camada;

        public ConquistasViewHolder(View itemView) {
            super(itemView);

            imagemConquista = itemView.findViewById(R.id.img_conquista);
            camada = itemView.findViewById(R.id.conquista_item_camada);

            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            if (mRecyclerViewOnItemClickListener != null) {
                mRecyclerViewOnItemClickListener.onItemClickListener(v, getAdapterPosition());
                return true;
            }

            return false;
        }
    }

}
