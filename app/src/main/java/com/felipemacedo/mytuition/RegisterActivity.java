package com.felipemacedo.mytuition;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.felipemacedo.mytuition.dto.AlunoDTO;
import com.felipemacedo.mytuition.dto.HeroiDTO;
import com.felipemacedo.mytuition.dto.UsuarioDTO;
import com.felipemacedo.mytuition.dto.save.wrapper.UsuarioSaveWrapper;
import com.felipemacedo.mytuition.enums.Perfil;
import com.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.felipemacedo.mytuition.model.eclipse.Aluno;
import com.felipemacedo.mytuition.model.eclipse.Curso;
import com.felipemacedo.mytuition.model.eclipse.Heroi;
import com.felipemacedo.mytuition.model.eclipse.Usuario;
import com.felipemacedo.mytuition.services.UsuarioService;
import com.felipemacedo.mytuition.services.impl.UsuarioServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private TextView mNomeCompleto;
    private TextView mNomeHeroi;
    private TextView mRa;
    private TextView mNascimento;
    private RadioButton mMasculino;
    private RadioButton mFeminino;
    private TextView mEmail;
    private TextView mSenha;
    private TextView mConfSenha;
    private Button mCadastrarButton;

    private TextView mCpf;
    private TextView mDataEntrada;
    private Spinner mCurso;
    private RadioButton mSim;
    private RadioButton mNao;

    private UsuarioService service;

    private Aluno aluno;
    private Heroi heroi;
    private Usuario usuario;
    private String mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponents();
        initListeners();
    }

    /**
     * Inicia os componentes visuais da tela.
     */
    private void initComponents() {
        mNomeCompleto = (TextView) findViewById(R.id.etCadNomeCompleto);
        mNomeHeroi = (TextView) findViewById(R.id.etCadNomeHeroi);
        mRa = (TextView) findViewById(R.id.etCadRA);
        mNascimento = (TextView) findViewById(R.id.etCadNascimento);

        mMasculino = (RadioButton) findViewById(R.id.rBtnCadMasculino);
        mFeminino = (RadioButton) findViewById(R.id.rBtnCadFeminino);

        mEmail = (TextView) findViewById(R.id.etCadEmail);
        mSenha = (TextView) findViewById(R.id.etCadPassword);
        mConfSenha = (TextView) findViewById(R.id.etCadPasswordConfirm);
        mCadastrarButton = (Button) findViewById(R.id.btnCreateAccount);


        mCpf = (TextView) findViewById(R.id.etCadCpf);
        mDataEntrada = (TextView) findViewById(R.id.etCadEntrada);
        mCurso = (Spinner) findViewById(R.id.sprCurso);
        mSim = (RadioButton) findViewById(R.id.rBtnCadTrabalha);
        mNao = (RadioButton) findViewById(R.id.rBtnCadNaoTrabalha);

        prepararSpinner();
    }

    private void prepararSpinner() {

        List<Curso> cursos = new ArrayList<>();
        Curso hint = new Curso();
        hint.setId(0L);
        hint.setNome("Curso");

        Curso c1 = new Curso();
        c1.setId(1L);
        c1.setNome("ADS");

        Curso c2 = new Curso();
        c2.setId(2L);
        c2.setNome("Comex");

        cursos.add(hint);
        cursos.add(c1);
        cursos.add(c2);

        ArrayAdapter<Curso> adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, cursos) {

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {

                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                if (position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);

                return view;
            }
        };

        mCurso.setAdapter(adapter);
    }

    /**
     * Configura os listeners dos componentes da tela.
     */
    private void initListeners() {
        mCadastrarButton.setOnClickListener((view) -> {
            if (!validateForm()) {
                return;
            }

            service = new UsuarioServiceImpl();
            service.registrar(view.getContext(), generateWrappedDTO(), new JsonRequestListener() {
                @Override
                public void onSuccess(Object response) {
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }

                @Override
                public void onError(Object response) {

                }
            });
        });
    }

    private boolean validateForm() {
        String nomeCompleto = mNomeCompleto.getText().toString();
        String confSenha = mConfSenha.getText().toString();
        String nomeHeroi = mNomeHeroi.getText().toString();
        String ra = mRa.getText().toString();
        String dataNascimento = mNascimento.getText().toString();
        String senha = mSenha.getText().toString();
        String email = mEmail.getText().toString();

        String cpf = mCpf.getText().toString();
        String dataEntrada = mDataEntrada.getText().toString();

        Curso cursoSelecionado = (Curso) mCurso.getSelectedItem();
        Long curso = cursoSelecionado.getId();

        if (nomeCompleto.isEmpty() || nomeHeroi.isEmpty() ||
                ra.isEmpty() || dataNascimento.isEmpty() ||
                (!mMasculino.isChecked() && !mFeminino.isChecked()) ||
                (!mSim.isChecked() && !mNao.isChecked()) ||
                email.equals("") || senha.isEmpty() ||
                confSenha.isEmpty() || curso == 0L ||
                cpf.isEmpty() || dataEntrada.isEmpty()) {

            exibirToast("Preencha os dados corretamente");
            return false;
        }

        if (senha.length() < 6) {
            exibirToast("A senha deve ter pelo menos 6 dígitos");
            return false;
        }

        if (!senha.equals(confSenha)) {
            exibirToast("As senhas informadas são diferentes");
            return false;
        }

        return true;
    }

    private UsuarioSaveWrapper generateWrappedDTO() {
        UsuarioDTO usuario = getUsuarioData();
        usuario.setAlunoDTO(getAlunoData());
        usuario.setHeroiDTO(getHeroiData());

        UsuarioSaveWrapper wrapper = new UsuarioSaveWrapper();
        wrapper.setUsuarioDTO(usuario);

        return wrapper;
    }

    private AlunoDTO getAlunoData() {
        AlunoDTO aluno = new AlunoDTO();

        aluno.setNome(mNomeCompleto.getText().toString());
        aluno.setRa(mRa.getText().toString());


        aluno.setCpf(mCpf.getText().toString());

        if (mSim.isChecked()) {
            aluno.setTrabalhaArea(true);
        } else if (mNao.isChecked()) {
            aluno.setTrabalhaArea(false);
        }

        Curso selecionado = (Curso) mCurso.getSelectedItem();
        aluno.setCurso(selecionado.getId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        aluno.setDataNascimento(LocalDate.parse(mNascimento.getText().toString(), formatter));
        aluno.setDataEntrada(LocalDate.parse(mDataEntrada.getText().toString(), formatter));

        if (mMasculino.isChecked()) {
            aluno.setSexo(true);
        } else if (mFeminino.isChecked()) {
            aluno.setSexo(false);
        }

        return aluno;
    }

    private UsuarioDTO getUsuarioData() {
        UsuarioDTO usuario = new UsuarioDTO();

        usuario.setEmail(mEmail.getText().toString());
        usuario.setSenha(mSenha.getText().toString());
        usuario.setPerfil(Perfil.ALUNO);

        return usuario;
    }

    private HeroiDTO getHeroiData() {
        HeroiDTO heroi = new HeroiDTO();

        heroi.setNome(mNomeHeroi.getText().toString());

        return heroi;
    }

    private void exibirToast(String mensagem) {
        Toast.makeText(RegisterActivity.this, mensagem, Toast.LENGTH_LONG).show();
    }
}
