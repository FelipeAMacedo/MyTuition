package com.example.felipemacedo.mytuition;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.felipemacedo.mytuition.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private Usuario usuario;
    private String mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

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

                mAuth.createUserWithEmailAndPassword(usuario.email, usuario.senha).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                            mensagem = "Conta criada com sucesso";
                            Toast.makeText(RegisterActivity.this, mensagem, Toast.LENGTH_SHORT).show();
                        } else {
                            mensagem = "Erro na criação da conta";
                            Toast.makeText(RegisterActivity.this, mensagem,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void onAuthSuccess(FirebaseUser user) {
        writeNewUser(user.getUid());
    }

    private void writeNewUser(String userId) {
        mDatabase.child("usuarios").child(userId).setValue(usuario);
    }

    private boolean validateForm() {
        usuario = new Usuario();

        usuario.nomeCompleto = mNomeCompleto.getText().toString();
        usuario.nomeHeroi = mNomeHeroi.getText().toString();
        usuario.ra = Long.parseLong(mRa.getText().toString());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            usuario.dataNascimento = sdf.parse(mNascimento.getText().toString()).getTime();
        } catch (ParseException pe) {
            pe.printStackTrace();
        }

        if (mMasculino.isChecked()) {
            usuario.sexo = true;
        } else if (mFeminino.isChecked()) {
            usuario.sexo = false;
        }


        usuario.email = mEmail.getText().toString();
        usuario.senha = mSenha.getText().toString();
        String confSenha = mConfSenha.getText().toString();

        if (usuario.nomeCompleto.equals("") || usuario.nomeHeroi.equals("") || usuario.ra < 1l || usuario.dataNascimento < 1l || (!mMasculino.isChecked() && !mFeminino.isChecked()) || usuario.email.equals("") || usuario.senha.equals("") || confSenha.equals("")) {
            exibirToast("Preencha os dados corretamente");
            return false;
        }

        if (usuario.senha.length() < 6) {
            exibirToast("A senha deve ter pelo menos 6 dígitos");
            return false;
        }

        if (!usuario.senha.equals(confSenha)) {
            exibirToast("As senhas informadas são diferentes");
            return false;
        }

        return true;
    }

    private void exibirToast(String mensagem) {
        Toast.makeText(RegisterActivity.this, mensagem, Toast.LENGTH_LONG).show();
    }
}
