package com.example.myfirebase;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
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
    public EditText novo_email;
    public EditText nova_senha;
    public  EditText confirmar_senha;
    public Button btn_login;
    public Button btn_registrar;
    public  Button btn_cadastrar;

    @SuppressLint("MissingInflatedId")
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
        novo_email = findViewById(R.id.id_novo_email);
        campo_senha = findViewById(R.id.id_edit_senha);
        nova_senha = findViewById(R.id.id_nova_senha);
        confirmar_senha = findViewById(R.id.id_confirmar_senha);
        btn_login = findViewById(R.id.id_btn_login);
        btn_registrar = findViewById(R.id.id_btn_registrar);
        btn_cadastrar = findViewById(R.id.id_btn_cadastrar);

        btn_login.setOnClickListener(view ->
                validar()
        );

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Dialog dialog = new Dialog(MainActivity.this);
              dialog.setContentView(R.layout.dialog_registro);
              dialog.show();
            }});
//        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(nova_senha.equals(confirmar_senha)){
//                    Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_LONG).show();
//                }else {
//                    Toast.makeText(MainActivity.this, "erro", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }

    private void validar() {
        String email = campo_email.getText().toString();
        String password = campo_senha.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "sucesso ao conectar", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(MainActivity.this, "falha ao conectar", Toast.LENGTH_LONG).show();
                    }
                });
    }

//    private void registrar(){
//        String email = novo_email.getText().toString();
//
//        String password = nova_senha.getText().toString();
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, task -> {
//                    if (task.isSuccessful()) {
//                        Toast.makeText(MainActivity.this,"usuário registrado com sucesso", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(MainActivity.this,"não foi possível -registrar o usuário", Toast.LENGTH_LONG).show();
//                    }
//                });
//    }

}