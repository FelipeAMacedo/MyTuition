//package com.example.felipemacedo.mytuition.dao;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//import com.example.felipemacedo.mytuition.database.Database;
//import com.example.felipemacedo.mytuition.model.Conteudo;
//import com.example.felipemacedo.mytuition.model.Licao;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by felipemacedo on 25/09/17.
// */
//
//public class ConteudoDao {
//    private Context context;
//    private final String table = "tb_conteudo";
//
//    public ConteudoDao(Context context) {
//        this.context = context;
//    }
//
//
//    public List<Conteudo> findAllByLicaoId(long licaoId) {
//        List<Conteudo> conteudos = new ArrayList<>();
//        SQLiteDatabase sqlDB = new Database(context).getReadableDatabase();
//        String sql = "SELECT * FROM " + table + " WHERE licao_id = " + licaoId;
//
//        Cursor cursor = sqlDB.rawQuery(sql, null);
//
//        Conteudo conteudo = new Conteudo();
//
//        while(cursor.moveToNext()) {
//            conteudo.setId(cursor.getLong(cursor.getColumnIndex("id")));
//            conteudo.setCompletado(cursor.getInt(cursor.getColumnIndex("completado")) == 1);
//            conteudo.setTexto(cursor.getString(cursor.getColumnIndex("texto")));
////            conteudo.setTipo(cursor.getExtras(cursor.getColumnIndex("tipo")));
//
//            conteudos.add(conteudo);
//        }
//
//        cursor.close();
//
//        return conteudos;
//    }
//
//    public void insert(Conteudo conteudo) {
//        ContentValues cv = new ContentValues();
//        cv.put("texto", conteudo.getTexto());
//        cv.put("tipo", conteudo.getTipo().getValor());
//        cv.put("completado", conteudo.isCompletado() ? 1 : 0);
//
//        SQLiteDatabase sqlDB = new Database(context).getWritableDatabase();
//        sqlDB.insert(table, null, cv);
//    }
//}
