package com.dubeard.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dubeard.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class EditarBarbeiro extends AppCompatActivity {

    EditText name, phone, email;
    Button editButton, backButton, cancelButton;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_barbeiro);

        /*String nameId = getIntent().getStringExtra("id");
        reference.child("Barbeiro").child(nameId).getRef();
        DatabaseReference barbeiro = reference.child("Barbeiro").child(nameId);

        barbeiro.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText(Objects.requireNonNull(snapshot.child("name").getValue().toString()));
                phone.setText(Objects.requireNonNull(snapshot.child("fone").getValue().toString()));
                email.setText(Objects.requireNonNull(snapshot.child("mail").getValue().toString()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        init();
        populateFields();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PrincipalProfissional.class);
                startActivity(intent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearInput();
            }
        });
    }

    private void init() {
        name = findViewById(R.id.editNome);
        phone = findViewById(R.id.editTelefone);
        email = findViewById(R.id.editEmail);
        editButton = findViewById(R.id.editBarberButton);
        backButton = findViewById(R.id.btvoltar);
        cancelButton = findViewById(R.id.btCancelar);
    }

    private void populateFields() {

        name.setText(getIntent().getStringExtra("id"));
        email.setText("");
        phone.setText("");
    }

    private void clearInput() {
        name.setText("");
        email.setText("");
        phone.setText("");
    }
}