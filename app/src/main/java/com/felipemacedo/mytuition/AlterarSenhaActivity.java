package com.felipemacedo.mytuition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AlterarSenhaActivity extends AppCompatActivity {
    
    private EditText etAltSenEmail;
    private EditText etAltSenSenhaAtual;
    private EditText etAltSenNovaSenha;
    private EditText etAltSenConfirmarNovaSenha;
    private Button btnAlterarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);
        
        initComponents();
        initListeners();
    }

    /**
     * Inicia os componentes visuais da tela.
     */
    private void initComponents() {
         etAltSenEmail = (EditText) findViewById(R.id.etAltSenEmail);
         etAltSenSenhaAtual = (EditText) findViewById(R.id.etAltSenSenhaAtual);
         etAltSenNovaSenha = (EditText) findViewById(R.id.etAltSenNovaSenha);
         etAltSenConfirmarNovaSenha = (EditText) findViewById(R.id.etAltSenConfirmarNovaSenha);
         btnAlterarSenha = (Button) findViewById(R.id.btnAlterarSenha);
    }

    /**
     * Configura os listeners dos componentes da tela.
     */
    private void initListeners() {
        btnAlterarSenha.setOnClickListener((view) -> {
            //TODO
            finish();
        });
    }
}
