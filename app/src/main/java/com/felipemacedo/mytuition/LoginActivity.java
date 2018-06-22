package com.felipemacedo.mytuition;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.felipemacedo.mytuition.conf.Configuration;
import com.felipemacedo.mytuition.dto.LoginDTO;
import com.felipemacedo.mytuition.dto.login.UsuarioResponseDTO;
import com.felipemacedo.mytuition.dto.save.wrapper.LoginWrapper;
import com.felipemacedo.mytuition.listeners.JsonRequestListener;
import com.felipemacedo.mytuition.services.UsuarioService;
import com.felipemacedo.mytuition.services.impl.UsuarioServiceImpl;
import com.felipemacedo.mytuition.utils.LocalDateAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button mEmailSignInButton;
    private Button mRegisterButton;
    private TextView txtTrocarSenha;
    private TextView txtEsqueciSenha;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private UsuarioService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();
        initListeners();

//        mEmailView.setText("felipexalves@gmail.com");
//        mPasswordView.setText("felipe");

//        signIn();
    }

    /**
     * Inicia os componentes visuais da tela.
     */
    private void initComponents() {
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mRegisterButton = (Button) findViewById(R.id.btnRegister);
        mEmailSignInButton.requestFocus();
        txtTrocarSenha = (TextView) findViewById(R.id.txtTrocarSenha);
        txtEsqueciSenha = (TextView) findViewById(R.id.txtEsqueciSenha);

        service = new UsuarioServiceImpl();
    }

    /**
     * Configura os listeners dos componentes da tela.
     */
    private void initListeners() {
        mEmailSignInButton.setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);
        txtTrocarSenha.setOnClickListener((view) -> {
            Intent intent = new Intent(this, AlterarSenhaActivity.class);
            startActivity(intent);
        });

        txtEsqueciSenha.setOnClickListener((view) -> {
            service.recuperarSenha(this, mEmailView.getText().toString(), new JsonRequestListener() {
                @Override
                public void onSuccess(Object response) {
                    AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).setMessage("Senha alterada com sucesso").create();
                    alertDialog.show();
                }

                @Override
                public void onError(Object response) {
                    AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).setMessage("ERRRRROU!").create();
                    alertDialog.show();
                }
            });
        });
    }

    private void signIn() {
        if (!validateForm()) {
            return;
        }

        service = new UsuarioServiceImpl();

        service.logar(this.getBaseContext(), generateWrappedDTO(), new JsonRequestListener<Object>() {
            @Override
            public void onSuccess(Object response) {

                try {
                    JSONObject jsonObject = getJSONObject(response);
                } catch (JSONException e) {
                    Toast.makeText(LoginActivity.this, "Erro ao converter para JSONObject", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                        .create();

                UsuarioResponseDTO dto = gson.fromJson(String.valueOf(response), UsuarioResponseDTO.class);

                LoginActivity.this.moveToHomeActivity(dto);

            }

            @Override
            public void onError(Object response) {
                Toast.makeText(LoginActivity.this, "Erro ao tentar fazer o login", Toast.LENGTH_LONG).show();
            }
        });
    }

    private JSONObject getJSONObject(Object object) throws JSONException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String json = gson.toJson(object).replace("\n", "");
        return new JSONObject(json);
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

    private void moveToHomeActivity(UsuarioResponseDTO dto) {
        Configuration.usuario = dto;
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

    private LoginDTO getLoginData() {
        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setEmail(mEmailView.getText().toString());
        loginDTO.setSenha(mPasswordView.getText().toString());

        return loginDTO;
    }
}

