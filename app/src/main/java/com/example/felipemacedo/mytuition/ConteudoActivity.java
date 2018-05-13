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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.felipemacedo.mytuition.conf.Configuration;
import com.example.felipemacedo.mytuition.dto.heroi.AtualizacaoExperienciaDTO;
import com.example.felipemacedo.mytuition.dto.save.wrapper.AtualizacaoExperienciaWrapper;
import com.example.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.example.felipemacedo.mytuition.model.CurrentUser;
import com.example.felipemacedo.mytuition.model.eclipse.Alternativa;
import com.example.felipemacedo.mytuition.model.eclipse.Conteudo;
import com.example.felipemacedo.mytuition.model.eclipse.Materia;
import com.example.felipemacedo.mytuition.services.HeroiService;
import com.example.felipemacedo.mytuition.services.impl.HeroiServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//import com.example.felipemacedo.mytuition.dao.ConteudoDao;

public class ConteudoActivity extends AppCompatActivity {

    private TextView textoConteudo;
    private Button btnAvancar;
    private Materia materia;
    private Long conteudosCompletados = 0L;
    private int posicao = 0;
    private List<Conteudo> conteudos;
    private Conteudo conteudoAtual;
    private RadioGroup alternativasRadioGroup;
    //    private Conteudo conteudo;

    private Toolbar toolbar;
    private ActionBar ab;

    private HeroiService heroiService;

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
        conteudos = (List<Conteudo>) getIntent().getExtras().getSerializable("conteudos");

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

        heroiService = new HeroiServiceImpl();

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
                if (conteudoAtual.getAlternativas() != null && !conteudoAtual.getAlternativas().isEmpty()) {
                    if (verificaResposta() == false) {
                        return;
                    }
                }

                // lógica para verificar onde o usuário parou (os dasdos serão guardados e recuperados localmente)
                ++posicao;
//
//                if (conteudosCompletados != conteudos.size() && posicao > conteudosCompletados) {
//                    conteudosCompletados++;
//                    mDatabase.child("licoes").child(licao.getId().toString()).child("usuarios").child(currUser.id).setValue(posicao);
//                }
//
                if (posicao < conteudos.size()) {
                    populateData(conteudos.get(posicao));
                }

                if(posicao == conteudos.size() - 1) {
                    btnAvancar.setText("Finalizar");
                } else if(posicao == conteudos.size()) {
//                    if (conteudosCompletados != conteudos.size()) {
//                        currUser.xp += licao.getPontos();
//
//                        currUser.level = Level.calculateLevel(currUser.xp);
//
//                        mDatabase.child("usuarios").child(currUser.id).child("level").setValue(currUser.level);
//                        mDatabase.child("usuarios").child(currUser.id).child("xp").setValue(currUser.xp);
//                    }

                    adicionaExperiencia();

                    finish();
                }
            }

            private void adicionaExperiencia() {
                AtualizacaoExperienciaWrapper wrapper = new AtualizacaoExperienciaWrapper();
                AtualizacaoExperienciaDTO dto = new AtualizacaoExperienciaDTO();
                dto.setId(Configuration.usuario.getHeroiResponseDTO().getId());
                dto.setPontosAdicionais(materia.getPontos());

                wrapper.setAtualizacaoExperienciaDTO(dto);

                heroiService.adicionarExperiencia(ConteudoActivity.this, wrapper, new JsonRequestListener() {
                    @Override
                    public void onSuccess(Object response) {
                        Configuration.usuario.getHeroiResponseDTO().setXp(Configuration.usuario.getHeroiResponseDTO().getXp() + materia.getPontos());
                    }

                    @Override
                    public void onError(Object response) {

                    }
                });
            }

            private boolean verificaResposta() {
                String resposta = "";

                for (int x = 0; x < alternativasRadioGroup.getTouchables().size(); x++) {
                    RadioButton radio = (RadioButton) alternativasRadioGroup.getTouchables().get(x);
                    if(radio.isChecked()) {
                        resposta = radio.getText().toString();
                    }
                }

                boolean acertou = false;

                for (Alternativa alternativa : conteudoAtual.getAlternativas()) {
                   if (alternativa.getTexto().equals(resposta)) {
                       acertou = alternativa.getCerto();
                   }
                }

                return acertou;
            }
        });
    }

    private void populateData(Conteudo conteudo) {
        conteudoAtual = conteudo;

        ab.setTitle(materia.getNome() + " " + (posicao + 1) + "/" + conteudos.size());

        Spanned spanned;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            spanned = Html.fromHtml(conteudo.getTexto(), Html.FROM_HTML_MODE_LEGACY);
        } else {
            spanned = Html.fromHtml(conteudo.getTexto());
        }

        textoConteudo.setText(spanned);

        addAlternativas();


    }

    private void addAlternativas() {
        if (conteudoAtual.getAlternativas() != null && !conteudoAtual.getAlternativas().isEmpty()) {
            if (alternativasRadioGroup != null)
                alternativasRadioGroup.removeAllViews();

            addRadioButtons(conteudoAtual.getAlternativas(), conteudoAtual.getAlternativas().size());
        }
    }

    private void addRadioButtons(Set<Alternativa> alternativasSet, int size) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.contentLayout);
        alternativasRadioGroup = new RadioGroup(this);
        alternativasRadioGroup.setOrientation(LinearLayout.VERTICAL);

        List<Alternativa> alternativas = new ArrayList<>();
        alternativas.addAll(alternativasSet);

        for(int x = 0; x < size; x++) {
            RadioButton radio = new RadioButton(this);
            radio.setText(alternativas.get(x).getTexto());
            alternativasRadioGroup.addView(radio);
        }

        layout.addView(alternativasRadioGroup);
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

                    populateData(conteudos.get(posicao));

                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
