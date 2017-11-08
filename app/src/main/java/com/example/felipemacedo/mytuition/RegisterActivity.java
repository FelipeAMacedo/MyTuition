package com.example.felipemacedo.mytuition;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class RegisterActivity extends AppCompatActivity {

    private TextView mEmail;
    private TextView mSenha;
    private TextView mConfSenha;
    private Button mCadastrarButton;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String email;
    private String senha;
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

                mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
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
        String username = usernameFromEmail(user.getEmail());
        writeNewUser(user.getUid(), username, user.getEmail(), senha);
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private void writeNewUser(String userId, String username, String email, String senha) {
        Usuario usuario = new Usuario (username, email, senha, 1, 0);

        mDatabase.child("usuarios").child(userId).setValue(usuario);
    }

    private boolean validateForm() {
        email = mEmail.getText().toString();
        senha = mSenha.getText().toString();
        String confSenha = mConfSenha.getText().toString();

        if (email.equals("") || senha.equals("") || confSenha.equals("")) {
            mensagem = "Preencha os dados corretamente";
            Toast.makeText(RegisterActivity.this, mensagem, Toast.LENGTH_LONG).show();
            return false;
        }

        if (senha.length() < 6) {
            mensagem = "A senha deve ter pelo menos 6 dígitos";
            Toast.makeText(RegisterActivity.this, mensagem, Toast.LENGTH_LONG).show();
            return false;
        }

        if (!senha.equals(confSenha)) {
            mensagem = "Preencha os dados corretamente";
            Toast.makeText(RegisterActivity.this, mensagem, Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
