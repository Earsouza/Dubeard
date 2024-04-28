package com.dubeard.activity.barber.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.dubeard.R;
import com.dubeard.activity.MainAdministrator;
import com.dubeard.activity.barber.model.Barber;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BarberCreate extends AppCompatActivity {

    EditText nome, telefone, email;

    CheckBox checkCabelo, checkBarba, checkSobrancelha;
    Button btCadastrarBarbeiro, btVoltar, btCancelar;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_create);

        iniciarComponente();
        createBarber();

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainAdministrator.class);
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
        telefone = findViewById(R.id.editTelefone);
        email = findViewById(R.id.editEmail);
        btCadastrarBarbeiro = findViewById(R.id.btCadastraBarbeiro);
        btVoltar = findViewById(R.id.btvoltar);
        btCancelar = findViewById(R.id.btCancelar);
        checkCabelo = findViewById(R.id.checkBoxHair);
        checkBarba = findViewById(R.id.checkBoxBeard);
        checkSobrancelha = findViewById(R.id.checkBoxEyebrow);


    }

    public void createBarber() {
        reference =  FirebaseDatabase.getInstance().getReference().child("barbeiro");

        btCadastrarBarbeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.push().setValue(new Barber(
                        nome.getText().toString(),
                        telefone.getText().toString(),
                        email.getText().toString()));
                Intent intent = new Intent(getApplicationContext(), BarberList.class);
                startActivity(intent);
            }
        });

        checkCabelo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //envia para firebase
                }
            }
        });

        checkBarba.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //envia para firebase
                    Toast.makeText(getApplicationContext(), "deu boa", Toast.LENGTH_LONG).show();
                }
            }
        });

        checkSobrancelha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //envia para firebase
                }
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
        Intent intent = new Intent(this, MainAdministrator.class);

        // Adicione flags para limpar a pilha de atividades, se desejar
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        // Inicie a nova atividade
        startActivity(intent);

        // Finalize a atividade atual, se desejar
        finish();
    }
}
