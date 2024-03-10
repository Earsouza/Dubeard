package com.dubeard.firebase;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;

public class FirebaseDataManager <T> extends AppCompatActivity {

    private String nodeId;
    private Class<T> entity;
    private DatabaseReference nodeReference;

    private FirebaseDataManager() { }

    public FirebaseDataManager(Class<T> entity, String nodeId) {
        this.entity = entity;
        this.nodeId = nodeId;
    }

    public DatabaseReference getNodeReference() {
        return nodeReference;
    }

    public void setCurrentData(DataLoadListener listener) {
        nodeReference = FirebaseDatabase.getInstance().getReference().child("barbeiro").child(nodeId);

        if (nodeReference != null) {
            nodeReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    T currentData = dataSnapshot.getValue(entity);
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
