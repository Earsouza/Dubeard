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
    ArrayList<Servico> listaServicos = new ArrayList<>();
    ServicoAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_servicos_main);

        inicializandoComponente();

        setandoAdapter();

        clicandoNovoCadastroServico();
    }

    private void clicandoNovoCadastroServico() {
        btcadastrarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastroServico.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void inicializandoComponente() {
        listView = findViewById(R.id.listView);
        btcadastrarServico = findViewById(R.id.cadastrarServico);
    }

    public void setandoDadoFirebase() {
        
    }

    public void setandoAdapter() {
        adapter = new ServicoAdapter(this, listaServicos);
        listView.setAdapter(adapter);
    }
}