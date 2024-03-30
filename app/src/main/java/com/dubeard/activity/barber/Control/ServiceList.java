package com.dubeard.activity.barber.Control;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.dubeard.R;
import com.dubeard.activity.MainAdministrator;
import com.dubeard.activity.barber.model.Service;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ServiceList extends AppCompatActivity {

    Button btNew;
    Button btCancel;
    Intent intent;
    ListView listView;
    DatabaseReference databaseReference;
    ArrayAdapter<Service> arrayAdapterServices;
    ArrayList<Service>  arrayListServices = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        initComponents();

        databaseReference = FirebaseDatabase.getInstance().getReference("servico");

        arrayAdapterServices = new ArrayAdapter<Service>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayListServices);
        listView.setAdapter(arrayAdapterServices);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Service service = snapshot.getValue(Service.class);
                arrayListServices.add(
                        new Service(snapshot.getKey(),
                                service.getDescricao(),
                                service.getValor())
                );

                arrayAdapterServices.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Service service = snapshot.getValue(Service.class);
                arrayListServices.remove(
                        new Service(snapshot.getKey(),
                                service.getDescricao(),
                                service.getValor())
                );

                arrayAdapterServices.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        defineButtonsAction();
        defineListViewAction();
    }

    public void initComponents() {
        intent = new Intent(getApplicationContext(), MainAdministrator.class);
        listView = findViewById(R.id.listViewService);
        btNew = findViewById(R.id.btNewServiceList);
        btCancel = findViewById(R.id.btCancelServiceList);
    }

    private void defineButtonsAction() {
        btNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), ServiceCreate.class);
                startActivity(intent);
                finish();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void defineListViewAction() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Service selectedService = (Service) parent.getItemAtPosition(position);
                Intent intent = new Intent(ServiceList.this, ServiceEdit.class);
                intent.putExtra("id", selectedService.getId());
                startActivity(intent);
                finish();
            }
        });
    }
}