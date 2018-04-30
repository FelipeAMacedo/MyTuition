package com.example.felipemacedo.mytuition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.felipemacedo.mytuition.dto.LoginDTO;
import com.example.felipemacedo.mytuition.dto.save.wrapper.LoginWrapper;
import com.example.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.example.felipemacedo.mytuition.services.UsuarioService;
import com.example.felipemacedo.mytuition.services.impl.UsuarioServiceImpl;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button mEmailSignInButton;
    private Button mRegisterButton;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private UsuarioService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();
        initListeners();
    }

    private void initComponents() {
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mRegisterButton = (Button) findViewById(R.id.btnRegister);
    }

    private void initListeners() {
        mEmailSignInButton.setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);
    }

    private void signIn() {
        if (!validateForm()) {
            return;
        }

        service = new UsuarioServiceImpl();

        service.logar(this.getBaseContext(), generateWrappedDTO(), new JsonRequestListener() {
            @Override
            public void onSuccess(Object response) {
                LoginActivity.this.moveToHomeActivity();
            }

            @Override
            public void onError(Object response) {

            }
        });
    }

    private LoginWrapper generateWrappedDTO() {
        LoginWrapper wrapper = new LoginWrapper();
        wrapper.setLoginDTO(getLoginData());

        return wrapper;
    }

    private boolean validateForm() {
        return true;
    }

    private void register() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    private void moveToHomeActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.email_sign_in_button) {
            signIn();
        } else if (view.getId() == R.id.btnRegister) {
            register();
        }
    }

    public LoginDTO getLoginData() {
        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setEmail(mEmailView.getText().toString());
        loginDTO.setSenha(mPasswordView.getText().toString());

        return loginDTO;
    }
}

