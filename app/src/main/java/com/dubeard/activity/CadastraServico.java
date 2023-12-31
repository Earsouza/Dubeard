package com.dubeard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.dubeard.R;
import com.dubeard.activity.model.Servico;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastraServico extends AppCompatActivity {

    EditText editDescricao, editValor;
    Button btCadastrarServico, btVoltar;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_servico);

        inicializandoComponentes();
        cadastrarServico();

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PrincipalProfissional.class);
                startActivity(intent);
            }
        });
    }

    private void inicializandoComponentes() {
        editDescricao = findViewById(R.id.editDescricao);
        editValor = findViewById(R.id.editValor);
        btCadastrarServico = findViewById(R.id.btCadastrar);
        btVoltar = findViewById(R.id.btvoltar);

    }

    public void cadastrarServico() {

        reference =  FirebaseDatabase.getInstance().getReference().child("servico");

        btCadastrarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.push().setValue(new Servico(editDescricao.getText().toString(), Double.parseDouble(editValor.getText().toString())));
                Intent intent = new Intent(getApplicationContext(), PrincipalProfissional.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // Defina a tela de destino que você deseja abrir ao pressionar o botão de voltar
        Intent intent = new Intent(this, PrincipalProfissional.class);

        // Adicione flags para limpar a pilha de atividades, se desejar
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        // Inicie a nova atividade
        startActivity(intent);

        // Finalize a atividade atual, se desejar
        finish();
    }
}
