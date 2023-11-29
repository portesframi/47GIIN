package com.mycompany.viuproyecto;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Asignatura Proyecto de ingenieria de software
 * 
 * @author frami
 */

/**
* La clase Conexion es la que establece la conexión con la base de datos Firebase
*/
public class Conexion {
    
    /**
    * La clase Conexion es la que establece la conexión con la base de datos Firebase
    */
    public static Firestore db;
    
    /* Esta clase la utilicé para conectar a Firebase antes de la separacion por capas MVC
    public static void conectarFirebase() throws IOException{
        
        try{
            FileInputStream sa = new FileInputStream("viuproyecto.json");
        
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(sa))
                .build();

            FirebaseApp.initializeApp(options);
            db = FirestoreClient.getFirestore();
            System.out.println("Conexión realizada con éxito");
        } catch (FileNotFoundException e){
            System.err.println("Error: "+e.getMessage());
        }
    }
    */
    
    /** En este caso utilizo un metodo de tipo Firestore para conectar aplicando la arquitectura MVC
     * 
     * @return abre Bd
     */
     public static Firestore getConnection() {
        FirestoreOptions firestoreOptions = null;
        try {
            firestoreOptions = FirestoreOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream("viuproyecto.json")))
                    .build();
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        Firestore dbMVC = firestoreOptions.getService();
        return dbMVC;
    }
}
