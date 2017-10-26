package com.example.felipemacedo.mytuition;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.felipemacedo.mytuition.model.CurrentUser;
import com.example.felipemacedo.mytuition.model.Licao;
import com.example.felipemacedo.mytuition.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragment(new HomeFragment());
                    return true;
                case R.id.navigation_train:
                    switchFragment(new LicoesFragment());
                    return true;
                case R.id.navigation_notifications:
                    Toast.makeText(MainActivity.this, "Notification taped", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
        } else {
        }


        navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


//        mDatabase = FirebaseDatabase.getInstance().getReference();
//
//        Materia materia = new Materia();
//        materia.setTitulo("Estrutura de Dados");
//        mDatabase.child("materias").child(mDatabase.push().getKey()).setValue(materia);
//
//        Licao licao = new Licao();
//        licao.setTitulo("Pilha");
//
//        mDatabase.child("licoes").child(mDatabase.push().getKey()).setValue(licao);
//
//        Toast.makeText(this, "Inserido", Toast.LENGTH_LONG).show();
//
//        Licao licao1 = new Licao();
//        licao1.setTitulo("Fila");
//        mDatabase.child("licoes").child(mDatabase.push().getKey()).setValue(licao1);
//
//
//        Toast.makeText(this, "Inserido", Toast.LENGTH_LONG).show();








//        insertDataOnTables();


//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... voids) {
//                System.out.println("Tentando criar o banco");
//                Database db = Room.databaseBuilder(getApplicationContext(),
//                        Database.class, "myTuition").build();
//
//                LicaDao licaoDao = db.licaoDao();
//                Licao licao = new Licao();
//                licao.setTitulo("TESTE COM O ROOM");
//                licao.setConteudos(new ArrayList<Conteudo>());
//                System.out.println(licaoDao.insert(licao));
//                System.out.println("Banco criado");
//                return null;
//            }
//        }.execute();

//        Database db = Room.databaseBuilder(getApplicationContext(),
//                Database.class, "myTuition").build();
//
//        LicaDao licaoDao = db.licaoDao();
//        Licao licao = new Licao();
//        licao.setTitulo("TESTE COM O ROOM");
//        licao.setConteudos(new ArrayList<Conteudo>());
//        licaoDao.insert(licao);


        switchFragment(new HomeFragment());
    }

//    private void insertDataOnTables() {
//        ConteudoDao conteudoDao = new ConteudoDao(this);
//
//        Licao licao1 = new Licao();
//        licao1.setTitulo("Pilha");
//
//        Licao licao2 = new Licao();
//        licao2.setTitulo("Fila");
//
//        new LicaoDao(this.getBaseContext()).insert(licao1);
//        new LicaoDao(this.getBaseContext()).insert(licao2);
//
//    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void switchFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frame_home, fragment, fragment.getTag()).commit();
    }
}
