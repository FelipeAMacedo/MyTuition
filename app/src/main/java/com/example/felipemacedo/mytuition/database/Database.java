package com.example.felipemacedo.mytuition.database;

import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.felipemacedo.mytuition.dao.ConteudDao;
import com.example.felipemacedo.mytuition.dao.LicaDao;
import com.example.felipemacedo.mytuition.model.Conteudo;
import com.example.felipemacedo.mytuition.model.Licao;

/**
 * Created by felipemacedo on 24/09/17.
 */
@android.arch.persistence.room.Database(entities = {Conteudo.class, Licao.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract ConteudDao conteudoDao();
    public abstract LicaDao licaoDao();
}

//public class Database extends SQLiteOpenHelper {
//    private String tbLicao = "tb_licao";
//    private String tbConteudo = "tb_conteudo";
//
//    public Database(Context context) {
//        super(context, "MyTuitionDatabase", null, 11);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String sql1 = "CREATE TABLE " + tbLicao + " (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT NOT NULL)";
//        String sql2 = "CREATE TABLE " + tbConteudo + " (id INTEGER PRIMARY KEY AUTOINCREMENT, texto TEXT NOT NULL," +
//                " completado INTEGER NOT NULL, tipo INTEGER NOT NULL," +
//                " licao_id INTEGER, FOREIGN KEY (licao_id) REFERENCES " + tbLicao + " (id))";
//        sqLiteDatabase.execSQL(sql1);
//        sqLiteDatabase.execSQL(sql2);
//
//
//        String verifica1 = "SELECT * FROM " + tbLicao;
//        Cursor c = sqLiteDatabase.rawQuery(verifica1, null);
//
//        if(c.getCount() == 0) {
////            String dataToInsert1 = "INSERT INTO " + tbLicao + " (titulo) VALUES ('Pilha')";
////            String dataToInsert2 = "INSERT INTO " + tbLicao + " (titulo) VALUES ('Fila')";
////
////            sqLiteDatabase.execSQL(dataToInsert1);
////
////            sqLiteDatabase.execSQL(dataToInsert2);
////
////            String conteudoToInsert1 = "INSERT INTO " + tbConteudo + " (texto, completado, tipo, licao_id) " +
////                    "VALUES " +
////                    "('É uma lista linear em que todas as inserções e remoções são " +
////                    "feitas numa mesma extremidade da lista linear. Esta " +
////                    "extremidade se denomina topo (em inglês “top”) ou lado aberto " +
////                    "da pilha.', 0, 1, 1)";
////
////            String conteudoToInsert2 = "INSERT INTO " + tbConteudo + " (texto, completado, tipo, licao_id) " +
////                    "VALUES " +
////                    "('Como o último elemento que entrou na pilha será o primeiro a " +
////                    "sair da pilha, a pilha é conhecida como uma estrutura do tipo " +
////                    "LIFO (“Last In First Out”)', 0, 1, 1)";
////
////            sqLiteDatabase.execSQL(conteudoToInsert1);
////            sqLiteDatabase.execSQL(conteudoToInsert2);
//        }
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        String sqlDrop1 = "DROP TABLE " + tbLicao;
//        String sqlDrop2 = "DROP TABLE " + tbConteudo;
//        sqLiteDatabase.execSQL(sqlDrop1);
//        sqLiteDatabase.execSQL(sqlDrop2);
//        onCreate(sqLiteDatabase);
//    }
//}
