package com.dubeard.activity.Admin.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dubeard.R;
import com.dubeard.activity.Admin.model.Client;
import com.dubeard.firebase.DataLoadListener;
import com.dubeard.firebase.FirebaseDataManager;

public class ClientEdit extends AppCompatActivity implements DataLoadListener<Client> {

    EditText editName;
    EditText editPhone;
    EditText editEmail;
    Button btSave;
    Button btExclude;
    Button btCancel;
    Client client;
    Intent intent;
    FirebaseDataManager<Client> firebaseDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_edit);

        initComponents();
        firebaseDataManager.setCurrentData(this);
        defineButtonsAction();
    }
    public void onDataLoaded(Client data) {
        client = data;
         populateFields();
    }


    private void initComponents() {
        String nodeId = getIntent().getStringExtra("id");

        firebaseDataManager = new FirebaseDataManager(Client.class, "cliente", nodeId);
        firebaseDataManager.init();

        intent = new Intent(getApplicationContext(), ClientList.class);

        editName = findViewById(R.id.editNameClientEdit);
        editPhone = findViewById(R.id.editPhoneClientEdit);
        editEmail = findViewById(R.id.editEmailClientEdit);
        btSave = findViewById(R.id.btSaveClientEdit);
        btExclude = findViewById(R.id.btExcludeClientEdit);
        btCancel = findViewById(R.id.btCancelClientEdit);

    }
    private void defineButtonsAction() {
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                client.setName(editName.getText().toString());
                client.setPhone(editPhone.getText().toString());
                client.setMail(editEmail.getText().toString());

                firebaseDataManager.getNodeReference().setValue(client);
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
        editName.setText(client.getName());
        editEmail.setText(client.getMail());
        editPhone.setText(client.getPhone());
    }

}