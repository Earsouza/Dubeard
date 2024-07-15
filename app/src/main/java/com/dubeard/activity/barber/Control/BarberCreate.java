package com.dubeard.activity.barber.Control;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.dubeard.R;
import com.dubeard.activity.MainAdministrator;
import com.dubeard.activity.barber.model.Barber;
import com.dubeard.activity.barber.model.GrupoServicos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BarberCreate extends AppCompatActivity {

    EditText nome, telefone, email;
    int i = 0;

    CheckBox checkCabelo, checkBarba, checkSobrancelha;
    Button btCadastrarBarbeiro, btVoltar, btCancelar;

    DatabaseReference reference;
    GrupoServicos grupo = new GrupoServicos();

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
        //checkbox
        checkCabelo = findViewById(R.id.checkBoxHair);
        checkBarba = findViewById(R.id.checkBoxBeard);
        checkSobrancelha = findViewById(R.id.checkBoxEyebrow);
    }

    public void createBarber() {
        reference = FirebaseDatabase.getInstance().getReference().child("barbeiro");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    i = (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
            }
        });

        btCadastrarBarbeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Barber barber = new Barber();

                if (checkCabelo.isChecked()) {
                    barber.setIsCabelo(true);
                } else {
                    barber.setIsCabelo(false);
                }
                if (checkBarba.isChecked()) {
                    barber.setIsBarba(true);
                } else {
                    barber.setIsBarba(false);
                }
                if (checkSobrancelha.isChecked()) {
                    barber.setIsSobrancelha(true);
                } else {
                    barber.setIsSobrancelha(false);
                }

                reference.push().setValue(new Barber(
                        nome.getText().toString(),
                        telefone.getText().toString(),
                        email.getText().toString(),
                        barber.isCabelo(),
                        barber.isBarba(),
                        barber.isSobrancelha()
                ));

                Intent intent = new Intent(getApplicationContext(), BarberList.class);
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
        Intent intent = new Intent(this, MainAdministrator.class);

        // Adicione flags para limpar a pilha de atividades, se desejar
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        // Inicie a nova atividade
        startActivity(intent);

        // Finalize a atividade atual, se desejar
        finish();
    }
}
