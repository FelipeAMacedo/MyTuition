package com.example.felipemacedo.mytuition.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.felipemacedo.mytuition.model.Licao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipemacedo on 27/09/17.
 */
@Dao
public interface LicaDao {

    @Query("SELECT * FROM tb_licoes")
    List<Licao> findAll();

    @Insert()
    Long insert(Licao licao);
}
