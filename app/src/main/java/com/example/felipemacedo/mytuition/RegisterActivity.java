package com.example.felipemacedo.mytuition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.felipemacedo.mytuition.dto.AlunoDTO;
import com.example.felipemacedo.mytuition.dto.HeroiDTO;
import com.example.felipemacedo.mytuition.dto.UsuarioDTO;
import com.example.felipemacedo.mytuition.dto.save.wrapper.UsuarioSaveWrapper;
import com.example.felipemacedo.mytuition.enums.Perfil;
import com.example.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.example.felipemacedo.mytuition.model.eclipse.Aluno;
import com.example.felipemacedo.mytuition.model.eclipse.Heroi;
import com.example.felipemacedo.mytuition.model.eclipse.Usuario;
import com.example.felipemacedo.mytuition.services.UsuarioService;
import com.example.felipemacedo.mytuition.services.impl.UsuarioServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    }

    private void initListeners() {
        mCadastrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

            }
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

        if (nomeCompleto.isEmpty() || nomeHeroi.isEmpty() ||
                ra.isEmpty() || dataNascimento.isEmpty() ||
                (!mMasculino.isChecked() && !mFeminino.isChecked()) ||
                email.equals("") || senha.isEmpty() ||
                confSenha.isEmpty()) {

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


        mockData(aluno);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        aluno.setDataNascimento(LocalDate.parse(mNascimento.getText().toString(), formatter));


        if (mMasculino.isChecked()) {
            aluno.setSexo(true);
        } else if (mFeminino.isChecked()) {
            aluno.setSexo(false);
        }

        return aluno;
    }

    private void mockData(AlunoDTO aluno) {
        aluno.setCpf("431.353.768-69");
        aluno.setTrabalhaArea(true);
        aluno.setDataEntrada(LocalDate.now());
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
