package com.dubeard.firebase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseAdminInitializer {

    public static void initialize() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("path/to/seu-arquivo-firebase-adminsdk.json");
//não carrega as dependências
        /*FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(FirebaseQCredentials.fromCertificate(serviceAccount))
                .setDatabaseUrl("https://dubeardifsc-default-rtdb.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);*/

    }
}