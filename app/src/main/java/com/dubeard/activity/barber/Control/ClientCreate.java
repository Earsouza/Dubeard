package com.dubeard.activity.barber.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dubeard.R;
import com.dubeard.activity.PrincipalProfissional;
import com.dubeard.activity.barber.model.Client;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClientCreate extends AppCompatActivity {

    EditText nome, telefone, email;
    Button btCadastrarCliente, btVoltar, btCancelar;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_cliente);

        iniciarComponente();
        createClient();

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
        nome = findViewById(R.id.editNome);
        telefone = findViewById(R.id.editCelular);
        email = findViewById(R.id.editEmail);
        btCadastrarCliente = findViewById(R.id.btCadastrarCliente);
        btCancelar = findViewById(R.id.btCancelarCliente);
        btVoltar = findViewById(R.id.btvoltar);

    }

    public void createClient() {
        reference = FirebaseDatabase.getInstance().getReference().child("cliente");

        btCadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.push().setValue(new Client(
                        nome.getText().toString(),
                        telefone.getText().toString(),
                        email.getText().toString()));
                Intent intent = new Intent(getApplicationContext(), ClientList.class);
                startActivity(intent);
            }
        });
    }

    private void clearInput() {
        nome.setText("");
        email.setText("");
        telefone.setText("");
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