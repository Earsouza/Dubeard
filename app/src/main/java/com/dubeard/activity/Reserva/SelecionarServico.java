package com.dubeard.activity.Reserva;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dubeard.R;
import com.dubeard.activity.Admin.model.Service;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SelecionarServico extends AppCompatActivity {

    private ListView listViewServicos;
    private DatabaseReference servicoReference;
    private ArrayAdapter<Service> arrayAdapterServices;
    private ArrayList<Service> arrayListServices = new ArrayList<>();
    public Service selectedService;
    private Button btAvancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_servico);

        initComponents();
        setupServicoListener();

        listViewServicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedService = (Service) parent.getItemAtPosition(position);
            }
        });

        btAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedService != null && selectedService.getDescricao() != null && !selectedService.getDescricao().isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), SelecionarProfissional.class);
                    intent.putExtra("selectedServiceDescricao", selectedService.getDescricao());
                    startActivity(intent);
                } else {
                    Toast.makeText(SelecionarServico.this, "Por favor, selecione um serviço.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initComponents() {
        btAvancar = findViewById(R.id.btAvancar);
        listViewServicos = findViewById(R.id.listViewServicos);
    }

    private void setupServicoListener() {
        servicoReference = FirebaseDatabase.getInstance().getReference().child("servico");
        arrayAdapterServices = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice, arrayListServices);
        listViewServicos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listViewServicos.setAdapter(arrayAdapterServices);

        servicoReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Service service = snapshot.getValue(Service.class);
                arrayListServices.add(new Service(snapshot.getKey(), service.getDescricao(), service.getValor()));
                arrayAdapterServices.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this, MainClient.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
