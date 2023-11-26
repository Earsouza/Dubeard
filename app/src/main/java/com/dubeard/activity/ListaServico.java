package com.dubeard.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.dubeard.R;
import com.dubeard.activity.model.Servico;
import com.dubeard.adapter.ServicoAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ListaServico extends AppCompatActivity {

    Button btcadastrarServico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_servicos_main);
        btcadastrarServico = findViewById(R.id.cadastrarServico);


        ListView listView = findViewById(R.id.listView);

        List<Servico> listaServicos = new ArrayList<>();
        listaServicos.add(new Servico("Barba", 30.0));
        listaServicos.add(new Servico("Cabelo Máquina", 30.0));
        listaServicos.add(new Servico("Cabelo Tesoura", 50.0));
        listaServicos.add(new Servico("Completo (Cabelo+Barba)", 65.0));

        ServicoAdapter adapter = new ServicoAdapter(this, listaServicos);
        listView.setAdapter(adapter);
        btcadastrarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastroServico.class);
                startActivity(intent);
                finish();
            }
        });
    }
}