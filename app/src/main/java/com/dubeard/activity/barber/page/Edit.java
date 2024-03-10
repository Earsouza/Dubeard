package com.dubeard.activity.barber.page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dubeard.R;
import com.dubeard.activity.FirebaseDataManager;
import com.dubeard.activity.DataLoadListener;
import com.dubeard.activity.PrincipalProfissional;
import com.dubeard.activity.barber.model.Barbeiro;
import com.google.firebase.database.*;

public class Edit extends FirebaseDataManager implements DataLoadListener <Barbeiro> {

    EditText name;
    EditText phone;
    EditText email;
    Button editButton;
    Button backButton;
    Button cancelButton;
    Barbeiro currentData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_barbeiro);

        initComponents();
        setCurrentData(this);
        defineButtonsAction();
    }

    private void initComponents() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        name = findViewById(R.id.editNome);
        phone = findViewById(R.id.editTelefone);
        email = findViewById(R.id.editEmail);
        editButton = findViewById(R.id.editBarberButton);
        backButton = findViewById(R.id.btvoltar);
        cancelButton = findViewById(R.id.btCancelar);
    }

    @Override
    public void onDataLoaded(Barbeiro data) {
        currentData = data;
        populateFields();
    }

    private void defineButtonsAction() {
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

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentData.setName(name.getText().toString());
                currentData.setFone(phone.getText().toString());
                currentData.setMail(email.getText().toString());

                itemReference.setValue(currentData);

                Intent intent = new Intent(getApplicationContext(), PrincipalProfissional.class);
                startActivity(intent);
            }
        });
    }

    private void populateFields() {
        name.setText(currentData.getName());
        email.setText(currentData.getMail());
        phone.setText(currentData.getFone());
    }

    private void clearInput() {
        name.setText("");
        email.setText("");
        phone.setText("");
    }

}
