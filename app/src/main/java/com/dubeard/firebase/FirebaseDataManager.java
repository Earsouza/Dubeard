package com.dubeard.firebase;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;

public class FirebaseDataManager <T> extends AppCompatActivity {

    private String entityName;
    private String nodeId;
    private Class<T> entity;
    private DatabaseReference nodeReference;
    private DatabaseReference entityReference;

    private FirebaseDataManager() { }

    public FirebaseDataManager(Class<T> entity, String entityName, String nodeId) {
        this.entity = entity;
        this.entityName = entityName;
        this.nodeId = nodeId;
    }

    public void init() {
        entityReference = FirebaseDatabase.getInstance().getReference().child(entityName);
        nodeReference = entityReference.child(nodeId);
    }

    public DatabaseReference getNodeReference() {
        return nodeReference;
    }

    public void setCurrentData(DataLoadListener listener) {
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
