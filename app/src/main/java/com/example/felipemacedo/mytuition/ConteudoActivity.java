package com.example.felipemacedo.mytuition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.felipemacedo.mytuition.dao.ConteudoDao;
import com.example.felipemacedo.mytuition.model.Conteudo;
import com.example.felipemacedo.mytuition.model.CurrentUser;
import com.example.felipemacedo.mytuition.model.Licao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConteudoActivity extends AppCompatActivity {

    private TextView textoConteudo;
    private Button btnAvancar;
    private String licaoId;
    private Long conteudosCompletados = 0L;
    private int posicao = 0;
    private DatabaseReference mDatabase;
    private List<Conteudo> conteudos;
    private Conteudo conteudo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo);

        initComponents();
        initListeners();
    }

    private void initComponents() {
        licaoId = getIntent().getExtras().getString("LicaoId");
        conteudosCompletados = getIntent().getExtras().getLong("conteudosCompletados");

        conteudo = new Conteudo();
        conteudos = new ArrayList<>();

        textoConteudo = (TextView) findViewById(R.id.textoConteudo);
        btnAvancar = (Button) findViewById(R.id.btnAvancar);



        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("conteudos").child(licaoId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dt : dataSnapshot.getChildren()) {
                    Conteudo c = dt.getValue(Conteudo.class);
                    conteudos.add(c);
                }

                conteudo = conteudos.get(posicao);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    textoConteudo.setText(Html.fromHtml(conteudo.getTexto(), Html.FROM_HTML_MODE_LEGACY).toString());
                } else {
                    textoConteudo.setText(Html.fromHtml(conteudo.getTexto()).toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initListeners() {
        this.btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentUser currUser = CurrentUser.getInstance();

                if (conteudosCompletados != conteudos.size()) {
                    mDatabase.child("licoes").child(licaoId).child("usuarios").child(currUser.id).setValue(posicao + 1);
                }

                ++posicao;

                if(posicao == conteudos.size() - 1) {
                    btnAvancar.setText("Finalizar");

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        textoConteudo.setText(Html.fromHtml(conteudos.get(posicao).getTexto(), Html.FROM_HTML_MODE_LEGACY).toString());
                    } else {
                        textoConteudo.setText(Html.fromHtml(conteudos.get(posicao).getTexto()).toString());
                    }
                } else if(posicao == conteudos.size()) {
                    if (conteudosCompletados != conteudos.size()) {
                        currUser.xp += 50;

                        currUser.level = Level.calculateLevel(currUser.xp);

                        mDatabase.child("usuarios").child(currUser.id).child("level").setValue(currUser.level);
                        mDatabase.child("usuarios").child(currUser.id).child("xp").setValue(currUser.xp);
                    }

                    finish();
                }
            }
        });
    }
}
