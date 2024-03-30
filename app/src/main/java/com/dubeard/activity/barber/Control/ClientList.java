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
import com.dubeard.activity.barber.model.Client;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ClientList extends AppCompatActivity {

    Button btNew;
    Button btCancel;
    Intent intent;
    ListView listView;
    DatabaseReference databaseReference;
    ArrayAdapter<Client> arrayAdapterClients;
    ArrayList<Client> arrayListClients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        initComponents();

        databaseReference = FirebaseDatabase.getInstance().getReference("cliente");


        arrayAdapterClients = new ArrayAdapter<Client>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayListClients);
        listView.setAdapter(arrayAdapterClients);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Client client = snapshot.getValue(Client.class);
                arrayListClients.add(
                        new Client(snapshot.getKey(),
                                client.getName(),
                                client.getPhone(),
                                client.getMail())
                );
                arrayAdapterClients.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Client client = snapshot.getValue(Client.class);
                arrayListClients.remove(
                        new Client(snapshot.getKey(),
                                client.getName(),
                                client.getPhone(),
                                client.getMail())
                );

                arrayAdapterClients.notifyDataSetChanged();
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
        listView = findViewById(R.id.listViewClient);
        btNew = findViewById(R.id.btNewClientList);
        btCancel = findViewById(R.id.btCancelClientList);
    }

    private void defineButtonsAction() {
        btNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), ClientCreate.class);
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
                Client selectedClient = (Client) parent.getItemAtPosition(position);
                Intent intent = new Intent(ClientList.this, ClientEdit.class);
                intent.putExtra("id", selectedClient.getId());
                startActivity(intent);
                finish();
            }
        });
    }


}