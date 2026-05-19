package com.example.myfirebase;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    public EditText campo_email;
    public EditText campo_senha;
    public Button btn_login;

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
        btn_login = findViewById(R.id.id_button);



        btn_login.setOnClickListener(view ->
                registrar()
        );



    }

    private void validar() {
        String email = campo_email.getText().toString();
        String password = campo_senha.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "sucesso ao conectado", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(MainActivity.this, "falha ao conectar", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void registrar(){
        String email = campo_email.getText().toString();
        String password = campo_senha.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this,"usuário registrado com sucesso", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this,"não foi possível -registrar o usuário", Toast.LENGTH_LONG).show();
                    }
                });
    }

}