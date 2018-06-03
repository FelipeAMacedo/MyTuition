package com.example.felipemacedo.mytuition.conquistas;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.felipemacedo.mytuition.R;
import com.example.felipemacedo.mytuition.model.eclipse.Conquista;
import com.example.felipemacedo.mytuition.utils.Base64Util;
import com.example.felipemacedo.mytuition.utils.RecyclerViewOnItemClickListener;

import java.util.Base64;
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
        return conquistas.size();
    }

    public void setmRecyclerViewOnClickListener(RecyclerViewOnItemClickListener r) {
        mRecyclerViewOnItemClickListener = r;
    }

    public class ConquistasViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        private AppCompatImageView imagemConquista;

        public ConquistasViewHolder(View itemView) {
            super(itemView);

            imagemConquista = (AppCompatImageView) itemView.findViewById(R.id.img_conquista);

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
