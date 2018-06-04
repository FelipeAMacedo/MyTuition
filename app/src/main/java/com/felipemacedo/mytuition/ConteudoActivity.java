package com.example.felipemacedo.mytuition;

import android.content.Intent;
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
import com.felipemacedo.mytuition.dto.heroi.AtualizacaoExperienciaDTO;
import com.felipemacedo.mytuition.dto.save.wrapper.AtualizacaoExperienciaWrapper;
import com.example.felipemacedo.mytuition.dto.save.wrapper.UsuarioMateriaSaveWrapper;
import com.example.felipemacedo.mytuition.dto.usuarioMateria.UsuarioMateriaDTO;
import com.example.felipemacedo.mytuition.dto.usuarioMateria.UsuarioMateriaMateriaDTO;
import com.example.felipemacedo.mytuition.dto.usuarioMateria.UsuarioMateriaUsuarioDTO;
import com.example.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.example.felipemacedo.mytuition.model.CurrentUser;
import com.example.felipemacedo.mytuition.model.eclipse.Alternativa;
import com.example.felipemacedo.mytuition.model.eclipse.Conteudo;
import com.example.felipemacedo.mytuition.model.eclipse.Materia;
import com.example.felipemacedo.mytuition.model.eclipse.Usuario;
import com.example.felipemacedo.mytuition.model.eclipse.UsuarioMateria;
import com.example.felipemacedo.mytuition.services.HeroiService;
import com.example.felipemacedo.mytuition.services.UsuarioMateriaService;
import com.example.felipemacedo.mytuition.services.impl.HeroiServiceImpl;
import com.example.felipemacedo.mytuition.services.impl.UsuarioMateriaServiceImpl;

import java.time.LocalDateTime;
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
    private Set<UsuarioMateria> usuarioMateria;
    private RadioGroup alternativasRadioGroup;
    private boolean aumentarExperiencia = false;
    //    private Conteudo conteudo;

    private Toolbar toolbar;
    private ActionBar ab;

    private HeroiService heroiService;

    private boolean somenteQuestao;

    private CurrentUser currUser = CurrentUser.getInstance();

//    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo);

        if(savedInstanceState != null) {
            posicao = savedInstanceState.getInt("posicao");
        }

        initComponents();
        initListeners();
    }

    /**
     * Inicia os componentes visuais da tela.
     */
    private void initComponents() {
        textoConteudo = (TextView) findViewById(R.id.textoConteudo);
        btnAvancar = (Button) findViewById(R.id.btnAvancar);

        toolbar = (Toolbar) findViewById(R.id.conteudoToolbar);
        setSupportActionBar(toolbar);

        ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        textoConteudo.setMovementMethod(new ScrollingMovementMethod());

        somenteQuestao = getIntent().getExtras().getBoolean("somenteQuestao");

        if (somenteQuestao) {
            conteudoAtual = (Conteudo) getIntent().getExtras().getSerializable("conteudo");
            populateData(conteudoAtual);
        } else {
            materia = (Materia) getIntent().getExtras().getSerializable("materia");
            conteudos = (List<Conteudo>) getIntent().getExtras().getSerializable("conteudos");
            usuarioMateria = (Set<UsuarioMateria>) getIntent().getExtras().getSerializable("usuarioMateria");
            populateData(conteudos.get(posicao));
        }
//        conteudosCompletados = getIntent().getExtras().getLong("conteudosCompletados");

//        conteudo = new Conteudo();
//        conteudos = new ArrayList<>();

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


    /**
     * Configura os listeners dos componentes da tela.
     */
    private void initListeners() {
        this.btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!somenteQuestao) {
                    if (conteudoAtual.getAlternativas() != null && !conteudoAtual.getAlternativas().isEmpty()) {
                        if (!verificaResposta()) {
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

                    if (posicao == conteudos.size() - 1) {
                        btnAvancar.setText("Finalizar");
                    } else if (posicao == conteudos.size()) {
//                    if (conteudosCompletados != conteudos.size()) {
//                        currUser.xp += licao.getPontos();
//
//                        currUser.level = Level.calculateLevel(currUser.xp);
//
//                        mDatabase.child("usuarios").child(currUser.id).child("level").setValue(currUser.level);
//                        mDatabase.child("usuarios").child(currUser.id).child("xp").setValue(currUser.xp);
//                    }

                        finalizarMateria();
                    }
                } else {
                    if (!verificaResposta())
                        setResult(1, new Intent().putExtra("acertou", false));
                    else
                        setResult(1, new Intent().putExtra("acertou", true));

                    finish();
                }
            }

            private void finalizarMateria() {
                if (usuarioMateria.iterator().next().getConclusao() == null) {
                    aumentarExperiencia = true;
                }

                UsuarioMateriaService usuarioMateriaService = new UsuarioMateriaServiceImpl();
                usuarioMateria.iterator().next().setConclusao(LocalDateTime.now());

                usuarioMateriaService.finalizarMateria(ConteudoActivity.this, montarDTO(usuarioMateria.iterator().next()), new JsonRequestListener() {
                    @Override
                    public void onSuccess(Object response) {
                        if (aumentarExperiencia)
                            adicionarExperiencia();
                        else
                            finish();
                    }

                    @Override
                    public void onError(Object response) {

                    }
                });

            }

            private void adicionarExperiencia() {
                AtualizacaoExperienciaWrapper wrapper = new AtualizacaoExperienciaWrapper();
                AtualizacaoExperienciaDTO dto = new AtualizacaoExperienciaDTO();
                dto.setId(Configuration.usuario.getHeroiResponseDTO().getId());
                dto.setPontosAdicionais(materia.getPontos());

                wrapper.setAtualizacaoExperienciaDTO(dto);

                heroiService.adicionarExperiencia(ConteudoActivity.this, wrapper, new JsonRequestListener() {
                    @Override
                    public void onSuccess(Object response) {
                        Configuration.usuario.getHeroiResponseDTO().setXp(Configuration.usuario.getHeroiResponseDTO().getXp() + materia.getPontos());
                        finish();
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
                    if (radio.isChecked()) {
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


    /**
     *
     * Cria o wrapper que será utilizado para enviar informações ao servidor e salvar no banco de
     * dados.
     *
     * @param usuarioMateria Contém informações relacionadas à interação do usuário com determinada
     * matéria.
     *
     * @return Retorna o wrapper preenchido com as informações sobre a interação do usuário com
     * determinada matéria.
     *
     */
    private UsuarioMateriaSaveWrapper montarDTO(UsuarioMateria usuarioMateria) {
        UsuarioMateriaSaveWrapper wrapper = new UsuarioMateriaSaveWrapper();
        wrapper.setUsuarioMateriaDTO(mapEntityToDTO(usuarioMateria));

        return wrapper;
    }


    /**
     *
     * Faz o mapeamento da entidade de UsuarioMateria para o DTO.
     *
     * @param usuarioMateria Entidade que será utilizada como referência para criação do DTO.
     * @return Retorna UsuarioMateriaDTO com todas as informações recebidas pela entidade.
     *
     */
    private UsuarioMateriaDTO mapEntityToDTO(UsuarioMateria usuarioMateria) {
        UsuarioMateriaDTO dto = new UsuarioMateriaDTO();
        dto.setInicio(usuarioMateria.getInicio());
        dto.setConclusao(usuarioMateria.getConclusao());

        dto.setUsuario(mapUsuarioEntityToDTO(usuarioMateria.getUsuario()));
        dto.setMateria(mapMateriaToEntityDTO(usuarioMateria.getMateria()));

        return dto;
    }

    /**
     *
     * @param materia
     * @return
     */
    private UsuarioMateriaMateriaDTO mapMateriaToEntityDTO(Materia materia) {
        UsuarioMateriaMateriaDTO materiaDTO = new UsuarioMateriaMateriaDTO();

        if (materia == null)
            materiaDTO.setId(this.materia.getId());
        else
            materiaDTO.setId(materia.getId());

        return materiaDTO;
    }

    private UsuarioMateriaUsuarioDTO mapUsuarioEntityToDTO(Usuario usuario) {
        UsuarioMateriaUsuarioDTO usuarioDTO = new UsuarioMateriaUsuarioDTO();

        if (usuario == null)
            usuarioDTO.setEmail(Configuration.usuario.getEmail());
        else
            usuarioDTO.setEmail(usuario.getEmail());

        return usuarioDTO;
    }

    private void populateData(Conteudo conteudo) {
        conteudoAtual = conteudo;

        if (!somenteQuestao) {
            ab.setTitle(materia.getNome() + " " + (posicao + 1) + "/" + conteudos.size());
        } else {
            ab.setTitle("Questão");
            btnAvancar.setText("Finalizar");
        }

        Spanned spanned;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
            spanned = Html.fromHtml(conteudo.getTexto(), Html.FROM_HTML_MODE_LEGACY);
        else
            spanned = Html.fromHtml(conteudo.getTexto());


        textoConteudo.setScrollY(0);
        textoConteudo.setText(spanned);

        addAlternativas();
    }

    private void addAlternativas() {
        if (alternativasRadioGroup != null)
            alternativasRadioGroup.removeAllViews();

        if (conteudoAtual.getAlternativas() != null && !conteudoAtual.getAlternativas().isEmpty())
            addRadioButtons(conteudoAtual.getAlternativas(), conteudoAtual.getAlternativas().size());
    }

    private void addRadioButtons(Set<Alternativa> alternativasSet, int size) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.contentLayout);
        alternativasRadioGroup = new RadioGroup(this);
        alternativasRadioGroup.setOrientation(LinearLayout.VERTICAL);

        List<Alternativa> alternativas = new ArrayList<>();
        alternativas.addAll(alternativasSet);

        for (int x = 0; x < size; x++) {
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

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);

        bundle.putInt("posicao", posicao);
    }
}
