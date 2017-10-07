package com.example.felipemacedo.mytuition.dao;

//import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.felipemacedo.mytuition.model.Conteudo;

import java.util.List;

/**
 * Created by felipemacedo on 26/09/17.
 */
@Dao
public interface ConteudDao {
//    @Query("SELECT * FROM tb_conteudos WHERE :licaoId = licao_id")
//    LiveData<List<Conteudo>> findAllByLicaoId(long licaoId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Conteudo conteudo);
}
