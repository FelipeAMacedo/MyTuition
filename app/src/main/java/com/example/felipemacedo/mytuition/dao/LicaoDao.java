//package com.example.felipemacedo.mytuition.dao;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//import com.example.felipemacedo.mytuition.database.Database;
//import com.example.felipemacedo.mytuition.mock.MockLicao;
//import com.example.felipemacedo.mytuition.model.Conteudo;
//import com.example.felipemacedo.mytuition.model.Licao;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by felipemacedo on 22/09/17.
// */
//
//public class LicaoDao {
//    private Context context;
//    private final String table = "tb_licao";
//
//    public LicaoDao(Context context) {
//        this.context = context;
//    }
//
//    public List<Licao> findAll() {
//        List<Licao> licoes = new ArrayList<>();
//        SQLiteDatabase sqlDB = new Database(context).getReadableDatabase();
//        String sql = "SELECT * FROM " + table;
//
//        Cursor cursor = sqlDB.rawQuery(sql, null);
//
//        Licao licao = new Licao();
//
//        while(cursor.moveToNext()) {
//            licao.setId(cursor.getLong(cursor.getColumnIndex("id")));
//            licao.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
//
//            licao.setConteudos(new ArrayList<Conteudo>());
//
//            licoes.add(licao);
//        }
//
//        cursor.close();
//
//        return licoes;
//    }
//
//    public void insert(Licao licao) {
//        ContentValues cv = new ContentValues();
//        cv.put("titulo", licao.getTitulo());
//
//        SQLiteDatabase sqlDB = new Database(context).getWritableDatabase();
//        sqlDB.insert(table, null, cv);
//
//        sqlDB.close();
//    }
//}
