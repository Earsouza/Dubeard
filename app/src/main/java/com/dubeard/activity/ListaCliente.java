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

import java.util.ArrayList;

public class ListaCliente extends AppCompatActivity {

    Button btCadastrarCliente;
    ArrayList<Servico> listaCliente = new ArrayList<>();
    ServicoAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_clientes);

        inicializandoComponente();

        setandoAdapter();

        clicandoNovoCadastroServico();
    }

    private void clicandoNovoCadastroServico() {
        btCadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastraCliente.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void inicializandoComponente() {
        listView = findViewById(R.id.listView);
        btCadastrarCliente = findViewById(R.id.btCadastrarCliente);
    }

    public void setandoAdapter() {
        adapter = new ServicoAdapter(this, listaCliente);
        listView.setAdapter(adapter);
    }
}