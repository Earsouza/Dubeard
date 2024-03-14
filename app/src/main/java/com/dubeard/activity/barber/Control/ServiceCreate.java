package com.dubeard.activity.barber.Control;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.dubeard.R;
import com.dubeard.activity.PrincipalProfissional;
import com.dubeard.activity.barber.Control.ServiceList;
import com.dubeard.activity.barber.model.Service;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ServiceCreate extends AppCompatActivity {

    EditText descricao, valor;
    Button btCadastrarServico, btVoltar, btCancelar;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_create);

        iniciarComponente();
        createService();

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PrincipalProfissional.class);
                startActivity(intent);
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearInput();
            }
        });
    }

    private void iniciarComponente() {
        descricao = findViewById(R.id.editDescricao);
        valor = findViewById(R.id.editValor);
        btCadastrarServico = findViewById(R.id.btCadastrarServico);
        btVoltar = findViewById(R.id.btvoltar);
        btCancelar = findViewById(R.id.btCancelar);
    }

    public void createService() {
        reference =  FirebaseDatabase.getInstance().getReference().child("servico");

        btCadastrarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.push().setValue(new Service(
                        descricao.getText().toString(),
                        valor.getText().toString()));
                Intent intent = new Intent(getApplicationContext(), ServiceList.class);
                startActivity(intent);
            }
        });
    }

    private void clearInput() {
        descricao.setText("");
        valor.setText("");
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