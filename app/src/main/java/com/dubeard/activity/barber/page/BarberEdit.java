package com.dubeard.activity.barber.page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.dubeard.R;
import com.dubeard.firebase.DataLoadListener;
import com.dubeard.firebase.FirebaseDataManager;
import com.dubeard.activity.barber.model.Barber;

public class BarberEdit extends AppCompatActivity implements DataLoadListener <Barber> {

    EditText editName;
    EditText editPhone;
    EditText editEmail;
    Button btSave;
    Button btExclude;
    Button btCancel;
    Barber barber;
    Intent intent;
    FirebaseDataManager<Barber> firebaseDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_edit);

        initComponents();
        firebaseDataManager.setCurrentData(this);
        defineButtonsAction();
    }

    @Override
    public void onDataLoaded(Barber data) {
        barber = data;
        populateFields();
    }

    private void initComponents() {
        String nodeId = getIntent().getStringExtra("id");

        firebaseDataManager = new FirebaseDataManager(Barber.class, nodeId);
        intent = new Intent(getApplicationContext(), BarberList.class);

        editName = findViewById(R.id.editNameBarberEdit);
        editPhone = findViewById(R.id.editPhoneBarberEdit);
        editEmail = findViewById(R.id.editEmailBarberEdit);
        btSave = findViewById(R.id.btSaveBarberEdit);
        btExclude = findViewById(R.id.btExcludeBarberEdit);
        btCancel = findViewById(R.id.btCancelBarberEdit);
    }

    private void defineButtonsAction() {
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barber.setName(editName.getText().toString());
                barber.setPhone(editPhone.getText().toString());
                barber.setEmail(editEmail.getText().toString());

                firebaseDataManager.getNodeReference().setValue(barber);
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
        editName.setText(barber.getName());
        editEmail.setText(barber.getEmail());
        editPhone.setText(barber.getPhone());
    }

}
