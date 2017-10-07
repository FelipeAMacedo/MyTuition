package com.example.felipemacedo.mytuition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.example.felipemacedo.mytuition.dao.ConteudoDao;
import com.example.felipemacedo.mytuition.model.Conteudo;
import com.example.felipemacedo.mytuition.model.Licao;

import java.util.List;

public class ConteudoActivity extends AppCompatActivity {

    private TextView textoConteudo;
    private Button btnAvancar;
    private long licaoId;
    private int posicao = 0;
    private List<Conteudo> conteudos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo);

        initComponents();
        initListeners();
    }

    private void initComponents() {
        licaoId = getIntent().getExtras().getLong("LicaoId");
//        conteudos = new ConteudoDao(this).findAllByLicaoId(licaoId);

        textoConteudo = (TextView) findViewById(R.id.textoConteudo);
        this.textoConteudo.setText(conteudos.get(posicao).getTexto());

        btnAvancar = (Button) findViewById(R.id.btnAvancar);
    }

    private void initListeners() {
        this.btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conteudos.get(posicao).setCompletado(true);

                ++posicao;

                if(posicao == conteudos.size() - 1) {
                    btnAvancar.setText("Finalizar");
                    textoConteudo.setText(conteudos.get(posicao).getTexto());
                } else if(posicao == conteudos.size()) {
                    finish();
                }
            }
        });
    }
}
