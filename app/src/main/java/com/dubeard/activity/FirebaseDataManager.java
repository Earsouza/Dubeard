package com.dubeard.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.dubeard.activity.barber.model.Barbeiro;
import com.google.firebase.database.*;

public class FirebaseDataManager extends AppCompatActivity {

    protected DatabaseReference itemReference;
    protected DatabaseReference databaseReference;

    public void setCurrentData(DataLoadListener listener) {
        String itemId = getIntent().getStringExtra("id");
        itemReference = databaseReference.child("barbeiro").child(itemId);

        itemReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Barbeiro currentData = dataSnapshot.getValue(Barbeiro.class);

                listener.onDataLoaded(currentData);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Pending implementation
            }
        });
    }
}
