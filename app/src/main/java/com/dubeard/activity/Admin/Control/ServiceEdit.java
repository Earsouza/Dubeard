package com.dubeard.activity.Admin.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dubeard.R;
import com.dubeard.activity.Admin.model.Service;
import com.dubeard.firebase.DataLoadListener;
import com.dubeard.firebase.FirebaseDataManager;

public class ServiceEdit extends AppCompatActivity implements DataLoadListener<Service> {

    EditText editDescricao;
    EditText editValor;
    Button btSave;
    Button btExclude;
    Button btCancel;
    Service service;
    Intent intent;
    FirebaseDataManager<Service> firebaseDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_edit);

        initComponents();
        firebaseDataManager.setCurrentData(this);
        defineButtonsAction();
    }

    @Override
    public void onDataLoaded(Service data) {
        service = data;
        populateFields();
    }

    private void initComponents() {
        String nodeId = getIntent().getStringExtra("id");

        firebaseDataManager = new FirebaseDataManager(Service.class, "servico", nodeId);
        firebaseDataManager.init();

        intent = new Intent(getApplicationContext(), ServiceList.class);

        editDescricao = findViewById(R.id.editDescriServiceEdit);
        editValor = findViewById(R.id.editValueServiceEdit);
        btSave = findViewById(R.id.btSaveServiceEdit);
        btExclude = findViewById(R.id.btExcludeServiceEdit);
        btCancel = findViewById(R.id.btCancelServiceEdit);
    }

    private void defineButtonsAction() {
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.setDescricao(editDescricao.getText().toString());
                service.setValor(editValor.getText().toString());

                firebaseDataManager.getNodeReference().setValue(service);
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
        editDescricao.setText(service.getDescricao());
        editValor.setText(service.getValor());
    }

}