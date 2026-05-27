package com.example.myfirebase;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    public EditText campo_email;
    public EditText campo_senha;
    public Button btn_login;
    public Button btn_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();
        campo_email = findViewById(R.id.id_edit_email);
        campo_senha = findViewById(R.id.id_edit_senha);
        btn_login = findViewById(R.id.id_btn_login);

        btn_login.setOnClickListener(view -> validar() );
        btn_registrar = findViewById(R.id.id_btn_registrar);

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_registro, null);

                EditText novo_email = dialogView.findViewById(R.id.id_novo_email);
                EditText nova_senha = dialogView.findViewById(R.id.id_nova_senha);
                EditText confirmar_senha = dialogView.findViewById(R.id.id_confirmar_senha);
                Button btn_cadastrar = dialogView.findViewById(R.id.id_btn_cadastrar);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(dialogView);

                AlertDialog dialog = builder.create();

                btn_cadastrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        registrar(novo_email.getText().toString(), nova_senha.getText().toString());
                    }
                });

                dialog.show();

            }});


    }

    private void validar() {
        String email = campo_email.getText().toString();
        String password = campo_senha.getText().toString();
        if ((!email.contains("@"))){
            campo_email.setError("Digite um email válido!");
            return;
        }
        if (!(email.length() > 5)){
            campo_email.setError("Digite seu usuário de email");
        }

        if(password.isEmpty()){
            campo_senha.setError("Digite sua senha");
            return;
        } else if (!(password.length() >= 6)) {
            campo_senha.setError("A senha contém no minimo 6 caracteres");
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "sucesso ao conectar", Toast.LENGTH_LONG).show();
                        Intent it = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(it);
                    } else {
                        Toast.makeText(MainActivity.this, "falha ao conectar", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void registrar(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this,"usuário registrado com sucesso", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this,"não foi possível registrar o usuário", Toast.LENGTH_LONG).show();
                    }
                });
    }

}