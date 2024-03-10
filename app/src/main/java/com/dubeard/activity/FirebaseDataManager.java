package com.dubeard.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class FirebaseDataManager <T> extends AppCompatActivity {

    private Class<T> clazz;
    private DatabaseReference nodeReference;

    public FirebaseDataManager(Class<T> clazz) {
        this.clazz = clazz;
    }

    public DatabaseReference getNodeReference() {
        return nodeReference;
    }

    public void setNodeReference(DatabaseReference nodeReference) {
        this.nodeReference = nodeReference;
    }

    public void setCurrentData(DataLoadListener listener) {

        if (nodeReference != null) {
            nodeReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    T currentData = dataSnapshot.getValue(clazz);
                    listener.onDataLoaded(currentData);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    //Pending implementation
                }
            });
        }
    }
}
