package com.example.felipemacedo.mytuition;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.felipemacedo.mytuition.model.CurrentUser;
import com.example.felipemacedo.mytuition.model.eclipse.Conteudo;
import com.example.felipemacedo.mytuition.model.eclipse.Materia;

import java.util.ArrayList;
import java.util.List;

//import com.example.felipemacedo.mytuition.dao.ConteudoDao;

public class ConteudoActivity extends AppCompatActivity {

    private TextView textoConteudo;
    private Button btnAvancar;
    private Materia materia;
    private Long conteudosCompletados = 0L;
    private int posicao = 0;
    private List<Conteudo> conteudos;

    //    private Conteudo conteudo;

    private Toolbar toolbar;
    private ActionBar ab;


    private CurrentUser currUser = CurrentUser.getInstance();

//    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo);

        initComponents();
        initListeners();
    }

    private void initComponents() {
        materia = (Materia) getIntent().getExtras().getSerializable("materia");
        conteudos = new ArrayList<>(materia.getConteudo());

//        conteudosCompletados = getIntent().getExtras().getLong("conteudosCompletados");

//        conteudo = new Conteudo();
//        conteudos = new ArrayList<>();

        textoConteudo = (TextView) findViewById(R.id.textoConteudo);
        btnAvancar = (Button) findViewById(R.id.btnAvancar);

        toolbar = (Toolbar) findViewById(R.id.conteudoToolbar);
        setSupportActionBar(toolbar);

        ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        textoConteudo.setMovementMethod(new ScrollingMovementMethod());

        populateData(conteudos.get(posicao));

//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        mDatabase.child("conteudos").child(licao.getId().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot dt : dataSnapshot.getChildren()) {
//                    Conteudo c = dt.getValue(Conteudo.class);
//                    conteudos.add(c);
//                }
//
//                conteudo = conteudos.get(posicao);
//
//                populateData(conteudo);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

    private void initListeners() {
        this.btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // lógica para verificar onde o usuário parou (os dasdos serão guardados e recuperados localmente)
//                ++posicao;
//
//                if (conteudosCompletados != conteudos.size() && posicao > conteudosCompletados) {
//                    conteudosCompletados++;
//                    mDatabase.child("licoes").child(licao.getId().toString()).child("usuarios").child(currUser.id).setValue(posicao);
//                }
//
//                if (posicao < conteudos.size()) {
//                    conteudo = conteudos.get(posicao);
//                    populateData(conteudo);
//                }
//
//                if(posicao == conteudos.size() - 1) {
//                    btnAvancar.setText("Finalizar");
//
//                    populateData(conteudo);
//                } else if(posicao == conteudos.size()) {
//                    if (conteudosCompletados != conteudos.size()) {
//                        currUser.xp += licao.getPontos();
//
//                        currUser.level = Level.calculateLevel(currUser.xp);
//
//                        mDatabase.child("usuarios").child(currUser.id).child("level").setValue(currUser.level);
//                        mDatabase.child("usuarios").child(currUser.id).child("xp").setValue(currUser.xp);
//                    }
//
//                    finish();
//                }
            }
        });
    }

    private void populateData(Conteudo conteudo) {

        ab.setTitle(materia.getNome() + " " + (posicao + 1) + "/" + materia.getConteudo().size());

        Spanned spanned;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            spanned = Html.fromHtml(conteudo.getTexto(), Html.FROM_HTML_MODE_LEGACY);
        } else {
            spanned = Html.fromHtml(conteudo.getTexto());
        }

        textoConteudo.setText(spanned);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (posicao == 0) {
                    finish();
                } else {
                    --posicao;

                    if (btnAvancar.getText() != "Avançar") {
                        btnAvancar.setText("Avançar");
                    }

//                    conteudo = conteudos.get(posicao);
//                    populateData(conteudo);


                    populateData(conteudos.get(posicao));

                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
