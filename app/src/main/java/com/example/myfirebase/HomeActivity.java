package com.example.myfirebase;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    EditText campo_tarefa;
    Button btn_add;
    ListView listView;
    ArrayAdapter<Tarefa> arrayAdapter;
    ArrayList<Tarefa> arrayList;
    Tarefa tarefa;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("tarefa");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        campo_tarefa = findViewById(R.id.id_campo_tarefa);
        btn_add = findViewById(R.id.id_btn_add);
        listView = findViewById(R.id.id_list);
        arrayList = new ArrayList<Tarefa>();
        arrayAdapter = new ArrayAdapter<Tarefa>(HomeActivity.this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        btn_add.setOnClickListener(view -> salvarTarefa());
    }

    private void salvarTarefa() {
        String tarefa = campo_tarefa.getText().toString();

        if(tarefa.isEmpty()){
            Toast.makeText(HomeActivity.this, "Preencha a tarefa", Toast.LENGTH_LONG).show();
        }

        // salvar no firebase
        String id = myRef.push().getKey();
        Tarefa minhaTarefa = new Tarefa(id, tarefa);
        myRef.child(id).setValue(minhaTarefa);
        campo_tarefa.setText("");

        // atualiza o arrayList
        arrayList.add(minhaTarefa);
    }
}