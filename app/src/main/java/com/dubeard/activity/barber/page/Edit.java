package com.dubeard.activity.barber.page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.dubeard.R;
import com.dubeard.activity.DataLoadListener;
import com.dubeard.activity.FirebaseDataManager;
import com.dubeard.activity.barber.model.Barbeiro;
import com.google.firebase.database.FirebaseDatabase;

public class Edit extends AppCompatActivity implements DataLoadListener <Barbeiro> {

    EditText name;
    EditText phone;
    EditText email;
    Button btSave;
    Button btExclude;
    Button btCancel;
    Barbeiro currentData;
    Intent intent;
    FirebaseDataManager<Barbeiro> firebaseDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_barbeiro);

        initComponents();
        firebaseDataManager.setCurrentData(this);
        defineButtonsAction();
    }

    @Override
    public void onDataLoaded(Barbeiro data) {
        currentData = data;
        populateFields();
    }

    private void initComponents() {
        String itemId = getIntent().getStringExtra("id");

        firebaseDataManager = new FirebaseDataManager(Barbeiro.class);
        firebaseDataManager.setNodeReference(FirebaseDatabase.getInstance().getReference().child("barbeiro").child(itemId));
        intent = new Intent(getApplicationContext(), ListaBarbeiro.class);

        name = findViewById(R.id.editNome);
        phone = findViewById(R.id.editTelefone);
        email = findViewById(R.id.editEmail);
        btSave = findViewById(R.id.btSave);
        btExclude = findViewById(R.id.btExclude);
        btCancel = findViewById(R.id.btCancel);
    }

    private void defineButtonsAction() {
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentData.setName(name.getText().toString());
                currentData.setFone(phone.getText().toString());
                currentData.setMail(email.getText().toString());

                firebaseDataManager.getNodeReference().setValue(currentData);
                startActivity(intent);
            }
        });

        btExclude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseDataManager.getNodeReference().removeValue();
                startActivity(intent);
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void populateFields() {
        name.setText(currentData.getName());
        email.setText(currentData.getMail());
        phone.setText(currentData.getFone());
    }

}
