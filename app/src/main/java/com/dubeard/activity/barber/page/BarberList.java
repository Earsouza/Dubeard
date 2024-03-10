package com.dubeard.activity.barber.page;

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
import com.dubeard.activity.PrincipalProfissional;
import com.dubeard.activity.barber.model.Barber;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BarberList extends AppCompatActivity {

    Button btNew;
    Button btCancel;
    Intent intent;
    ListView listView;
    DatabaseReference databaseReference;
    ArrayAdapter<Barber> arrayAdapterBarbers;
    ArrayList<Barber> arrayListBarbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_list);

        initComponents();

        databaseReference = FirebaseDatabase.getInstance().getReference("barbeiro");

        arrayAdapterBarbers = new ArrayAdapter<Barber>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayListBarbers);
        listView = (ListView) findViewById(R.id.listViewBarber);
        listView.setAdapter(arrayAdapterBarbers);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Barber barber = snapshot.getValue(Barber.class);
                arrayListBarbers.add(
                        new Barber(snapshot.getKey(),
                                barber.getName(),
                                barber.getPhone(),
                                barber.getEmail())
                );

                arrayAdapterBarbers.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Barber barber = snapshot.getValue(Barber.class);
                arrayListBarbers.remove(
                        new Barber(snapshot.getKey(),
                                barber.getName(),
                                barber.getPhone(),
                                barber.getEmail())
                );

                arrayAdapterBarbers.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        defineButtonsAction();
        defineListViewAction();
    }

    public void initComponents() {
        intent = new Intent(getApplicationContext(), PrincipalProfissional.class);

        btNew = findViewById(R.id.btNewBarberList);
        btCancel = findViewById(R.id.btCancelBarberList);
    }

    private void defineButtonsAction() {
        btNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), BarberCreate.class);
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
                Barber selectedBarber = (Barber) parent.getItemAtPosition(position);
                Intent intent = new Intent(BarberList.this, BarberEdit.class);
                intent.putExtra("id", selectedBarber.getId());
                startActivity(intent);
                finish();
            }
        });
    }
}
